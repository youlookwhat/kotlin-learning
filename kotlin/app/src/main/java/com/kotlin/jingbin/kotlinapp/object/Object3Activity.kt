package com.kotlin.jingbin.kotlinapp.`object`

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlin.jingbin.kotlinapp.R

/**
 * 4.3 编译器生成的方法: 数据类和类委托
 */
class Object3Activity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_object)

        title = "编译器生成的方法: 数据类和类委托"


        /**------------------------- 4.3.1 通用对象方法 -------------------------*/
        // 处理 toString equals hashCode等
        // 代码清单 4.17 Client类的最初声明
        class Client(val name: String, val postalCode: Int)

        /**toString()*/
        // 代码清单 4.18 为Client实现 toString()
        class Client2(val name: String, val postalCode: Int) {
            override fun toString(): String = "Client(name=$name,postalCode=$postalCode)"
        }
        println(Client2("jingbin", 333))

        /**equals()*/
        // 在Kotlin中，==检查对象是否相等，而不是比较引用。===与java中的 ==比较对象引用的效果一模一样
        // 代码清单 4.19 为Client类实现 equals()
        class Client3(val name: String, val postalCode: Int) {
            // "Any"是java.lang.Object的模拟:Kotlin中所有类的父类。可空类型"Any?"意味着"other"是可以为空的
            override fun equals(other: Any?): Boolean {
                if (other == null || other !is Client) {
                    return false
                }
                return name == other.name && postalCode == other.postalCode
            }

            override fun toString(): String = "Client(name=$name,postalCode=$postalCode)"
        }

        /**hashCode()*/
        val hashSetOf = hashSetOf(Client3("jingbin", 123456))
        println(hashSetOf.contains(Client3("jingbin", 123456)))// false
        // 因为Client缺少了hashCode方法。因此它违反了通用的hashCode契约：如果两个对象相等，它们必须有着相同的hash值。
        // 代码清单 4.20 为Client实现hashCode()
        class Client4(val name: String, val postalCode: Int) {
            override fun hashCode(): Int = name.hashCode() * 31 + postalCode
        }

        /**------------------------- 4.3.2 数据类：自动生成通用方法的实现 -------------------------*/
        // 给你的类添加 data修饰符上述的方法就会自动添加好，而且还会有其他的方法
        // 代码清单4.21 数据类 Client
        // equals 和 hashCode 会将所有主构造方法里的值一起处理，不在主构造方法里的不会处理！
        data class Client5(val name: String, val postalCode: Int)

        // 数据类和不可变性:Copy()方法
        class Client6(val name: String, val postalCode: Int) {
            fun copy(name: String = this.name, postalCode: Int = this.postalCode) = Client6(name, postalCode)
        }

        val client6 = Client6("jingbin", 123)
        println(client6.copy(postalCode = 345))
        // 为避免上述的模板代码，使用Kotlin -> by 类委托

        /**------------------------- 4.3.3 类委托：使用“by”关键字 -------------------------*/
        // 无论什么时候实现一个接口，你都可以使用by关键字将接口的实现 委托 到另一个对象。
        class DetegatingCollention<T>(innerList: Collection<T> = ArrayList<T>()) : Collection<T> by innerList {}

        // 代码清单4.22 使用类委托
        class CountingSet<T>(val innerSet: MutableCollection<T> = HashSet<T>()) : MutableCollection<T> by innerSet {
            // 将MutableCollection的实现委托给innerSet
            var objectAdded = 0

            override fun add(element: T): Boolean {
                objectAdded++
                return innerSet.add(element)
            }

            override fun addAll(c: Collection<T>): Boolean {
                objectAdded += c.size
                return innerSet.addAll(c)
            }
        }

        val countingSet = CountingSet<Int>()
        countingSet.addAll(listOf(1, 2, 3))
        // 3 objects were added,2 remain
        println("${countingSet.objectAdded} objects were added,${countingSet.size} remain ")
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent()
            intent.setClass(context, Object3Activity::class.java)
            context.startActivity(intent)
        }
    }
}
