package com.kotlin.jingbin.kotlinapp.website.functionlambda

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlin.jingbin.kotlinapp.R

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

        // 3.高阶函数
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

        // 函数引用也可以用于高阶函数调用：
        val product = items.fold(1, Int::times)

    }

    companion object {
        fun start(context: Context) {
            val intent = Intent()
            intent.setClass(context, FunctionLambdaActivity::class.java)
            context.startActivity(intent)
        }
    }
}
