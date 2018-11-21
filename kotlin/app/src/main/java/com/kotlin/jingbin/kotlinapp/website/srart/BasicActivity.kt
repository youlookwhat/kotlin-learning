package com.kotlin.jingbin.kotlinapp.website.srart

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlin.jingbin.kotlinapp.R
import com.kotlin.jingbin.kotlinapp.classproperty.Rectangle

/**
 * 3、使用 for 循环
 * 4、使用 while 循环
 * 5、使用区间（range）
 * 6、使用集合
 * 7、创建基本类及其实例
 */
class BasicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic)

        /**-------------------使用 for 循环-------------------------------*/
        val items = listOf("apple", "google", "bevol")
        for (item in items) {
            println(item)
        }
        for (index in items.indices) {
            println("item at $index is ${items[index]}")
        }

        /**-------------------使用 while 循环-------------------------------*/
        var index2 = 0
        while (index2 < items.size) {
            println("item at $index2 is ${items[index2]}")
            index2++
        }

        /**-------------------使用区间（range）-------------------------------*/
        // 使用 in 运算符来检测某个数字是否在指定区间内
        val x = 10
        val y = 9
        if (x in 1..y + 1) {
            println("fits in range")
        }

        // 检测某个数字是否在区间外
        val list = listOf("a", "b", "c")
        if (-1 !in 0..list.lastIndex) {
            println("-1 is out of range")
        }
        if (list.size !in list.indices) {
            println("list size is  out of valid list indices range ,too")
        }

        // 区间迭代
        for (x2 in 1..5) {
            println(x2)
        }
        // 数列迭代
        for (x in 1..10 step 2) {
            // 13579
            println(x)
        }
        println()
        for (x in 9 downTo 0 step 3) {
            // 9630
            println(x)
        }

        /**-------------------使用集合-------------------------------*/
        // 对集合进行迭代:
        for (item in items) {
            println(item)
        }

        // 使用 in 运算符来判断集合内是否包含某实例：
        when {
            "apple" in items -> println("apple is yes ")
            "haha" in items -> println("haha is no ")
        }

        // 使用 lambda 表达式来过滤（filter）与映射（map）集合：
        val fruits = listOf("apple", "banana", "avocado", "kiwifruit")
        fruits.filter { it.startsWith("a") }
                .sortedBy { it }
                .map { it.toUpperCase() }
                .forEach { println(it) }

        /**-------------------创建基本类及其实例-------------------------------*/
        // 不需要关键字
        val rectangle = Rectangle(2, 1)

    }
}
