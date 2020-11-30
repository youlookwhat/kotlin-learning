package com.kotlin.jingbin.kotlinapp.typesystom

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlin.jingbin.kotlinapp.R
import java.io.BufferedReader
import java.io.StringReader
import java.lang.NumberFormatException

/**
 * 6.3 数组与集合
 */
class TypeSystem3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_type_system)

        /**-------------------- 6.3.1 可空性和集合 ----------------------*/
        // 代码清单6.21 创建一个包含可空值的集合
        // 从一个文件中读取文本行的列表，并尝试把每一行文本解析成一个数字
        fun readNumbers(reader: BufferedReader): List<Int?> {
            // 创建包含可空Int值的列表
            val arrayList = ArrayList<Int?>()
            for (line in reader.readLine()) {
                try {
                    val toInt = line.toInt()
                    // 向列表添加整数（非空值）
                    arrayList.add(toInt)
                } catch (e: NumberFormatException) {
                    // 向列表添加null
                    arrayList.add(null)
                }
            }
            return arrayList
        }

        // 代码清单6.22 使用可空值的集合
        fun addValidNumbers(numbers: List<Int?>) {
            var sumOfValidNumbers = 0
            var invalidNumbers = 0
            for (number in numbers) {
                if (number != null) {
                    sumOfValidNumbers += number
                } else {
                    invalidNumbers++
                }
            }
            println("sumOfValidNumbers:  $sumOfValidNumbers")
            println("invalidNumbers:  $invalidNumbers")
        }

        val bufferedReader = BufferedReader(StringReader("1\nabc\n42"))
        // [1,null,42]
        val readNumbers = readNumbers(bufferedReader)
        addValidNumbers(readNumbers)
        // sumOfValidNumbers:  43
        // invalidNumbers:  1

        // 代码清单6.23 对包含可空值的集合使用 filterNotNull
        fun addValidNumbers2(numbers: List<Int?>) {
            // 类型为List<Int>，因为过滤保证了不会出现null
            val filterNotNull: List<Int> = numbers.filterNotNull()
            println("sum of valid numbers::  ${filterNotNull.sum()}")
            println("Invalid numbers:  ${numbers.size - filterNotNull.size}")
        }

        /**-------------------- 6.3.2 只读集合和可变集合 ----------------------*/
        /*
        * Kotlin的集合设计和Java不同的另一项重要特质是，它把访问集合数据的接口和修改集合数据的接口分开了。
        * 一般的规则是在代码的任何地方都应该使用只读接口，只在代码需要修改集合的地方使用可变接口的变体。
        *
        * Collection
        *  - size
        *  - iterator()
        *  - contains()
        * MutableCollection 继承Collection
        *  - add()
        *  - remove()
        *  - clear()
        */

        // 代码清单6.24 使用只读集合接口与可变集合接口
        fun <T> copyElements(source: Collection<T>, target: MutableCollection<T>) {
            // 在source集合中的所有元素中循环
            for (item in source) {
                // 想可变的target集合中添加元素
                target.add(item)
            }
        }

        val source: Collection<Int> = arrayListOf(3, 5, 7)
        val target: MutableCollection<Int> = arrayListOf(1)
        copyElements(source, target)
        println(target)// [1,3,5,7]

        // 只读集合不一定是不可变的，只读集合并不总是线程安全的。


        /**-------------------- 6.3.3 Kotlin集合和Java ----------------------*/

    }

    companion object {
        fun start(context: Context) {
            val intent = Intent()
            intent.setClass(context, TypeSystem3Activity::class.java)
            context.startActivity(intent)
        }
    }
}
