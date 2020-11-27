package com.kotlin.jingbin.kotlinapp.lambda

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.kotlin.jingbin.kotlinapp.R

/**
 * 5.5 带接收者的lambda: “with”与“apply”
 * */
class Lambda5Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lambda)

        title = "带接收者的lambda: “with”与“apply”"

        /*--------------- 5.5.1 “with”函数-------------*/
        // 对同一对象执行多次操作，而不需要反复把对象的名称写出来。
        // 代码清单5.16 构建字母表
        fun alphabet(): String {
            val result = StringBuilder()
            for (letter in 'A'..'Z') {
                result.append(letter)
            }
            result.append("\nNow I know the alphabet!")
            return result.toString()
        }
        println(alphabet())

        // 代码清单5.17 使用with构建字母表
        fun alphabet2(): String {
            val stringBuilder = StringBuilder()
            // 指定接收者的值，你会调用它的方法
            return with(stringBuilder) {
                for (letter in 'A'..'Z') {
                    this.append(letter)
                }
                // 通过显示的 this 或者省掉this 都可以调用方法
                append("\nNow I know the alphabet!2")
                // 从lambda返回值
                this.toString()
            }
        }
        println(alphabet2())

        /*
         * with 结构看起来是一种特殊的语法结构，但它实际上是一个接收两个参数的函数：这个例子分别是stringBuilder和一个lambda。lambda放在了括号外。
         * lambda是一种类似普通函数的定义行为的方式。而带接收者的lambda是类似扩展函数的定义行为的方式。
         */

        // 代码清单5.18 使用with和一个表达式函数体来构建字母表
        fun alphabet3() = with(StringBuilder()) {
            for (letter in 'A'..'Z') {
                append(letter)
            }
            append("\nNow I know the alphabet!3")
            toString()
        }

        // with 返回的值是执行lambda代码的结果，该结果就是lambda中的最后一个表达式的值。
        // apply 返回的是接收者对象


        /*--------------- 5.5.1 “apply”函数-------------*/
        // apply 函数几乎和 with 函数一模一样， 唯一的区别是 apply 始终会返回作为实参传递给它的对象（换句话说，接收者对象）。
        // 代码清单5.19 使用apply构建字母表
        fun alphabet4() = StringBuilder().apply {
            for (letter in 'A'..'Z') {
                append(letter)
            }
            append("\nNow I know the alphabet!4")
        }.toString()

        // 代码清单5.20 使用apply初始化一个TextView
        fun createViewCustomAttributes(context: Context) = TextView(context).apply {
            text = "Sample Text"
            textSize = 20f
            setPadding(10, 0, 0, 0)
        }

        /**
         * 新的 TextView 实例创建后立即被传给了 apply 。在传给 apply lambda 中， TextView 实例变成了（lambda的）接收者，你就可以调用它的方法并设置它的属性。
         * Lambda 执行之后， apply回己经初始化过的接收者实例 它变成了 createViewWithCustomAttributes函数的结果。
         */

        // 代码清单5.21 使用buildString创建字母表
        fun alphabet5() = buildString {
            for (letter in 'A'..'Z') {
                append(letter)
            }
            append("\nNow I know the alphabet!5")
        }
        /*
         * buildString 会负责创建 StringBuilder 调用 toString，buildString 的实参是一个带接收者的 lambda ，接收者就是StringBuilder。
         * buildString 函数优雅地完成了借助StringBuilder创建String的任务。
         */
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent()
            intent.setClass(context, Lambda5Activity::class.java)
            context.startActivity(intent)
        }
    }
}