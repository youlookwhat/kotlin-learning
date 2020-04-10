package com.kotlin.jingbin.kotlinapp.website.classesandobjects

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlin.jingbin.kotlinapp.R
import java.util.*
import kotlin.collections.HashSet

/**
 * 集合
 * https://www.kotlincn.net/docs/reference/constructing-collections.html
 */
class CollectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person)

        // 1.list 的初始化函数
        val doubled = List(3, { it * 2 })  // 如果你想操作这个集合，应使用 MutableList
        println(doubled)

        // 2.具体类型构造函数
        val linkedList = LinkedList<String>(listOf("one", "two", "three"))
        val presizedSet = HashSet<Int>(32)

        // 3.复制
        val sourceList = mutableListOf(1, 2, 3)
        val copyList = sourceList.toMutableList()
        val readOnlyCopyList = sourceList.toList()
        sourceList.add(4)
        // 3
        println("Copy size: ${copyList.size}")

        //readOnlyCopyList.add(4)             // 编译异常
        // 3
        println("Read-only copy size: ${readOnlyCopyList.size}")

        // 4.这些函数还可用于将集合转换为其他类型，例如根据 List 构建 Set，反之亦然。
        val sourceList2 = mutableListOf(1, 2, 3)
        val copySet = sourceList2.toMutableSet()
        copySet.add(3)
        copySet.add(4)
        println(copySet)

        // 5 调用其他集合的函数
        // 5.1 过滤列表会创建与过滤器匹配的新元素列表：
        val numbers = listOf("one", "two", "three", "four")
        val longerThan3 = numbers.filter { it.length > 3 }
        println(longerThan3)

        // 5.2 映射生成转换结果列表：
        val numbers2 = setOf(1, 2, 3)
        println(numbers2.map { it * 3 })
        println(numbers2.mapIndexed { idx, value -> value * idx })

        // 5.3 关联生成 Map:
        val numbers3 = listOf("one", "two", "three", "four")
        println(numbers3.associateWith { it.length })
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent()
            intent.setClass(context, CollectionActivity::class.java)
            context.startActivity(intent)
        }
    }
}
