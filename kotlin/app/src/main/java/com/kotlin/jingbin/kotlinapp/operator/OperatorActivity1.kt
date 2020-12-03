package com.kotlin.jingbin.kotlinapp.operator

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlin.jingbin.kotlinapp.R

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
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent()
            intent.setClass(context, OperatorActivity1::class.java)
            context.startActivity(intent)
        }
    }
}
