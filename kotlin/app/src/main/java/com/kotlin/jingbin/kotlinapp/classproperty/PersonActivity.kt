package com.kotlin.jingbin.kotlinapp.classproperty

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlin.jingbin.kotlinapp.R
import com.kotlin.jingbin.kotlinapp.utils.LogUtil

class PersonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person)

        // 构造方法调用不需要"new"
        val person = Person("jing")
        LogUtil.e("name", person.name)

        val personProperty = PersonProperty()
        // 设置值
        personProperty.isMarried = false
        // val 不能设置值
//        personProperty.name = "haha"

        // 自定义访问器
        LogUtil.e(Rectangle(12, 12).isSquare)

        // 导入其他包中的函数
        LogUtil.e(createRandomRectangle().isSquare)
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent()
            intent.setClass(context, PersonActivity::class.java)
            context.startActivity(intent)
        }
    }
}
