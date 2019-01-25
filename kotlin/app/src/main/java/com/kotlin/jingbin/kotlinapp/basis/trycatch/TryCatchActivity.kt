package com.kotlin.jingbin.kotlinapp.basis.trycatch

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlin.jingbin.kotlinapp.R
import com.kotlin.jingbin.kotlinapp.utils.LogUtil
import java.io.BufferedReader
import java.io.StringReader

/**
 * Kotlin中的异常
 */
class TryCatchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_try_catch)

        // val 不能再赋值，相当于 final
        val percentage = 0

        if (percentage !in 0..100) {
            throw IllegalAccessException("A percentage value must be between 0 and 100: $percentage")
        }
        /**
         * 和所有其他类一样，不必使用 new 关键字来创建异常实例。
         * 和java不同的是，Kotlin中throw结构是一个表达式，能作为另一个表达式的一部分使用：
         */

        val number = 8
        val percentage2 =
                if (number in 0..100) {
                    number
                } else {
                    // throw 是一个表达式
                    throw IllegalAccessException("A percentage value must be between 0 and 100: $percentage")
                }

        val bufferedReader = BufferedReader(StringReader("239"))
        LogUtil.e(readNumber(bufferedReader).toString())
    }

    /**---------------1、try catch 和 finally---------------*/
    // 不必显式地知道这个函数可能抛出的异常
    fun readNumber(reader: BufferedReader): Int? {
        try {
            val line = reader.readLine()
            return Integer.parseInt(line)

            // 异常类型在右边
        } catch (e: NumberFormatException) {
            return null
        } finally {
            reader.close()
        }
    }


    /**---------------2、try 作为表达式---------------*/
    fun readNumber2(reader: BufferedReader) {
        val number = try {
            // 没有任何异常发生时 使用这个值
            Integer.parseInt(reader.readLine())
        } catch (e: NumberFormatException) {
//            return
            // 发生异常时的情况下使用 null
            null
        }
    }


    companion object {
        fun start(context: Context) {
            val intent = Intent()
            intent.setClass(context, TryCatchActivity::class.java)
            context.startActivity(intent)
        }
    }
}
