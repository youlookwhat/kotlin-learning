package com.kotlin.jingbin.kotlinapp.operator

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlin.jingbin.kotlinapp.R
import java.math.BigDecimal

/**
 * 7.1 运算符
 * */
class OperatorActivity1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_operator1)

        /**-------------------- 7.1.1 重载二元算术运算 ----------------------*/
        // 代码清单7.1 定义一个plus运算符
        data class Point(val x: Int, val y: Int) {
            // 定义一个名为 plus 的方法
            operator fun plus(other: Point): Point {
                return Point(x + other.x, y + other.y)
            }
        }
        println(Point(10, 10).plus(Point(12, 13)).x)// 22
        val point = Point(10, 10)
        val point2 = Point(12, 13)
        // 通过使用 + 号来调用plus方法
        println(point + point2)// Point(x=22, y=23)

        /*
        * 在使用了operator修饰符声明了plus函数后，你就可以直接使用_好来求和了。
        * 除了这个运算符声明为一个 成员函数 外，也可以把它定义为一个扩展函数
        */
        // 代码清单7.2 把运算符定义为扩展函数
        operator fun Point.plus(other: Point): Point {
            return Point(x + other.x, y + other.y)
        }

        /*
        * 可重载的二元算术运算符
        *  表达式     函数名
        *  a * b     times
        *  a / b     div
        *  a % b     mod
        *  a + b     plus
        *  a - b     minus
        */

        // 代码清单7.3 定义一个运算数类型不同的运算符
        operator fun Point.times(scale: Double): Point {
            return Point((x * scale).toInt(), (y * scale).toInt())
        }

        val point3 = Point(10, 20)
        println(point3 * 2.0)// Point(x=20,y=40)

        // 注意不能使用 2.0 * point3，如果需要使用需要另定义
        operator fun Double.times(point: Point): Point {
            return Point((this * point.x).toInt(), (this * point.y).toInt())
        }
        println(3.0 * point3)// Point(x=30,y=60)

        // 代码清单7.4 定义一个返回结果不同的运算符
        operator fun Char.times(count: Int): String {
            return toString().repeat(count)
        }
        println('a' * 3)// aaa

        /*
        * 没有用于位运算的特殊运算符
        * 以下，用于执行位运算的完整函数列表
        * shl  --  带符号左移
        * shr  --  带符号右移
        * ushr --  无符号右移
        * and  --  按位与
        * or   --  按位或
        * xor  --  按位异或
        * inv  --  按位取反
        */
        // 使用方法:
        println(0x0F and 0x0F)// 0
        println(0x0F or 0x0F)// 255
        println(0x1 shl 4)// 16

        /**-------------------- 7.1.2 重载复合赋值运算符 ----------------------*/
        // 对可变变量var有效，例子：
        var point1 = Point(1, 2)
        point1 += Point(1, 1)
        println(point1)// Point(x=2,y=3)

        // 将元素添加到可变集合，例子：
        val numbers = ArrayList<Int>()
        numbers += 42
        println(numbers[0])// 42

        /*
        * 如果你定义了一个返回值 Unit，名为plusAssign函数，Kotlin将会在用到+=运算符的地方调用它。
        * 其他如 minusAssign、timeAssign
        */
        operator fun <T> MutableCollection<T>.plusAssign(element: T) {
            this.add(element)
        }

        /*
        * a += b
        * a = a.plus(b)
        * a.plusAssign(b)
        * 运算符 += 可以被转换为plus或者plusAssign函数的调用
        * + 和 - 运算符总是返回一个新的集合。
        * +=和-=运算符用于可变集合时，始终就地修改它们，用于只读集合时，会返回一个修改过的副本。
        */
        val list = arrayListOf(1, 2)
        list += 3

        val newList = list + listOf(4, 5)
        println(list)// 就地修改 [1,2,3]
        println(newList)// 新的集合 [1,2,3,4,5]


        /**-------------------- 7.1.3 重载一元运算符 ----------------------*/
        // 代码清单7.5 定义一个一元运算符
        operator fun Point.unaryMinus(): Point {
            // 一元运算符无参数
            return Point(-x, -y)
        }

        val point4 = Point(10, 20)
        println(-point4)// Point(x=-10,y=-20)

        /*
        * +a  --   a.unaryPlus()
        * 一元运算符 + 被转换为unaryPlus函数的调用
        * 可重载的一元算法的运算符
        * 表达式   函数名
        * +a      unaryPlus
        * -a      unaryMinus
        * !a      not
        * ++a,a++ inc
        * --a,a-- dec
        */

        // 代码清单7.6 定义一个自增运算符
        operator fun BigDecimal.inc() = this + BigDecimal.ONE

        var db = BigDecimal.ZERO
        println(db++)// 0  在执行后添加
        println(++db)// 2  在执行前添加
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent()
            intent.setClass(context, OperatorActivity1::class.java)
            context.startActivity(intent)
        }
    }
}
