package com.kotlin.jingbin.kotlinapp.typesystom

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlin.jingbin.kotlinapp.R

/**
 * 6.2 基本数据类型和其他基本类型
 */
class TypeSystem2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_type_system)

        /**-------------------- 6.2.1 基本数据类型：Int、Boolean及其他 ----------------------*/

        // Kotlin并不区分基本数据类型和包装类型，使用的永远是同一类型：(如:Int)
        val i: Int = 1
        val listOf: List<Int> = listOf(1, 2, 3)

        fun showProgress(progress: Int) {
            val coerceIn = progress.coerceIn(0, 100)
            println("we are ${coerceIn}% done!")
        }
        // 只在泛型类的时候会被编译成Integer，如集合类，其他是int
        // 对应到Java基本数据类型的类型完整列表如下：
        /*
        * 整数类型：Byte、Short、Int、Long
        * 浮点型类型：Float、Double
        * 字符类型：Char
        * 布尔类型：Boolean
        */

        /**-------------------- 6.2.2 可空的基本数据类型：Int?、Boolean? 及其他 ----------------------*/
        data class Person(val name: String, val age: Int? = null) {
            fun isOlderThan(other: Person): Boolean? {
                if (age == null || other.age == null) {
                    return null
                }
                return age > other.age
            }
        }

        /**-------------------- 6.2.3 数字转换 ----------------------*/
        // Kotlin和Java之间一条重要的区别就是处理数字转换的方式。

        val i2 = 1
        // 必须显示的转换
        val l: Long = i2.toLong()

        println(i2.toLong() in listOf(1L, 2L, 3L))

        /*
        * 基本数据类型字面值
        * 使用后缀 L 表示Long  字面值：123L
        * 标准浮点数表示 Double  字面值：0.12 2.0 1.2e10
        * 后缀 F 表示Float类型 字面值：123.4f、.456f
        * 使用前缀 0x或0X 表示十六进制字面值：0xCAFEBABE 或者 0xbdcL
        * 使用前缀 0b或0B 表示二进制字面值：0b000000101
        *
        * */

        // 初始化一个类型已知和变量时，或者把字面值作为实参传给函数时，必要的转换会自动的发生。
        fun foo(l: Long) = println(l)
        foo(42)

        val b: Byte = 1
        val l2 = b + 1L


        /**-------------------- 6.2.4 Any 和 Any? 根类型 ----------------------*/
        /*
        * 和 Object作为Java类层级结构的根差不多，Any类型是Kotlin所有非空类型的超类型(非空类型的根)。
        */
        // Any是引用类型，所以值42会被装箱
        val answer: Any = 42


        /**-------------------- 6.2.5 Unit类型 Kotlin的 void ----------------------*/

        //  Kotlin中的Unit类型完成了Java中的void一样的功能。当函数没什么有意义的结果返回时，他可以用作函数的返回类型
        fun f(): Unit {}

        // 显式的Unit声明被省略了
        fun f2() {}


        class NoResultProcess : Processor2<Unit> {
            // 返回Unit，但可以省略类型说明
            override fun process(): Unit {
                // 这里不需要显示的return
//                return Unit
            }
        }

        /**-------------------- 6.2.6 Nothing类型: 这个函数永不返回 ----------------------*/
        // 对某些 Kotlin 函数来说，＂返回类型”的概念没有任何意义，因为它们从来不会成功地结束。
        fun fail(message: String): Nothing {
            throw IllegalStateException(message)
        }
        // 注意 返回Nothing的函数可以放在Elvis运算符的右边来做先决条件检查：
//        val address: String? = null
        val address: String? = "wuhan"
        val s = address ?: fail("No address")


    }

    interface Processor2<T> {
        fun process(): T
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent()
            intent.setClass(context, TypeSystem2Activity::class.java)
            context.startActivity(intent)
        }
    }
}
