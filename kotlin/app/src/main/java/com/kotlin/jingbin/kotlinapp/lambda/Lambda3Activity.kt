package com.kotlin.jingbin.kotlinapp.lambda

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlin.jingbin.kotlinapp.R
import java.io.File

/**
 * 5.3 惰性集合操作：序列
 * */
class Lambda3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lambda)

        title = "惰性集合操作：序列"

        // 很多时候使用函数会创建一个中间集合，有损性能。序列给了一个选择，避免创建中间对象。
        val people = listOf(Person("jingbin", 12), Person("jinbeen", 23))
        // 下面的链式调用会创建两个列表，一个保存filter 一个保存map
        println(people.map(Person::name).filter { it.startsWith("j") })// [jingbin, jinbeen]

        // 为提高效率使用序列，而不是直接使用集合：
        val list = people.asSequence()// 把初始集合转换成序列
                .map(Person::name)// 序列支持和集合一样的api
                .filter { it.startsWith("j") }
                .toList()// 把结果序列转换回列表
        println(list)// [jingbin, jinbeen]

        /**
         * Kotlin惰性集合操作的入口就是Sequence接口。
         * 这个接口表示的就是一个可以逐个列举元素的元素序列。Sequence值提供一个方法，iterator，用来从序列中获取值。
         * 通常，需要对一个大型集合执行链式操作时要使用序列。
         */

        /*--------------- 5.3.1 执行序列操作：中间和末端操作-------------*/
        // 一次中间操作返回的是另一个序列，这个新序列知道如何变换原始序列中的元素。
        // 一次末端操作返回的是一个结果，这个结果可能是集合，元素、数字，或者其他从初始集合的变换序列中获取的任意对象。
        // 中间操作:map{}、filter{}
        // 末端操作:toList()

        // 中间操作始终都是惰性的！ 一行的话使用 ;
        listOf(1, 2, 3, 4).asSequence().map { println("map($it) ");it * it }
                .filter {
                    println("filter($it) ")
                    it % 2 == 0
                }
        // 以上不会被调用，只有在获取结果的时候会被调用
        listOf(1, 2, 3, 4).asSequence().map { println("map($it) ");it * it }
                .filter {
                    println("filter($it) ")
                    it % 2 == 0
                }.toList()
        // 处理完第一个元素，然后完成第二个元素的处理。这样意味着有时候部分元素不需要变换，就可以拿到拿到结果。
        // 结果:  map(1) filter(1) map(2) filter(4) map(3) filter(9) map(4) filter(16)

        // 找到平方后的第一个大于3的数。只会执行到2 （有时候部分元素不需要变换，就可以拿到拿到结果。）
        println(listOf(1, 2, 3, 4).asSequence().map { it * it }.find { it > 3 })//4

        // 在集合上执行操作的顺序也会影响性能。
        // 假设有一个人的集合，想要打印集合中那些长度小于某个限制的人名。
        val people2 = listOf(Person("jingbin", 12), Person("jin", 23), Person("jinbeen", 23), Person("Bob", 21))
        // 先map再filter  先把所有名字找出再筛选
        println(people2.asSequence().map(Person::name).filter { it.length < 4 }.toList())
        // 先filter再map  先筛选再找出名字，显然这个好
        println(people2.asSequence().filter { it.name.length < 4 }.map(Person::name).toList())


        /*--------------- 5.3.2 创建序列-------------*/
        // generateSequence 函数，给定序列中的前一个元素，这个函数会计算出下一个元素。
        // 代码清单5.12 生成并使用自然数序列
        val generateSequence = generateSequence(0) { it + 1 }
        val numTo100 = generateSequence.takeWhile { it <= 100 }
        // 当获取结果 sum 时，所有被推迟的操作都被执行
        println(numTo100.sum())// 5050

        // 代码清单5.13 创建并使用父目录的序列。  查询文件是否放在隐藏目录中
        // any 至少有一个元素匹配给定谓词
        // find 找到第一个符合条件的情况
        fun File.isInsideHiddenDirectory() =
//                generateSequence(this) { it.parentFile }.any { it.isHidden }
                generateSequence(this) { it.parentFile }.find { it.isHidden }

        val file = File("/users/svtk/.hiddenDir/a.txt")
        println(file.isInsideHiddenDirectory())
    }

    data class Person(val name: String, val age: Int)

    companion object {
        fun start(context: Context) {
            val intent = Intent()
            intent.setClass(context, Lambda3Activity::class.java)
            context.startActivity(intent)
        }
    }
}