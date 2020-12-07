package com.kotlin.jingbin.kotlinapp.operator

import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlin.jingbin.kotlinapp.R
import java.time.LocalDate

/**
 * 7.3 集合与区间的约定
 * */
class Operator3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_operator1)

        /**-------------------- 7.3.1 通过下标来访问元素 get 和 set ----------------------*/
        // kotlin中可以使用类似java中数组中的方式来访问map中的元素---使用方括号
//        val value = map[key]
        // 也可以用同样的运算符来改变一个可变map的元素
//        mutableMap[key] = newValue

        // 使用方括号来引用点的坐标：p[0]访问x坐标，p[1]访问y坐标。
        // 代码清单7.9 实现 get 约定
        // 定义一个名为 get 的运算符函数
        operator fun Point.get(index: Int): Int {
            // 根据给出的index返回对应的坐标
            return when (index) {
                0 -> x
                1 -> y
                else -> throw IndexOutOfBoundsException("Invalid coordinate $index")
            }
        }

        val point = Point(1, 2)
        println(point[1])// 2

        /*
        * x[a,b] -> x.get(a,b)
        *
        * get的参数可以是任何类型，而不只是Int，还可以是多个参数。例如 matrix[row,rol]
        */
        // 代码清单7.10 实现 set 的约定方法
        data class MutablePoint(var x: Int, var y: Int)

        // 定义一个名为 set 的运算符函数
        operator fun MutablePoint.set(index: Int, value: Int) {
            when (index) {
                // 根据给出的index参数修改对应的坐标
                0 -> x = value
                1 -> y = value
                else -> throw java.lang.IndexOutOfBoundsException("Invalid coordinate $index")
            }
        }

        val mutablePoint = MutablePoint(10, 20)
        mutablePoint[1] = 42
        println(mutablePoint)// MutablePoint(x=10,y=42)

        // set 最后一个参数用来接收赋值语句中等号右边的值。
        // x(a,b) = c  ->  x.set(a,b,c)


        /**-------------------- 7.3.2 in 的约定 ----------------------*/
        // 代码清单7.11 实现in的约定
        data class Rectangle(val upperLeft: Point, val lowerRight: Point)

        operator fun Rectangle.contains(p: Point): Boolean {
            // 构建一个区间，检查坐标x是否属于这个区间，使用 until 函数来构建一个开区间
            return p.x in upperLeft.x until lowerRight.x &&
                    p.y in upperLeft.y until lowerRight.y
        }

        /*
        * in 右边的对象将会调用 contains 函数，in 左边的对象将会作为函数入参。
        * a in c -> c.contains(a)
        * in 10..20        [10,20] 用 10..20构建一个普通的闭区间。
        * in 10 until 20   [10,20) 开区间是不包括最后一个点的区间。
        */

        /**-------------------- 7.3.3 rangeTo 的约定 ----------------------*/
        /*
        * start..end  ->  start.rangeTo(end)
        * .. 运算符将被转换为 rangeTo函数的调用
        */

        // 这个函数返回一个区间，可以用来检测其他一些元素是否属性它。
//        operator fun <T : Comparable<T>> T.rangeTo(that: T): ClosedRange<T>

        // 代码清单7.12 处理日期的区间

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val now = LocalDate.now()
            // 创建一个从今天开始的10天的区间
            val closedRange = now..now.plusDays(10)
            // 检测一个特定的日期是否属于这个区间
            println(now.plusWeeks(1) in closedRange) // true

            // rangeTo 运算符的优先级低于算术运算符，但是最好把参数括起来
            val n = 9
            println(0..(n + 1))// 0..10

            (0..9).forEach {
                // 把区间括起来，来调用它的方法
                println(it)// 0123456789
            }


            /**-------------------- 7.3.4 在 for 循环中使用 iterator 的约定 ----------------------*/
            // in 在for循环中使用被执行迭代，转换成 list.iterator()
            // 这个库函数让迭代字符串成为可能
//         operator fun CharSequence.iterator():CharIterator
            for (c in "abc") {
                println(c)
            }

            // 代码清单7.13 实现日期区间的迭代器
            operator fun ClosedRange<LocalDate>.iterator(): Iterator<LocalDate> =
                    // 这个对象实现了遍历LocalDate元素的Iterator
                    object : Iterator<LocalDate> {
                        var current = start

                        // 注意，这里日期用到了compareTo约定
                        override fun hasNext() = current <= endInclusive

                        // 在修改前返回当前日期作为结果
                        override fun next() = current.apply {
                            // 把当前日期增加一天
                            current = plusDays(1)
                        }
                    }

            val ofYearDay = LocalDate.ofYearDay(2017, 1)
//            val dayOff = ofYearDay.minusDays(1)..newYear()
            // 对应的 iterator函数实现后，遍历daysOff
//            for (dayOff in daysOff) {
//                println(dayOff)
//            }
        }

    }

    companion object {
        fun start(context: Context) {
            val intent = Intent()
            intent.setClass(context, Operator3Activity::class.java)
            context.startActivity(intent)
        }
    }
}
