package com.kotlin.jingbin.kotlinapp.lambda

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlin.jingbin.kotlinapp.R

/**
 * 5.4 使用Java函数式调用接口
 * */
class Lambda4Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lambda)

        title = "使用Java函数式调用接口"

        /*--------------- 5.4.1 把lambda当做参数传递给Java方法-------------*/
    }

    data class Person(val name: String, val age: Int)

    companion object {
        fun start(context: Context) {
            val intent = Intent()
            intent.setClass(context, Lambda4Activity::class.java)
            context.startActivity(intent)
        }
    }
}