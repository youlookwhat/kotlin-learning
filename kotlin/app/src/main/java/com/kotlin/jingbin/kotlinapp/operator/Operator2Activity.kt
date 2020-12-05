package com.kotlin.jingbin.kotlinapp.operator

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlin.jingbin.kotlinapp.R

/**
 * 7.2 重载比较运算符
 * */
class Operator2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_operator1)

        /**-------------------- 7.2.1 等号运算符 equals ----------------------*/
        /*
        * == != 可以用于可空运算数
        * a == b  -> a? equals(b) ?: (b==null)
        */

        // 代码清单7.7 实现equals函数
        class Point(val x: Int, val y: Int) {

            // 重写在Any中定义的方法
            override fun equals(other: Any?): Boolean {
                // 优化：检查参数是否与this是统一对象
                if (other === this) return true
                // 检查参数类型
                if (other !is Point) return false
                // 智能转换为Point来访问x、y属性
                return other.x == x && other.y == y
            }
        }
        println(Point(10, 20) == Point(10, 20))// true
        println(Point(10, 20) != Point(5, 5))// true
        println(null == Point(10, 20))// false

        // 恒等运算符（===）：来检查参数与调用equals的对象是否相同。
        // Any中的基本方法已经将equals方法标记为 operator 了


        /**-------------------- 7.2.2 排序运算符 compareTo ----------------------*/
        /*
        * a >= b  -> a.compareTo(b) >= 0
        * 两个对象的比较被转换为compareTo的函数调用，然后结果与零比较
        */

        // 代码清单7.8 实现 compareTo 方法
        class Person(val firstName: String, val lastName: String) : Comparable<Person> {

            override fun compareTo(other: Person): Int {
                // 按顺序调用给定的方法，并比较它们的值
                return compareValuesBy(this, other, Person::lastName, Person::firstName)
            }
        }

        val person = Person("Alice", "Smith")
        val person2 = Person("Bob", "Johnson")
        println(person < person2)// false

        /*
        * compareValuesBy 这个函数接收用来计算比较值的一系列回调，按顺序依次调用回调方法，两两一组分别做比较，
        * 并返回结果。如果值不同，则返回比较结果；如果它们相同，则继续调用下一个；如果没有更多的回调来调用，则返回0。
        * */
        println("abc" < "bac")// true
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent()
            intent.setClass(context, Operator2Activity::class.java)
            context.startActivity(intent)
        }
    }
}
