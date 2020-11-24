package com.kotlin.jingbin.kotlinapp.lambda

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlin.jingbin.kotlinapp.R
import kotlinx.android.synthetic.main.activity_lambda.*

/**
 * 5.2 集合的函数式API
 * */
class Lambda2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lambda)

        title = "集合的函数式API"

        /*--------------- 5.2.1 基础：filter 和 map-------------*/
        // filter函数遍历合集并选出应用给定lambda后会返回true的那些元素：
        val list = listOf(1, 2, 3, 4)
        // 只有偶数留了下来
        println(list.filter { it % 2 == 0 })// [2,4]

        // 如果你想留下那些超过30岁的人，可以用filter
        val people = listOf(Person("jingbin", 12), Person("jinbeen", 23))
        println(people.filter { it.age > 20 })//  [Person(name=jinbeen, age=23)]

        /**
         * filter可以移除不想要的元素但不会改变这些元素。map用于元素的变换。
         * map 函数对集合中的每一个元素应用给定的函数并把结果收集到一个新集合。
         */
        // 把数字列表变换成他们平方的列表
        val list2 = listOf(1, 2, 3, 4)
        println(list2.map { it * it })// [1, 4, 9, 16]

        // 打印姓名列表，而不是人的完整信息
        val people2 = listOf(Person("jingbin", 12), Person("jinbeen", 23), Person("haha", 23))
        println(people2.map { it.name })// [jingbin, jinbeen, haha]
        // 使用 成员引用 重写
        println(people2.map(Person::name))

        // 打印出年龄超过30岁人的名字
        println(people2.filter { it.age > 20 }.map(Person::name))

        // 分组中所有年龄最大的人的名字
        println(people2.filter { it.age == people2.maxBy(Person::age)?.age })// [Person(name=jinbeen, age=23), Person(name=haha, age=23)]
        // 集合中有100个人就会执行100遍
        val maxAge = people2.maxBy(Person::age)?.age
        println(people2.filter { it.age == maxAge })


    }

    data class Person(val name: String, val age: Int)


    companion object {
        fun start(context: Context) {
            val intent = Intent()
            intent.setClass(context, Lambda2Activity::class.java)
            context.startActivity(intent)
        }
    }
}