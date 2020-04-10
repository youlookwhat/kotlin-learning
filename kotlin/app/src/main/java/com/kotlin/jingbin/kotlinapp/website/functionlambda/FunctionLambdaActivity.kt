package com.kotlin.jingbin.kotlinapp.website.functionlambda

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlin.jingbin.kotlinapp.R
import com.kotlin.jingbin.kotlinapp.utils.DebugUtil

/**
 * 网站学习：函数与Lambda表达式
 *  - 函数：https://www.kotlincn.net/docs/reference/functions.html
 * */
class FunctionLambdaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person)

        // 1.返回 Unit 的函数 (如果一个函数不返回任何有用的值，它的返回类型是 Unit)
        fun printHello(name: String?): Unit {
            if (name != null)
                println("Hello ${name}")
            else
                println("Hi there!")
            // `return Unit` 或者 `return` 是可选的
        }

        // 2.尾递归函数
        // Kotlin 支持一种称为尾递归的函数式编程风格。 这允许一些通常用循环写的算法改用递归函数来写，而无堆栈溢出的风险。
        // 当一个函数用 tailrec 修饰符标记并满足所需的形式时，编译器会优化该递归，留下一个快速而高效的基于循环的版本
        val eps = 1E-10 // "good enough", could be 10^-15

        tailrec fun findFixPoint(x: Double = 1.0): Double =
                if (Math.abs(x - Math.cos(x)) < eps) x else findFixPoint(Math.cos(x))

        // 3.高阶函数：高阶函数是将函数用作参数或返回值的函数
        fun <T, R> Collection<T>.fold(
                initial: R,
                combine: (acc: R, nextElement: T) -> R
        ): R {
            var accumulator: R = initial
            for (element: T in this) {
                accumulator = combine(accumulator, element)
            }
            return accumulator
        }

        val items = listOf(1, 2, 3, 4, 5)

        // Lambdas 表达式是花括号括起来的代码块。
        items.fold(0, {
            // 如果一个 lambda 表达式有参数，前面是参数，后跟“->”
            acc: Int, i: Int ->
            print("acc = $acc, i = $i, ")
            val result = acc + i
            println("result = $result")
            // lambda 表达式中的最后一个表达式是返回值：
            result
        })

        // lambda 表达式的参数类型是可选的，如果能够推断出来的话：
        val joinedToString = items.fold("Elements:", { acc, i -> acc + " " + i })
        DebugUtil.e(joinedToString)

        // 函数引用也可以用于高阶函数调用： 二元操作: a * b	a.times(b)
        val product = items.fold(1, Int::times)
        DebugUtil.e("product:" + product)

        // 4. 函数类型实例调用 函数类型的值可以通过其 invoke(……) 操作符调用：f.invoke(x) 或者直接 f(x)。
        val stringPlus: (String, String) -> String = String::plus
        val intPlus: Int.(Int) -> Int = Int::plus

        println(stringPlus.invoke("<-", "->"))
        println(stringPlus("Hello, ", "world!"))

        println(intPlus.invoke(1, 1)) // 2
        println(intPlus(1, 2)) // 3
        println(2.intPlus(3)) // 5、类扩展调用

        // 5.Lambda 表达式与匿名函数
//         max(strings,{a,b -> a.length<b.length})
        fun compare(a: String, b: String): Boolean = a.length < b.length

        // 6.Lambda 表达式语法
        val sum: (Int, Int) -> Int = { x: Int, y: Int -> x + y }
        sum.invoke(1, 1)
        sum(1, 2)
//        2.sum(3)

        val sum2 = { x: Int, y: Int -> x + y }
        sum2.invoke(1, 1)
        sum2(1, 2)

        // 7.传递末尾的 lambda 表达式
        val product2 = items.fold(1) { acc, e -> acc * e }

        // 8.匿名函数
        fun(x: Int, y: Int): Int = x + y

        fun(x: Int, y: Int): Int {
            return x + y
        }
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent()
            intent.setClass(context, FunctionLambdaActivity::class.java)
            context.startActivity(intent)
        }
    }
}
