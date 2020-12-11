package com.kotlin.jingbin.kotlinapp.lambdahigher

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlin.jingbin.kotlinapp.R

/**
 * 8.1 声明高阶函数
 * */
class LambdaHigherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lambda_higher)
        title = "8.1 声明高阶函数"

        // 高阶函数就是以另一个函数作为参数或者返回值的函数。
        val list = listOf(0, 1, 2, 3)
        println(list.filter { it > 0 })


        /**-------------------- 8.1.1 函数类型 ----------------------*/
        // Kotlin的类型推导
        val sum = { x: Int, y: Int -> x + y }
        val action = { println(42) }

        // 这些变量的显示类型声明是什么样的？
        // 有两个Int型参数和Int型返回值的函数
        val sum2: (Int, Int) -> Int = { x, y -> x + y }
        // 没有参数和返回值的函数
        val action2: () -> Unit = { println(42) }

        /*
        * (Int, String) -> Unit
        * 参数类型           返回类型
        * 声明函数类型，需要将函数参数类型防在括号中，紧接着是一个箭头和函数的返回类型
        */
        // 标记函数类型返回值为可空类型：
        var canReturnNull: (Int, Int) -> Int? = { x, y -> null }
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent()
            intent.setClass(context, LambdaHigherActivity::class.java)
            context.startActivity(intent)
        }
    }
}
