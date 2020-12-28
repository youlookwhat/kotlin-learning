package com.kotlin.jingbin.kotlinapp.annotations

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.kotlin.jingbin.kotlinapp.R
import com.kotlin.jingbin.kotlinapp.generic.Generic3Activity
import org.json.JSONException
import java.time.temporal.Temporal
import kotlin.experimental.ExperimentalTypeInference
import kotlin.reflect.KClass
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties

/**
 * 10.2 反射：在运行时对Kotlin对象进行自身
 * */
class AnnotationsReflection2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_annotations_reflection)

        /**-------------------------10.2.1 Kotlin反射API：KClass、KCallable、KFunction、和KProperty-------------------------*/

        // 从Java切换到Kotlin的反射API：
        class Person(val name: String, val age: Int)

        val person = Person("jingbin", 28)
        val kClass = person.javaClass.kotlin// 返回一个 KClass<Person> 的实例
        println(kClass.simpleName)// Person
        kClass.memberProperties.forEach { println(it.name) }// age name

        // 如何通过反射使用call来调用一个函数
//        interface KCallable<out R> {
//            fun call(vararg age: Any?): R
//        }

        fun foo(x: Int) = println(x)
        val kFunction = ::foo
        kFunction.call(42)

        /**-------------------------10.2.2 用反射实现对象序列化-------------------------*/
        // 代码清单10.1 序列化一个对象
//        private fun StringBuilder.serializeObject(obj: Any) {
//            // 取得对象的 XClass
//            val kClass = obj.javaClass.kotlin
//            // 取得类的所有属性
//            val properties = kClass.memberProperties
//
//            properties.joinToStringBuilder(this, prefix = "{", postfix = "}") { prop ->
//                // 取得属性名
//                serializeString(prop.name)
//                append(": ")
//                // 取得属性值
//                serializePropertyValue(prop.get(obj))
//            }
//        }

        /**-------------------------10.2.3 用注解订制序列化-------------------------*/
        // 代码清单10.2 使用属性过滤序列化对象
    }

    private fun StringBuilder.serializeObject(obj: Any) {
//        obj.javaClass.kotlin.memberProperties
//                .filter { it.findAnnotation<JsonExclude>() == null }
//                .joinToStringBuilder(this, prefix = "{", postfix = "}") {
//                    serializeProperty(it, obj)
//                }
    }

    interface KCallable<out R> {
        fun call(vararg age: Any?): R
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent()
            intent.setClass(context, AnnotationsReflection2Activity::class.java)
            context.startActivity(intent)
        }
    }
}