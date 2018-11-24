package com.kotlin.jingbin.kotlinapp.website.srart.basic

/**
 * @author jingbin
 * @data 2018/11/21
 * @description
 * 1、使用可空值及 null 检测
 * 2、使用类型检测及自动类型转换
 */
class Basic {
    /**当某个变量可以为null的时候，必须在声明处添加 ？ 来标识改引用可为空*/
    fun parseInt(str: String): Int? {
        // ---
        return null
    }

    // 使用返回可空值的函数：
    fun printProduct(arg1: String, arg2: String) {
        val x = parseInt(arg1)
        val y = parseInt(arg2)

        // 直接使用  x * y 会导致编译错误，因为他们可能为null
        if (x != null && y != null) {
            // // 在空检测后，x 与 y 会自动转换为非空值（non-nullable）
            println(x * y)
        } else {
            println("either '$x' or '$y' is not a number")
        }
    }

    /**--------------------------------------------------*/
    /**is 运算符检测一个表达式是否某类型的一个实例。 如果一个不可变的局部变量或属性已经判断出为某类型，那么检测后的分支中可以直接当作该类型使用，无需显式转换：*/
    fun getStringLength(obj: Any): Int? {
        if (obj is String) {
            return obj.length
        }
        return null
    }

}