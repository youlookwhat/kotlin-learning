package com.kotlin.jingbin.kotlinapp.enumwhen.enumcode

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.kotlin.jingbin.kotlinapp.R
import com.kotlin.jingbin.kotlinapp.enumwhen.enumcode.Color.*
import com.kotlin.jingbin.kotlinapp.utils.LogUtil

class EnumWhenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enum_when)

        // 带属性的枚举类
        LogUtil.e(BLUE.rgb())

        println(getMnemonic(RED))
    }

    /**
     * 使用when处理枚举类:
     * 直接返回一个“when"表达式
     */
    fun getMnemonic(color: Color) = {
        when (color) {
            RED -> "Richard"
            ORANGE -> "Of"
            YELLOW -> "Haha"
            // 合并多个选项
            BLUE, GREEN -> "望穿"
            VIOLET, INDIGO -> "秋水"
        }
    }
}
