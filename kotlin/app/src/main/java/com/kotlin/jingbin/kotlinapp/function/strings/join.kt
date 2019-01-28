package com.kotlin.jingbin.kotlinapp.function.strings

import com.kotlin.jingbin.kotlinapp.utils.LogUtil

/**
 * Created by jingbin on 2019/1/28.
 * 3.2.3、消除静态工具类：顶层函数和属性
 */
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


/*
* String: 接受者类型
* this: 接受者对象
* */
fun String.lastChar(): Char = this.get(this.length - 1)