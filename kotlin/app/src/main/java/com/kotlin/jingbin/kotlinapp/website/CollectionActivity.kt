package com.kotlin.jingbin.kotlinapp.website

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlin.jingbin.kotlinapp.R
import java.util.*
import kotlin.collections.HashSet

/**
 * 集合
 * 构造集合：https://www.kotlincn.net/docs/reference/constructing-collections.html
 * 序列：https://www.kotlincn.net/docs/reference/sequences.html
 *
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

        // 5.3 关联生成 Map:
        val numbers3 = listOf("one", "two", "three", "four")
        println(numbers3.associateWith { it.length })

        // 6 序列
        // 6.1 构造
        val numbersSequence = sequenceOf("four", "three", "two", "one")
        // 6.2 由 Iterable
        val numbers4 = listOf("four", "three", "two", "one");
        val numbersSequence2 = numbers4.asSequence()
        // 6.3 由函数 (创建序列的另一种方法是通过使用计算其元素的函数来构建序列。)
        val generateSequence = generateSequence(1) { if (it < 10) it + 2 else null }
        println(generateSequence.count())
        // 6.4 由组块
        val oddNumbers = sequence {
            yield(1)
            yieldAll(listOf(3, 5))
            yieldAll(generateSequence(7) { it + 2 })
        }
        println(oddNumbers.take(5).toList())
        // 6.5 序列处理示例
        // 假定有一个单词列表。下面的代码过滤长于三个字符的单词，并打印前四个单词的长度。
        // Iterable
        val works = "The quick brown fox jumps over the lazy dog".split(" ")
        val lengthsList = works.filter { println("filter: $it");it.length > 3 }
                .map { println("length: ${it.length}");it.length }
                .take(4)
        println("Lengths of first 4 words longer than 3 chars:")
        println(lengthsList)
        // Sequence (仅在构建结果列表时才调用 filter() 与 map() 函数)
        val works2 = "The quick brown fox jumps over the lazy dog".split(" ")
        val asSequence = works2.asSequence()
        val take = asSequence.filter { println("filter: $it");it.length > 3 }
                .map { println("length: ${it.length}");it.length }
                .take(4)
        println("Lengths of first 4 words longer than 3 chars")
        // 末端操作：以列表形式获取结果。
        println(take.toList())

        // 7 集合操作概述 https://www.kotlincn.net/docs/reference/collection-operations.html
        // 7.1 一些操作返回其结果，而不会影响原始集合
        val listOf = listOf("one", "two", "three")
        listOf.filter { it.length > 3 }
        println("numbers are still $listOf")
        val filter = listOf.filter { it.length > 3 }
        println("numbers longer than 3 chars are $filter")

        // 7.2 对于某些集合操作，有一个选项可以指定 目标 对象。
        val listOf1 = listOf("one", "two", "three")
        val mutableListOf = mutableListOf<String>()
        listOf1.filterTo(mutableListOf) { it.length > 3 }
        // 不使用的String常量用_标识
        listOf1.filterIndexedTo(mutableListOf) { index, _ -> index == 0 }
        println(mutableListOf)

        // 7.3 为了方便起见，这些函数将目标集合返回了，因此您可以在函数调用的相应参数中直接创建它：
        // 将数字直接过滤到新的哈希集中，
        // 从而消除结果中的重复项
        val mapTo = listOf1.mapTo(HashSet()) { it.length }
        println("distinct item lengths are $mapTo")

        // 8 写操作
        // 对于某些操作，有成对的函数可以执行相同的操作：一个函数就地应用该操作，另一个函数将结果作为单独的集合返回
        val mutableListOf1 = mutableListOf("one", "two", "three")
        val sorted = mutableListOf1.sorted()
        println(mutableListOf1 == sorted)//false
        mutableListOf1.sort()
        println(mutableListOf1 == sorted)//true

        // 9 集合转换 https://www.kotlincn.net/docs/reference/collection-transformations.html

        // 9.1 映射
        // 映射转换从另一个集合的元素上的函数结果创建一个集合。 基本的映射函数是 map()。
        val numbers9 = setOf(1, 2, 3)
        println(numbers9.map { it * 3 })
        println(numbers9.mapIndexed { idx, value -> value * idx })
        // 如果转换在某些元素上产生 null 值，则可以通过调用 mapNotNull() 函数取代
        val numbers10 = setOf(1, 2, 3)
        println(numbers10.mapNotNull { if (it == 2) null else it * 3 })
        println(numbers10.mapIndexedNotNull { idx, value -> if (idx == 0) null else value * idx })
        // 映射转换时，有两个选择：转换键，使值保持不变，反之亦然。
        val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key11" to 11)
        println(numbersMap.mapKeys { it.key.toUpperCase() })//{KEY1=1, KEY2=2, KEY3=3, KEY11=11}
        println(numbersMap.mapValues { it.value + it.key.length })//{key1=5, key2=6, key3=7, key11=16}

        // 9.2 双路合并
        // 双路合并 转换是根据两个集合中具有相同位置的元素构建配对
        val colors = listOf("red", "brown", "grey")
        val animals = listOf("fox", "bear", "wolf")
        println(colors zip animals)//[(red, fox), (brown, bear), (grey, wolf)]
        val twoAnimals = listOf("fox", "bear")
        println(colors.zip(twoAnimals))//[(red, fox), (brown, bear)]
        // 也可以使用带有两个参数的转换函数来调用 zip()：接收者元素和参数元素。
        // [The Fox is red, The Bear is brown, The Wolf is grey] (capitalize首字母大写)
        println(colors.zip(animals) { color, animal -> "The ${animal.capitalize()} is $color" })
        // 当拥有 Pair 的 List 时，可以进行反向转换 unzipping
        val numberPairs = listOf("one" to 1, "two" to 2, "three" to 3, "four" to 4)
        println(numberPairs.unzip())// ([one, two, three, four], [1, 2, 3, 4])

        // 9.3 关联
        // 基本的关联函数 associateWith() 创建一个 Map，其中原始集合的元素是键，并通过给定的转换函数从中产生值。 如果两个元素相等，则仅最后一个保留在 Map 中
        val numbers11 = listOf("one", "two", "three", "four")
        println(numbers11.associateWith { it.length })// {one=3, two=3, three=5, four=4}
        // 为了使用集合元素作为值来构建 Map，有一个函数 associateBy()。
        val numbers12 = listOf("one", "two", "three", "four")
        println(numbers12.associateBy { it.first().toUpperCase() })
        println(numbers12.associateBy(keySelector = { it.first().toUpperCase() }, valueTransform = { it.length }))
        // 另一种构建 Map 的方法是使用函数 associate()，其中 Map 键和值都是通过集合元素生成的。
        val names = listOf("Alice Adams", "Brian Brown", "Clara Campbell")
//        println(names.associate { name -> parseFullName(name).let { it.lastName to it.firstName } })

        // 9.4 打平
        // 如需操作嵌套的集合，则可能会发现提供对嵌套集合元素进行打平访问的标准库函数很有用
        // 第一个函数为 flatten()。
        val numberSets = listOf(setOf(1, 2, 3), setOf(4, 5, 6), setOf(1, 2))
        println(numberSets.flatten())
        // 另一个函数——flatMap() 提供了一种灵活的方式来处理嵌套的集合。
//        val containers = listOf(
//                StringContainer(listOf("one", "two", "three")),
//                StringContainer(listOf("four", "five", "six")),
//                StringContainer(listOf("seven", "eight"))
//        )
//        println(containers.flatMap { it.values })

        // 9.5 字符串表示
        // 将集合转换为字符串的函数：joinToString() 与 joinTo()。
        val numbers13 = listOf("one", "two", "three", "four")
        println(numbers13)//[one, two, three, four]
        println(numbers13.joinToString())//one, two, three, four
        val listString = StringBuffer("The list of numbers: ")
        numbers13.joinTo(listString)
        println(listString)//The list of numbers: one, two, three, four

    }

    companion object {
        fun start(context: Context) {
            val intent = Intent()
            intent.setClass(context, CollectionActivity::class.java)
            context.startActivity(intent)
        }
    }
}
