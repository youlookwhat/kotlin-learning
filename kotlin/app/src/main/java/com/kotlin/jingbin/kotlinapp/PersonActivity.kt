package com.kotlin.jingbin.kotlinapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.kotlin.jingbin.kotlinapp.classproperty.Person
import com.kotlin.jingbin.kotlinapp.classproperty.PersonProperty

class PersonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person)

        // 构造方法调用不需要"new"
        val person = Person("jing")
        Log.e("name", person.name)

        val personProperty = PersonProperty()
        // 设置值
        personProperty.isMarried = false
        // val 不能设置值
//        personProperty.name = "haha"
    }
}
