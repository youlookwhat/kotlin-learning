package com.kotlin.jingbin.kotlinapp.function.strings

import com.kotlin.jingbin.kotlinapp.utils.LogUtil

/**
 * Created by jingbin on 2019/1/28.
 * 2.3、消除静态工具类：顶层函数和属性
 */

/**---------------------------------顶层函数---------------------------------------*/

/**函数的目的为：将给定的集合数据，用 , 分割拼接后返回*/
fun <T> joinToStrings(collection: Collection<T>,
                      separator: String = ",",
                      prefix: String = "",
                      postfix: String = ""): String {

    val result = StringBuilder(prefix)

    for ((index, element) in collection.withIndex()) {
        // 不用在第一个元素前添加分隔符
        if (index > 0) {
            result.append(separator)
        }
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

/**-------------------------------顶层属性-----------------------------------------*/

// 声明一个 顶层属性
var opCount = 0

// 改变属性的值
fun performOperation() {
    // 改变属性的值
    opCount++
    // ...
}

// 读取属性的值
fun reportOperationCount() {
    LogUtil.e("Operation performed $opCount times")
}

const val UNIX_LINE_SEPATOR = "\n"
// 等同于 Java
//public static final String UNIX_LINE_SEPATOR = "\n";


/**----------------------------------扩展函数--------------------------------------*/
/**
 * 扩展函数：
 * String: 接受者类型
 * this: 接受者对象
 * */
fun String.lastChar(): Char = this.get(this.length - 1)

fun String.lastChar2(): Char = get(length - 1)

/**
 * 扩展属性：
 * */
val String.lastChar: Char
    get() = get(length - 1)

/**
 * 可变的扩展属性：
 */
var StringBuilder.lastChar2: Char
    // getter 属性
    get() = get(length - 1)
    // setter 属性
    set(value: Char) {
        this.setCharAt(length - 1, value)
    }
