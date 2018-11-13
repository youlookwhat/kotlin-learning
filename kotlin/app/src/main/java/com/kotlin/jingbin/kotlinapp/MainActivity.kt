package com.kotlin.jingbin.kotlinapp

import kotlinx.android.synthetic.main.activity_main.*

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // 1.hello world
        println("hello world kotlin!")
        tv_content.setText("hello world kotlin!")

        // 2.函数
        // 网址
        Log.e("sum", sum(1, 1).toString())
        Log.e("sum2", sum2(1, 3).toString())
        logSum(2, 3)
        logSum2(3, 4)
        // 书籍
        Log.e("max", max(1, 2).toString())
        Log.e("max2", max2(10, 2).toString())

    }

    // 求和
    private fun sum(a: Int, b: Int): Int {
        return a + b
    }

    // 将表达式作为函数体、返回值类型自动推断的函数：
    fun sum2(a: Int, b: Int) = a + b

    // 函数返回无意义的值：
    fun logSum(a: Int, b: Int): Unit {
        Log.e("logSum", (a + b).toString())
    }

    // Unit 返回类型可以省略
    fun logSum2(a: Int, b: Int) {
        Log.e("logSum2", (a + b).toString())
    }

    //----------------------------------------

    /**
     *  求最大值
     * if是表达式而不是语句，表达式有值，语句没有。
     * java中所有的控制结构都是语句
     * kotlin中除了循环以外大多数控制结构都是表达式
     */
    private fun max(a: Int, b: Int): Int {
        return if (a > b) a else b
    }

    /**
     * 如果函数体写在花括号中，我们说这个函数有代码块体。
     * 如果直接返回了一个表达式体，他就有表达式体。
     */
    fun max2(a: Int, b: Int): Int = if (a > b) a else b
}
