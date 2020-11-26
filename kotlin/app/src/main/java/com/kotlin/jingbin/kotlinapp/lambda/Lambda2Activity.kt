package com.kotlin.jingbin.kotlinapp.lambda

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlin.jingbin.kotlinapp.R
import kotlinx.android.synthetic.main.activity_lambda.*
import java.util.*

/**
 * 5.2 集合的函数式API
 * */
class Lambda2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lambda)

        title = "集合的函数式API"

        /*--------------- 5.2.1 基础：filter 和 map-------------*/
        // filter函数遍历合集并选出应用给定lambda后会返回true的那些元素：
        val list = listOf(1, 2, 3, 4)
        // 只有偶数留了下来
        println(list.filter { it % 2 == 0 })// [2,4]

        // 如果你想留下那些超过30岁的人，可以用filter
        val people = listOf(Person("jingbin", 12), Person("jinbeen", 23))
        println(people.filter { it.age > 20 })//  [Person(name=jinbeen, age=23)]

        /**
         * filter可以移除不想要的元素但不会改变这些元素。map用于元素的变换。
         * map 函数对集合中的每一个元素应用给定的函数并把结果收集到一个新集合。
         */
        // 把数字列表变换成他们平方的列表
        val list2 = listOf(1, 2, 3, 4)
        println(list2.map { it * it })// [1, 4, 9, 16]

        // 打印姓名列表，而不是人的完整信息
        val people2 = listOf(Person("jingbin", 12), Person("jinbeen", 23), Person("haha", 23))
        println(people2.map { it.name })// [jingbin, jinbeen, haha]
        // 使用 成员引用 重写
        println(people2.map(Person::name))

        // 打印出年龄超过30岁人的名字
        println(people2.filter { it.age > 20 }.map(Person::name))

        // 分组中所有年龄最大的人的名字 （集合中有100个人就会执行100遍）
        println(people2.filter { it.age == people2.maxBy(Person::age)?.age })// [Person(name=jinbeen, age=23), Person(name=haha, age=23)]
        // 只计算一次最大年龄
        val maxAge = people2.maxBy(Person::age)?.age
        println(people2.filter { it.age == maxAge })

        // 注意不要重复计算！
        val map = mapOf(0 to "zero", 1 to "one")
        println(map.mapValues { it.value.toUpperCase() })

        /**键和值分别由各自的函数来处理。filterKeys和mapKeys过滤和变换map的键，而另外的filterValues和mapValues过滤和变换对应的值*/


        /*--------------- 5.2.2 "all" "any" "count"和"find"：对集合应用判断式-------------*/
        /**
         * all和any 检查集合中的所有元素是否都符合某个条件。
         * count 检查有多少元素满足判断式
         * find  返回第一个符合条件的元素
         */
        // 1.检查一个人是否还没到28岁
        val canBeInClub27 = { p: Person -> p.age <= 20 }
        // 2.如果对是否所有元素都满足判断式，应使用all函数
        val people3 = listOf(Person("jingbin", 11), Person("jinbeen", 22))
        println(people3.all(canBeInClub27))// false
        // 3.检查集合中是否至少存在一个匹配元素，那就用any
        println(people3.any(canBeInClub27))// true
        // 4.!all【不是所有】可以用any加上这个条件的取反来替换。最好使用any+条件取反
        val listOf = listOf(1, 2, 3)
        // 不全部==3
        println(!listOf.all { it == 3 })
        // 有一个!=3
        println(listOf.any { it != 3 })

        // count 有多少个元素满足了判断式
        val people4 = listOf(Person("jingbin", 11), Person("jinbeen", 22))
        println(people4.count(canBeInClub27))// 1
        // 注意正确的使用函数！count 和 size，以下会创建一个不必要的中间集合！应使用count！
        println(people4.filter(canBeInClub27).size)

        // find 找到第一个满足判断式的元素，如果没有找到就返回null
        val people5 = listOf(Person("jingbin", 11), Person("jinbeen", 15))
        println(people5.find(canBeInClub27))// Person(name=jingbin, age=11)
        println(people5.firstOrNull(canBeInClub27)) // Person(name=jingbin, age=11)


        /*--------------- 5.2.3 groupBy: 把列表转换成分组的map-------------*/
        // 将相同年龄的人放在一组
        val people6 = listOf(Person("jingbin", 11), Person("jinbeen", 15), Person("haha", 15))
        println(people6.groupBy { it.age == 15 })// {false=[Person(name=jingbin, age=11)], true=[Person(name=jinbeen, age=15), Person(name=haha, age=15)]}
        println(people6.groupBy { it.age })// {11=[Person(name=jingbin, age=11)], 15=[Person(name=jinbeen, age=15), Person(name=haha, age=15)]}

        // 使用成员引用把字符串按照首字母分组：
        val listOf1 = listOf("a", "b", "c", "cd")
        // 注意
        // 1、成员引用在()里，不在{}里
        // 2、这里first并不是String类的成员，而是一个扩展，可以把它当做成员引用访问
        println(listOf1.groupBy(String::first)) // {a=[a], b=[b], c=[c, cd]}

        /*--------------- 5.2.4 flatMap 和 flatten：处理嵌套集合中的元素-------------*/
        data class Book(val title: String, val authors: List<String>)
        // 统计图书馆中的所有作者的set
        
    }

    //    data class Book(val title: String, val authors: List<String>)
    data class Person(val name: String, val age: Int)


    companion object {
        fun start(context: Context) {
            val intent = Intent()
            intent.setClass(context, Lambda2Activity::class.java)
            context.startActivity(intent)
        }
    }
}