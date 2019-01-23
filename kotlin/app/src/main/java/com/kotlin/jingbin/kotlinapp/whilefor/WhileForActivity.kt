package com.kotlin.jingbin.kotlinapp.whilefor

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlin.jingbin.kotlinapp.R
import com.kotlin.jingbin.kotlinapp.utils.LogUtil
import java.util.*

class WhileForActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_while_for)

        // 2、迭代数字：区间和数列
        for (i in 1..100) {
//            LogUtil.e(fizzBuzz(i))
        }
        // 倒序 只计偶数 [使用 until 函数可以标识：不包含指定结束值的半闭合区间]
        for (i in 100 downTo 0 step 2) {
            LogUtil.e(fizzBuzz(i))
        }

        /**---------------3、迭代map---------------*/
        // 使用 TreeMap 让键排序
        val binaryReps = TreeMap<Char, String>()
        // 创建字符区间 包括 F
        for (c in 'A'..'F') {
            // 把 ASCII 码转换成二进制
            val binaryString = Integer.toBinaryString(c.toInt())
            binaryReps[c] = binaryString
        }
        // 迭代 map ，把键和值赋值给两个变量
        for ((letter, binary) in binaryReps) {
            LogUtil.e("$letter = $binary")
        }

        // 迭代集合时 使用下标
        val list = arrayListOf("10", "11", "1001")
        for ((index, element) in list.withIndex()) {
            LogUtil.e("$index = $element")
        }
    }

    /**---------------1、“while” 循环---------------*/
    /**
     * Kotlin 有 while 循环和 do-while 循环，他们的语法和Java中相应的循环没有什么区别
     */
    /*
    while(condition) {
        /*...*/
    }*/

    /*
    do {

    }while(condition){}
    */

    /**---------------2、迭代数字：区间和数列---------------*/
    /**
     * 区间：区间本质上就是两个值之间的间隔，这两个值通常是数字：一个起始值，一个结束值。
     * 使用 .. 运算符来表示区间
     * 数列：你能用整数区间做的最基本的事情就是循环迭代其中所有的值。
     * 如果你能迭代区间中所有的值，这样的区间被称作数列。
     * */
    val oneToTen = 1..10

    // 使用 when 实现 Fizz-Buzz 游戏
    fun fizzBuzz(i: Int) = when {
        i % 15 == 0 -> "FizzBuzz"
        i % 3 == 0 -> "Fizz"
        i % 5 == 0 -> "Buzz"
        else -> "$i"
    }


    companion object {
        fun start(context: Context) {
            val intent = Intent()
            intent.setClass(context, WhileForActivity::class.java)
            context.startActivity(intent)
        }
    }

}
