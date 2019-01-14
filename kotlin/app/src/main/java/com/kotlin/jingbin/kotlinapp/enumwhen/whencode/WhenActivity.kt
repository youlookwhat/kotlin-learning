package com.kotlin.jingbin.kotlinapp.enumwhen.whencode

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.kotlin.jingbin.kotlinapp.R
import com.kotlin.jingbin.kotlinapp.enumwhen.enumcode.Color

/**
 * 1、在 when 结构中使用任意对象
 * 2、不带参数的 when
 * 3、智能转换：合并类型检查和转换
 */
class WhenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_when)

    }

    /**---------------1、在 when 结构中使用任意对象---------------*/
    fun mix(c1: Color, c2: Color) = {
        // when 表达式的实参可以是任何对象，它被检查是否与分支条件对等
        when (setOf(c1, c2)) {
            setOf(Color.RED, Color.YELLOW) -> Color.ORANGE
            setOf(Color.BLUE, Color.YELLOW) -> Color.GREEN
            setOf(Color.BLUE, Color.VIOLET) -> Color.INDIGO
        // 如果没有任何其他分支匹配这里就会执行
            else -> throw Exception("Dirty color")
        }
    }

    /**---------------2、不带参数的 when---------------*/
    fun minOptimized(c1: Color, c2: Color) = {
        // 没有实参传给 when
        when {
            (c1 == Color.RED && c2 == Color.YELLOW) || (c2 == Color.RED && c1 == Color.YELLOW) -> Color.ORANGE
            (c1 == Color.BLUE && c2 == Color.YELLOW) || (c2 == Color.BLUE && c1 == Color.YELLOW) -> Color.GREEN
            (c1 == Color.BLUE && c2 == Color.VIOLET) || (c2 == Color.BLUE && c1 == Color.VIOLET) -> Color.INDIGO

            else -> throw Exception("Dirty color")
        }
    }

    /**---------------3、智能转换：合并类型检查和转换---------------*/
    interface Expr

    // 简单的值对象类，只有一个属性value，实现了Expr接口
    class Num(val value: Int) : Expr

    // sum运算的实参可以是任何Expr: Num或者另一个Sum
    class Sum(val left: Expr, val right: Expr) : Expr
}
