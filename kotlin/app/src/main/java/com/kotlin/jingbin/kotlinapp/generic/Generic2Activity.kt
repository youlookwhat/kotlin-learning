package com.kotlin.jingbin.kotlinapp.generic

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlin.jingbin.kotlinapp.R

/**
 * 9.2 运行时的泛型：擦除和实化类型参数
 */
class Generic2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generic1)

        title = "9.1 运行时的泛型：擦除和实化类型参数"

        /**-------------------- 9.2.1 运行时的泛型：类型检查和转换 ----------------------*/
        // 和Java一样，Kotlin的泛型在运行时也被擦除了。
        val list1: List<String> = listOf("a", "b")
        val list2: List<Int> = listOf(1, 2, 3)

        /*
        * ["a","b"]  -- list1
        * [1,2,3]    -- list2
        * 在运行时，你不知道list1和list2是否声明成字符串或者整数列表。它们每个都只是List
        */
        // 一般而言，在 is 检查中不可能使用类型实参中的类型。下面这样的代码不会编译：
//        if(value is List<String>){...}

        // 可以使用特殊的 星号投影 语法来做这种检查：
//        if (value is List<*>){...}

        // 代码清单9.5 对泛型类型做类型转换
        fun printSum(c: Collection<*>) {
            // 这里会有警告。Unchecked cast:List<*> to List<Int>
            val intList = c as? List<Int> ?: throw IllegalArgumentException("List is expected")
            println(intList.sum())
        }
        // 一切都符合预期
        printSum(listOf(1, 2, 3))// 6
        // Set 不是列表，所以抛出了异常
        printSum(setOf(1, 2, 3))// IllegalArgumentException: List is expected
        // 类型转换成功，但后面抛出了另外的异常，as 通过了，在计算时抛出
        printSum(listOf("a", "b", "c"))// ClassCastException:String cannot be cast to Number

        // 代码清单9.6 对已知类型实参做类型转换
        fun printSum2(c: Collection<Int>) {
            // 这次的检查是合法的
            if (c is List<Int>) {
                println(c.sum())
            }
        }
        printSum2(listOf(1, 2, 3))// 6


        /**-------------------- 9.2.2 声明带实化类型参数的函数 ----------------------*/

    }

    companion object {
        fun start(context: Context) {
            val intent = Intent()
            intent.setClass(context, Generic2Activity::class.java)
            context.startActivity(intent)
        }

    }


}
