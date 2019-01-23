package com.kotlin.jingbin.kotlinapp.enumwhen.whencode

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlin.jingbin.kotlinapp.R
import com.kotlin.jingbin.kotlinapp.enumwhen.enumcode.Color
import com.kotlin.jingbin.kotlinapp.utils.LogUtil

/**
 * 1、在 when 结构中使用任意对象
 * 2、不带参数的 when
 * 3、智能转换：合并类型检查和转换
 * 4、重构：用“when”代替“if”
 * 5、代码块作为 “if” 和 “when” 的分支
 */
class WhenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_when)


        LogUtil.e("WhenActivity-eval:", eval(Sum(Sum(Num(1), Num(2)), Num(4))).toString())
        LogUtil.e("WhenActivity-eval2:", eval2(Sum(Sum(Num(1), Num(2)), Num(4))).toString())
        LogUtil.e("WhenActivity-eval3:", eval3(Sum(Sum(Num(1), Num(2)), Num(4))).toString())
        LogUtil.e("WhenActivity-evalWithLogging:", evalWithLogging(Sum(Sum(Num(1), Num(2)), Num(4))).toString())
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
    // 3.1表达式层次结构
    interface Expr

    // 简单的值对象类，只有一个属性value，实现了Expr接口
    class Num(val value: Int) : Expr

    // sum运算的实参可以是任何Expr: Num或者另一个Sum
    class Sum(val left: Expr, val right: Expr) : Expr

    /**
     * 3.2 使用 if 层叠对表达式求值
     * 在 Kotlin 中，如果你检查过一个变量是某种类型，后面就不再需要转换它，可以就把它当作你检查过的类型使用。
     * 事实上编译器为你执行了类型转换，我们把这种行为称为智能转换。
     * */
    fun eval(e: Expr): Int {
        // is - instanceOf
        if (e is Num) {
            // 显示的转换成类型 Num是多余的
            val num = e as Num
            return num.value
        }
        if (e is Sum) {
            // 变量 e 被智能转换了类型
            return eval(e.left) + eval(e.right)
        }
        throw IllegalAccessException("Unknown expression")
    }

    /**---------------4、重构：用“when”代替“if”---------------*/
    /**
     * Kotlin 中没有三元运算符，因为if有返回值
     * 意味着: 可以用表达式语法重写eval函数，去掉return语句和花括号，使用if表达式作为函数体
     */
    // 4.1 使用用返回值的 if 表达式
    fun eval2(e: Expr): Int =
            if (e is Num) {
                e.value
            } else if (e is Sum) {
                eval2(e.right) + eval2(e.left)
            } else {
                throw IllegalAccessException("Unknown expression")
            }

    // 4.2 使用 when 代替 if 层叠
    fun eval3(e: Expr): Int =
            when (e) {
                is Num -> e.value
                is Sum -> eval3(e.right) + eval3(e.left)
                else -> throw IllegalAccessException("Unknown expression")
            }


    /**---------------5、代码块作为 “if” 和 “when” 的分支---------------*/
    /**
     * 一个函数要么具有不是代码块的表达式函数体，
     * 要么具有包含显示return语句的代码块函数体
     */
    // 在分支中含有混合操作的 when
    fun evalWithLogging(e: Expr): Int =
            when (e) {
                is Num -> {
                    LogUtil.e("num: ${e.value}")
                    e.value
                }
                is Sum -> {
                    val left = this.evalWithLogging(e.left)
                    val right = this.evalWithLogging(e.right)
                    LogUtil.e("Sum: $left + $right")
                    // 代码块中最后的表达式就是结果
                    left + right
                }
                else -> throw IllegalAccessException("Unknown expression")
            }


    companion object {
        fun start(context: Context) {
            val intent = Intent()
            intent.setClass(context, WhenActivity::class.java)
            context.startActivity(intent)
        }
    }
}
