package com.kotlin.jingbin.kotlinapp.typesystom

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlin.jingbin.kotlinapp.R
import com.kotlin.jingbin.kotlinapp.`object`.JavaCode
import com.kotlin.jingbin.kotlinapp.`object`.Object4Activity
import java.io.BufferedReader
import java.io.File
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
        /*
        * 集合创建函数
        * 集合类型    只读                可变
        * List      listOf          mutableListOf、arrayListOf
        * Set       setOf           mutableSetOf、hashSet、linkedSetOf、sortedSetOf
        * Map       mapOf           mutableMapOf、hashMapOf、linkedMapOf、sortMapOf
        *
        * Java并不会区分只读集合与可变集合，即使Kotlin中把集合声明成只读的，Java代码页能够修改这个集合。
        */

        /*Java*/
//        public static class CollectionUtils {
//            public static List<String> uppercaseAll(List<String> items) {
//                for (int i = 0; i < items.size(); i++) {
//                    items.set(i, items.get(i).toLowerCase());
//                }
//                return items;
//            }
//        }

        // 声明只读的参数
        fun printInUppercase(list: List<String>) {
            // 调用可以修改集合的Java函数
            println(JavaCode.CollectionUtils.uppercaseAll(list))
            // 打印被修改过的函数
            println(list.first())
        }

        val listOf = listOf("a", "b", "c")
        printInUppercase(listOf)// [A,B,C] A


        /**-------------------- 6.3.4 作为平台类型的集合 ----------------------*/
        /*
        * 集合是否可空？
        * 集合中的元素是否可空？
        * 你的方法会不会修改集合？
        */

        // 代码清单6.25 使用集合参数的Java接口r
//        public interface FileContentProcessor {
//            void processContents(File path, byte[] binaryContents, List<String> textContents);
//        }

        // 代码清单6.26 FileContentProcessor的kotlin实现
        class FileIndexer : JavaCode.FileContentProcessor {
            override fun processContents(path: File, binaryContents: ByteArray?, textContents: MutableList<String>?) {

            }
        }

        // 代码清单6.27 另一个使用集合参数的Java接口
//        public interface DataParser<T> {
//            void parseData(String input, List<T> output, List<String> errors);
//        }

        // 代码清单6.28 DataParser的kotlin实现
        class PersonParser : JavaCode.DataParser<Object4Activity.Person> {
            override fun parseData(input: String, output: MutableList<Object4Activity.Person>, errors: MutableList<String?>?) {
                // 默认是都会为空、集合元素不为空、方法会修改集合。
                // 需要根据具体场景配置设置
            }
        }


        /**-------------------- 6.3.5 对象和基本数据类型的数组 ----------------------*/
        fun main(args: Array<String>) {
            // 使扩展属性 array.indices 在下标的范围内迭代
            for (i in args.indices) {
                // 通过下标使用array[index]访问元素
                println("Argument $i is: ${args[i]}")
            }
        }

        // 代码清单6.30 创建字符数组
        val array = Array<String>(26) { i -> ('a' + i).toString() }
        println(array.joinToString(""))// abcd...

        // 代码清单6.31 向vararg方法传递集合
        val listOf1 = listOf("a", "b", "c")
        // 期望vararg参数时使用展开运算符 (*) 传递数组
        println("%s%s%s".format(*listOf1.toTypedArray()))

        // 创建存储了5个0的整型数组的两种选择：
        val fiveZeros = IntArray(5)
        val intArrayOf = intArrayOf(0, 0, 0, 0, 0)
        // 接收lambda的构造方法的例子
        val intArray = IntArray(5) { i -> (i + 1) * (i + 1) }
        println(intArray.joinToString())// 1,4,9,16,25

        // 代码清单6.32 对数组使用 forEachIndexed
        fun main2(args: Array<String>) {
            args.forEachIndexed { index, element -> println("Argument $index is: $element !!!!") }
        }
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent()
            intent.setClass(context, TypeSystem3Activity::class.java)
            context.startActivity(intent)
        }
    }
}
