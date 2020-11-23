package com.kotlin.jingbin.kotlinapp.lambda

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlin.jingbin.kotlinapp.R
import kotlinx.android.synthetic.main.activity_lambda.*

/**
 * 5.1 Lambda表达式和成员引用
 * */
class LambdaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lambda)

        title = "Lambda表达式和成员引用"

        /*--------------- 5.1.1 Lambda简介：作为函数参数的代码块-------------*/
        // 代码清单5.1 用匿名内部类实现监听器 java
        /* Java */
//        tv_click.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick (View view) {
//                ／＊点击后执行的动作＊／
//            }
//        }

        // 代码清单5.2 用lambda实现监听器 kotlin
        tv_click.setOnClickListener { }

        /*--------------- 5.1.2 Lambda和集合-------------*/
        data class Person(val name:String,val age:Int)
        // 代码清单5.3 手动在集合中搜索
        fun findTheOldest(people: List<Person>){
            var maxAge=0
            var theOldest:Person?=null
            for (person in people){
            }
        }

    }

    companion object {
        fun start(context: Context) {
            val intent = Intent()
            intent.setClass(context, LambdaActivity::class.java)
            context.startActivity(intent)
        }
    }
}