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
import kotlin.reflect.KProperty
import kotlin.reflect.KProperty1
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
//        private fun StringBuilder.serializeObject(obj: Any) {
//        obj.javaClass.kotlin.memberProperties
//                .filter { it.findAnnotation<JsonExclude>() == null }
//                .joinToStringBuilder(this, prefix = "{", postfix = "}") {
//                    serializeProperty(it, obj)
//                }
//        }

        // 代码清单10.3 序列化单个属性
//        private fun StringBuilder.serializeProperty(prop: KProperty1<Any, *>, obj: Any) {
//            val jsonNameAnn = prop.findAnnotation<JsonName>()
//            val propName = jsonNameAnn?.name ?: prop.name
//            append(": ")
//            serializePropertyValue(prop.get(obj))
//        }

        // 代码清单10.4 取回属性值的序列化器
        // 代码清单10.5 序列化属性，支持自定义序列化器

        /**-------------------------10.2.4 JSON解析和对象反序列化-------------------------*/
        // 代码清单10.6 JSON解析器回调接口
        // 代码清单10.7 从JSON数据创建对象的接口
        // 代码清单10.8 顶层反序列化函数
        // 代码清单10.9 反序列化一个对象

        /**-------------------------10.2.5 反序列化的最后一步：callBy()和使用反射创建对象-------------------------*/
        // 代码清单10.10 根据值类型取得序列化器
        // 代码清单10.11 Boolean值的序列化器
        // 代码清单10.12 缓存的反射数据的存储
        // 代码清单10.13 构造方法的参数及注解数据的缓存
        // 代码清单10.14 验证需要的参数被提供了
    }

    // 代码清单10.3 序列化单个属性
//    private fun StringBuilder.serializeProperty(prop: KProperty1<Any, *>, obj: Any) {
//        val jsonNameAnn = prop.findAnnotation<JsonName>()
//        val propName = jsonNameAnn?.name ?: prop.name
//        append(": ")
//        serializePropertyValue(prop.get(obj))
//    }

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