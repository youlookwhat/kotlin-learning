package com.kotlin.jingbin.kotlinapp.generic

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlin.jingbin.kotlinapp.R

/**
 * 9.1 泛型类型参数
 */
class Generic1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generic1)

        title = "9.1 泛型类型参数"

        // 如果要创建一个空的列表，必须显示的指定，有值的话可以被推导出来
        val readers: MutableList<String> = mutableListOf()
        val readers1 = mutableListOf<String>()

        val reader2 = listOf("jingbin", "jinbeen")

        /**-------------------- 9.1.1 泛型函数和属性 ----------------------*/
        // slice 泛型函数的类型形参为T
        fun <T> List<T>.slice(indices: IntRange): List<T>? {
            return null
        }
        /*
        * 第一个 <T> :类型形参声明
        * 第二个 <T> :接收者 类型使用了类型形参
        * 第三个 <T> :返回 类型使用了类型形参
        */

        // 代码清单9.1 调用泛型函数
        val letters = ('a'..'z').toList()
        // 显示地指定类型实参
        println(letters.slice<Char>(0..2))// [a,b,c]
        // 编译器推导出这里的T是Char
        println(letters.slice(10..13))// [k,l,m,n]

        // 代码清单9.2 调用泛化的高阶函数
        val authors = listOf("jingbin", "jinbeen")
        val readers3 = mutableListOf<String>()
        fun <T> List<T>.filter(predicate: (T) -> Boolean): List<T>? {
            return null
        }
        readers3.filter { it !in authors }


        // 这个 泛型扩展函数 能任何种类元素的列表上调用
//        val <T> List<T>.penultimate: T
//        get() = this[size - 2]

        // 在这次调用中，类型参数T被推导成Int
        println(listOf(1, 2, 3, 4).penultimate)


        /**-------------------- 9.1.2 声明泛型类 ----------------------*/
        // List 接口定义了类型参数T
//        interface List2<T> {
        // 在接口或类的内部，T可以当作普通类型使用
//            operator fun get(index: Int): T
//        }

        // 这个类实现了List2，提供了具体类型实参: String
        class StringList : List2<String> {
            // 注意 T 如何被 String 代替
            override fun get(index: Int): String = ""
        }

        // 现在  ArrayList 的泛型类型形参 T 就是List的类型实参
        class ArrayList<T> : List2<T> {
            override fun get(index: Int): T {
                TODO("not implemented")
            }
        }

        // 一个类可以把它自己作为类型实参引用
//        interface Comparable<T>{
//            fun compareTo(other:T):Int
//        }
        class String3 : Comparable<String3> {
            override fun compareTo(other: String3): Int {
                return 1
            }
        }


        /**-------------------- 9.1.3 类型参数约束 ----------------------*/
        // Java
//        <T extends Number> T sum(List<T> list)

        // Kotlin
        // 通过在类型参数后指定上界来定义约束
//        fun <T : Number> List<T>.sum(): T

        println(listOf(1, 2, 3).sum())// 6

        // 指定 Number 为类型形参的上界
        fun <T : Number> onHalf(value: T): Double {
            // 调用Number类中的方法
            return value.toDouble() / 2.0
        }
        println(onHalf(3))// 1.5

        // 代码清单9.3 声明带类型参数约束的函数
        // 这个函数的实参必须是可比较的元素
        fun <T : kotlin.Comparable<T>> max(first: T, second: T): T {
            return if (first > second) first else second
        }
        // 字符串按字母表顺序比较
        println(max("kotlin", "java"))// kotlin

        // 代码清单9.4 为一个类型参数指定多个约束
        fun <T> ensureTrailingPeriod(seq: T)
                where T : CharSequence, T : Appendable {// 类型参数约束的列表
            if (!seq.endsWith('.')) {// 调用为 CharSequence 接口定义的扩展函数
                seq.append('.')// 调用为 Appendable 接口的方法
            }
        }

        val helloWord = StringBuilder("Hello World")
        ensureTrailingPeriod(helloWord)
        println(helloWord)// Hello World.


        /**-------------------- 9.1.4 让类型形参非空 ----------------------*/
        // 没有指定上界的类型形参将会使用 Any?
        class Processor<T> {
            fun process(value: T) {
                value?.hashCode()// value 是可空的，所以要用安全调用
            }
        }

        // 可空类型 String? 被用来替代T
        val processor = Processor<String?>()
        // 使用 null 作为value实参的代码可以编译
        processor.process(null)

        // 保证替代类型始终是非空类型
        class Processor2<T : Any> {
            fun process(value: T) {
                value.hashCode()
            }
        }
    }

    // 一个类可以把它自己作为类型实参引用
    interface Comparable<T> {
        fun compareTo(other: T): Int
    }

    // List 接口定义了类型参数T
    interface List2<T> {
        // 在接口或类的内部，T可以当作普通类型使用
        operator fun get(index: Int): T
    }

    // 这个泛型扩展函数能任何种类元素的列表上调用
    val <T> List<T>.penultimate: T
        get() = this[size - 2]

    companion object {
        fun start(context: Context) {
            val intent = Intent()
            intent.setClass(context, Generic1Activity::class.java)
            context.startActivity(intent)
        }

    }


}
