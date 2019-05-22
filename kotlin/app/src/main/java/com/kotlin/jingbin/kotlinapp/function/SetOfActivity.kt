package com.kotlin.jingbin.kotlinapp.function

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import com.kotlin.jingbin.kotlinapp.R
import com.kotlin.jingbin.kotlinapp.function.strings.joinToStrings
import com.kotlin.jingbin.kotlinapp.function.strings.lastChar
import com.kotlin.jingbin.kotlinapp.function.strings.lastChar2
import com.kotlin.jingbin.kotlinapp.utils.LogUtil
import kotlin.text.StringBuilder
import com.kotlin.jingbin.kotlinapp.function.strings.lastChar as last

/**
 * 3.1 在Kotlin中创建集合
 * 3.2 让函数更好的调用
 * 3.3 给别人的类添加方法：扩展函数和属性
 * 3.4 处理集合: 可变参数、中辍调用和库的支持
 * */
class SetOfActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_of)
        setTitle("在Kotlin中创建集合")


        /**---------------1、在Kotlin中创建集合---------------*/
        // 支持数字创建
        val set = hashSetOf(1, 7, 53)

        // 用类似的方法创建一个 list 或 map:
        val list = arrayListOf(1, 7, 53)
        val map = hashMapOf(1 to "one", 7 to "seven, 53 to fifty-three")
        /**注意： to 并不是一个特殊的结构，而是一个普通函数。后面讨论。*/

        // javaClass 相当于 java 中的getClass()
        LogUtil.e(set.javaClass.toString())
        LogUtil.e(list.javaClass.toString())
        LogUtil.e(map.javaClass.toString())
        /**
         * class java.util.HashSet
         * class java.util.ArrayList
         * class java.util.HashMap
         * Kotlin 没有采用它自己的集合类，而是采用的标准的Java集合类。
         */

        // 获取最后一个元素
        val string = listOf("first", "second", "fourteenth")
        LogUtil.e(string.last())

        // 取最大值
        val numbers = setOf(1, 14, 2)
        LogUtil.e(numbers.max().toString())


        // 2.让函数更好的调用  测试
        val list2 = listOf(1, 2, 3)
        LogUtil.e(joinToString(list2, ";", "(", ")"))


        /**---------------2、让函数更好的调用---------------*/
        /*---------------2.1、命名参数---------------*/
        joinToString(collection = list2, separator = "", prefix = "", postfix = ".")

        /*---------------2.2、默认参数值  解决 [重载] 重复问题---------------*/
//        fun <T> joinToString(
//                collection: Collection<T>,
//                separator: String = ",",
//                prefix: String = "",
//                postfix: String = ""
//        ): String {
//
//        }


        LogUtil.e(joinToString(list2))
        LogUtil.e(joinToString(list2, ";"))
        LogUtil.e(joinToString(list2, ",", ",", ","))
        LogUtil.e(joinToString(list2, postfix = ",", prefix = "#"))

        /*---------------2.3、消除静态工具类：顶层函数和属性---------------*/
        /*
         *  顶层函数:
         *  声明joinToString()作为顶层函数
         *  新建 strings 包，里面直接放置 joinToStrings
         */
        joinToStrings(list2, ",", ",", ",")


        /**---------------3、给别人的类添加方法：扩展函数和属性---------------*/
        LogUtil.e("Kotlin".lastChar())

        /*---------------3.1、导入和扩展函数---------------*/
        // import com.kotlin.jingbin.kotlinapp.function.strings.lastChar
        val lastChar = "Kotlin".lastChar()
        // 可以用关键字as 来修改导入的类或者函数名称:  [可以用来解决命名冲突]
        val last = "Kotlin".last()


        /*---------------3.2、从Java中调用扩展函数---------------*/
        // java    -->   StringUtil.lastChar("Kotlin");
        // java    -->   lastChar("Kotlin");
        // kotlin  -->   "Kotlin".lastChar()


        /*---------------3.3、作为扩展函数的工具函数---------------*/
        // joinToString 函数的终极版本，和kotlin标准库中看到的一模一样
        // 为Collection<T> 声明一个扩展函数
        fun <T> Collection<T>.joinToString(separator: String = ",", prefix: String = "", postfix: String = ""): String {
            val result = StringBuilder(prefix)

            for ((index, element) in this.withIndex()) {
                // 不用在第一个元素前添加分隔符
                if (index > 0) {
                    result.append(separator)
                }
                result.append(element)
            }
            result.append(postfix)
            return result.toString()
        }
        LogUtil.e(list2.joinToString(";", "((", "))"))

        fun Collection<String>.join(separator: String = ",", prefix: String = "", postfix: String = "") = joinToString(separator, prefix, postfix)

        LogUtil.e(listOf("one", "two", "eight").join(" "))

        /*---------------3.4、不可重写的扩展函数---------------*/
        // 在Kotlin中，重写成员函数式很平常的一件事情。但是，不能重写扩展函数。
        fun View.showOff() = LogUtil.e("I'm a view")

        fun Button.showOff() = LogUtil.e("I'm a Button")

        val view: View = Button(this)
        LogUtil.e(view.showOff()) //  I'm a view
        // 扩展函数并不存在重写，因为Kotlin会把它们当做作静态函数对待

        /*---------------3.5、扩展属性  join.kt  ---------------*/
        // val kotlin.String.lastChar: Char
        //     get() = get(length - 1)
        "Kotlin".lastChar
        val builder = StringBuilder("Kotlin?")
        builder.lastChar2 = '!'
        LogUtil.e(builder) // Kotlin!


        /**---------------4、处理集合: 可变参数、中辍调用和库的支持---------------*/


    }

    /**---------------2、让函数更好的调用---------------*/
    /**
     * val list = listOf(1,2,3)
     * println(list)    --- 触发了 toString()的调用
     * 默认输出  [1,2,3]
     * 想要效果  (1;2;3)
     *
     * joinToString() 的基本实现
     * 通过在元素中间添加分割符号，从直接重写实现函数开始，然后再过渡到Kotlin更惯用的方法来重写。
     */
    /**
     * @param collection 集合
     * @param separator 分割符
     * @param prefix 前缀
     * @param postfix 后缀
     * 有默认值的参数
     */
    fun <T> joinToString(collection: Collection<T>,
                         separator: String = ",",
                         prefix: String = "",
                         postfix: String = ""
    ): String {

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


    companion object {
        fun start(context: Context) {
            val intent = Intent()
            intent.setClass(context, SetOfActivity::class.java)
            context.startActivity(intent)
        }
    }
}
