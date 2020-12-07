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
 * 7.4 解构声明和组件函数
 * */
class Operator4Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_operator1)

        class Point(val x: Int, val y: Int) {
            operator fun component1() = x
            operator fun component2() = y
        }


        // 解构声明：允许你展开单个复合值，并使用它来初始化多个单独变量
        val point = Point(10, 20)
        val (x, y) = point
        println(x)// 10
        println(y)// 20
        /*
        * 一个解构声明看起来像一个普通的变量声明，但它在括号中有多个变量。约定原理。
        * val (x,y) = point   :
        * val a = p.component1()
        * val b = p.component2()
        *
        * 主要场景之一：从一个函数返回多个值。
        */

        // 代码清单7.14 使用解构声明来返回多个值
        // 声明一个数据类来持有值
        data class NameComponents(val name: String, val extension: String)

        fun splitFileName(fullName: String): NameComponents {
            val result = fullName.split(".", limit = 2)
            // 返回也该数据类型的示例
            return NameComponents(result[0], result[1])
        }
        // 使用解构声明来展开这个类
        val (name, ext) = splitFileName("example.kt")
        println(name)// example
        println(ext)// ext

        // 代码清单7.15 使用解构声明来处理集合
        fun splitFilename(fullName: String): NameComponents {
            val (name, extension) = fullName.split(".", limit = 2)
            return NameComponents(name, extension)
        }
        // 标准库只允许使用此语法来访问一个对象的前五个元素，让一个函数能返回多个值有更简单的方法，使用标准库中的Pair和Triple类。


        /**-------------------- 7.4.1 解构声明和循环 ----------------------*/
        // 代码清单7.16 用解构声明来遍历 map
        // 使用这个语法来打印给定map中的所有条目
        fun printEntries(map: Map<String, String>) {
            for ((key, value) in map) {
                // 在 in 循环中用解构声明
                println("$key -> $value")
            }
            // 等同于这个
            for (entry in map.entries) {
                val key = entry.component1()
                val value = entry.component2()
                println("$key -> $value")
            }
        }

        val mapOf = mapOf("Oracle" to "Java", "JetBrains" to "Kotlin")
        printEntries(mapOf)
        // 使用了两个Kotlin约定：一个是迭代一个对象 一个是用于解构声明

    }

    companion object {
        fun start(context: Context) {
            val intent = Intent()
            intent.setClass(context, Operator4Activity::class.java)
            context.startActivity(intent)
        }
    }
}
