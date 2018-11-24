package com.kotlin.jingbin.kotlinapp.website.srart.habit

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlin.jingbin.kotlinapp.R

/**
 * 习惯用法
 * 1、创建 DTOs（POJOs/POCOs）
 */
class HabitUsageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habit_usage)

    }

    /**-----------1、创建 DTOs（POJOs/POCOs）----------*/
    /**
     * 会为 Customer 类提供以下功能：
     * 所有属性的 getters （对于 var 定义的还有 setters）
     * equals()
     * hashCode()
     * toString()
     * copy()
     * 所有属性的 component1()、 component2()……等等
     * */
    data class Customer(val name: String, val email: String)

    /**-----------2、函数的默认参数----------*/
    fun foo(a: Int = 0, b: String = "") {}

    /**-----------3、过滤list----------*/
//    val positives = list.filter { x -> x > 0 }

//    val positives = list.filter{it>0}

    /**-----------4、字符串内插---------*/
//    println("Name $name")

    /**-----------5、类型判断---------*/
//    when(x) {
//        is Foo //-> ...
//        is Bar //-> ...
//        else   //-> ...
//    }
}

