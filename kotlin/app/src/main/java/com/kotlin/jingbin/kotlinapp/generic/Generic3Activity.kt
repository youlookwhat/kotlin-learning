package com.kotlin.jingbin.kotlinapp.generic

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlin.jingbin.kotlinapp.R

/**
 * 9.3 变型：泛型很子类型化
 */
class Generic3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generic1)

        title = "9.3 变型：泛型很子类型化"

        /**-------------------- 9.3.1 为什么存在变型：给函数传递实参 ----------------------*/
        // 打印出列表内容的函数
        fun printContents(list: List<Any>) {
            println(list.joinToString())
        }
        printContents(listOf("abc", "bac"))// abc,bac

        // 修改列表
        fun addAnswer(list: MutableList<Any>) {
            list.add(42)
        }

        val strings = mutableListOf("abc", "bac")
        // 编译不了，添加或替换忽悠安全性问题
//        addAnswer(strings)

        /**-------------------- 9.3.2 类、类型和子类型 ----------------------*/
        // 每一个Kotlin类都可以用于构造至少两种类型
        var x: String
        var y: String?

        /*
        * 任何时候，如果需要的是 类型A 的值，你都能够使用 类型B 的值(当做A的值)，类型B就称为类型A的子类型。
        * 如果A是B的子类型，那么B就是A的超类型。
        */

        // 代码清单9.10 检查一个类型是否是另一个的子类型
        fun test(i: Int) {
            // 编译通过，因为 Int 是Number的子类型
            val n: Number = i

            // 不能编译，因为Int不是String的子类型
//            fun f(s: String) {}
//            f(i)
        }
        /*
        * 简单的情况下，子类型和子类本质上意味着一样的事物。
        * 例如，Int类是Number的子类，因此Int类型是Number类型的子类型。
        *
        * 可空类型 的子类型和子类不是同一事物。
        */

        val s: String = "abc"
        // 这次的赋值是很合法的，因为 String 是 String? 的子类型
        val t: String? = s

        // MutableList<String> 不是 MutableList<Any> 的子类型，因为MutableList可空


        /**-------------------- 9.3.3 协变：保留子类型化关系 ----------------------*/
        // 如果 A 是 B 的子类型，那么 List<A>就是List<B>的子类型。这样的类或者接口被称为协变的。

        // 要声明类在某个类型参数上是可协变的，在该类型参数的名称前加上 out 关键字即可
//        interface Producer<out T>{
//            fun produce():T
//        }


        // 代码清单9.11 定义一个不变型的类似集合的类
        open class Animal {
            fun feed() {}
        }

        // 类型参数没有声明成协变的
        class Herd<T : Animal> {
            val size: Int get() = 1
            operator fun get(i: Int): T? {
                return null
            }
        }

        fun feedAll(animal: Herd<Animal>) {
            for (i in 0 until animal.size) {
                animal[i]?.feed()
            }
        }

        // 代码清单9.12 使用一个不变型的类似集合的类
        class Cat : Animal() {
            fun cleanLitter() {}
        }

        fun takeCarsOfCats(cats: Herd<Cat>) {
            for (i in 0 until cats.size) {
                cats[i]?.cleanLitter()
                // 错误：推导的类型是 Herd<Cat>,但期望的却是 Herd<Animal>。加上 out就好了： Herd<out T : Animal>
//                feedAll(cats)
            }
        }

        // 代码清单9.13 使用一个协变的类似集合的类
        class Herd2<out T : Animal> {

        }

        // 函数参数的类型叫做 in 位置，而函数返回类型叫作 out 位置
//        interface Transformer<T> {
//            // in 位置：[t:T]
//            // out 位置：[T]
//            fun transform(t: T): T
//        }

        /*
        * 类型参数 T 上的关键字 out 有两层含义：
        *  - 子类型化会被保留 (Producer<Car> 是  Producer<Animal>的子类型)
        *  - T 只能用在 out 位置
        */

        // List<Interface>的List是只读的，所以它只有一个返回类型为 T 的元素的方法 get，而没有定义任何类型为T的元素存储到列表中的方法，因为它是协变的。
//        interface List<out T>:Collection<T>{
//            operator fun get(index:Int):T
//        }

        // MutableList 不能在T上声明成协变的
//        interface MutableList<T> : List<T>, MutableCollection<T> {
//             因为T用在了 in 的位置
//            override fun add(element: T): Boolean
//        }

        /**-------------------- 9.3.4 逆变：反转子类型化关系 ----------------------*/

        // 在 in 位置使用T
//        interface Comparator<in T> {
//            fun compare(e1: T, e2: T): Int
//        }

        val anyComparator = Comparator<Any> { e1, e2 ->
            e1.hashCode() - e2.hashCode()
        }
        // 可以用任意对象的比较器比较具体对象，比如字符串
        val strings1: List<String> = listOf("11", "22")
        strings1.sortedWith(anyComparator)

        /*
        * Animal  <--  Cat
        * Producer<Animal>  <-- 协变 -- Producer<Cat>
        * Producer<Animal>   -- 逆变 --> Producer<Cat>
        * 对协变类型Producer<T>来说，子类型化保留了，但对逆变类型Consumer<T>来说，子类型化反转了
        *
        * 协变的、逆变的和不变型的类
        * 协变                             逆变                             不变型
        * -------------------------------------------------------------------------------------
        * Producer<out T>                  Consumer<in T>                  MutableList<T>
        * -------------------------------------------------------------------------------------
        * 类的子类型化保留了：Producer<Cat>  子类型化反转了：Consumer<Animal>   没有子类型变化
        * 是Producer<Animal>的子类型        是Consumer<Cat>的子类型
        * -------------------------------------------------------------------------------------
        * T 只能在 out 位置                 T 只能在 in 位置                   T 可以在任何位置
        */

        // 一个类可以在一个类型参数上协变，同时在另一个类型参数上逆变。Function接口
//        interface Function1<in P, out R> {
//            operator fun invoke(p: P): R
//        }


        fun enumerateCats(f: (Cat) -> Number) {}
        fun Animal.getIndex(): Int = 1
        // 在 Kotlin 中这段代码是合法的。Animal是Cat的超类型，而 Int 是Number的子类型
        enumerateCats(Animal::getIndex)

        /**-------------------- 9.3.5 使用点变型：在类型出现的地方指定变型 ----------------------*/
        // Kotlin的声明点变型 vs .Java 通配符
    }

    // 一个类可以在一个类型参数上协变，同时在另一个类型参数上逆变。
    interface Function1<in P, out R> {
        operator fun invoke(p: P): R
    }

    // 在 in 位置使用T
    interface Comparator<in T> {
        fun compare(e1: T, e2: T): Int
    }

    // MutableList 不能在T上声明成协变的
    interface MutableList<T> : List<T>, MutableCollection<T> {
        // 因为T用在了 in 的位置
        override fun add(element: T): Boolean
    }

    interface Transformer<T> {
        // in 位置：[t:T]
        // out 位置：[T]
        fun transform(t: T): T
    }


    // 要声明类在某个类型参数上是可协变的，在该类型参数的名称前加上 out 关键字即可
    // 类被声明成在T上协变
    interface Producer<out T> {
        fun produce(): T
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent()
            intent.setClass(context, Generic3Activity::class.java)
            context.startActivity(intent)
        }

    }


}
