package com.kotlin.jingbin.kotlinapp.operator

import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlin.jingbin.kotlinapp.R

/**
 * 7.3 集合与区间的约定
 * */
class Operator3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_operator1)

        /**-------------------- 7.3.1 通过下标来访问元素 get 和 set ----------------------*/
        // kotlin中可以使用类似java中数组中的方式来访问map中的元素---使用方括号
//        val value = map[key]
        // 也可以用同样的运算符来改变一个可变map的元素
//        mutableMap[key] = newValue

        // 使用方括号来引用点的坐标：p[0]访问x坐标，p[1]访问y坐标。
        // 代码清单7.9 实现 get 约定
        // 定义一个名为 get 的运算符函数
        operator fun Point.get(index: Int): Int {
            // 根据给出的index返回对应的坐标
            return when (index) {
                0 -> x
                1 -> y
                else -> throw IndexOutOfBoundsException("Invalid coordinate $index")
            }
        }

        val point = Point(1, 2)
        println(point[1])// 2



    }

    companion object {
        fun start(context: Context) {
            val intent = Intent()
            intent.setClass(context, Operator3Activity::class.java)
            context.startActivity(intent)
        }
    }
}
