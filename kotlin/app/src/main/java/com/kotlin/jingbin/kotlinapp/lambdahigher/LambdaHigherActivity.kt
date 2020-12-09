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

    }

    companion object {
        fun start(context: Context) {
            val intent = Intent()
            intent.setClass(context, LambdaHigherActivity::class.java)
            context.startActivity(intent)
        }
    }
}
