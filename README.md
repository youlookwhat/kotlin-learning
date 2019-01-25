# kotlin-learning

>  - 书籍：[《Kotlin实战》][1]
>  - GitHub：[JetBrains/kotlin][2]
>  - Kotlin 语言中文站：https://www.kotlincn.net/docs/reference/android-overview.html

## 1.定义和目的
 - Kotlin和Java一样是一种静态类型的编程语言。编译时即可检查代码正确性。动态语言：Groovy,JRuby。
 - 根据上下问判断变量类型： val x=1
 - 性能、可靠性、可维护性、工具支持。

---

支持函数式编程风格，不强制使用：

 - 函数类型，允许函数接受其他函数作为参数，或者返回其他函数。
 - lambda表达式
 - 数据类，提供了创建不可变值对象的简明语法
 - 标准库中包含了丰富的API集合，让你用函数式编程风格操作对象和集合。

---
自动检查空指针：

 - val s: String? = null  可以为null，也会检查，禁止可能导致的空指针
 - val s2: String = ""    不能为null

避免类型转换异常：

```kotlin
if(value is String)               检查类型
  println(value.toUpperCase())    调用该类型的方法
```

---

 - 源代码文件存放在后缀名为.kt的文件中，编辑器生成.class文件。
 - AndriodSdudio中使用：**"Setting(设置) - Plugins(插件) - Install JetBrains Plugin - Kotlin"**


## [2.Kotlin基础](https://github.com/youlookwhat/kotlin-learning/blob/master/Kotlin%E5%9F%BA%E7%A1%80.md)

> - 声明函数、变量、类、枚举以及类型
> - Kotlin中的控制结构
> - 智能转换
> - 抛出和处理异常

### 总结
 - fun 关键字用来声明函数。Val关键字和var关键字分别用来声明只读变量和可变变量。
 - 字符串模板帮组你避免繁琐的字符串拼接。在变量名称前加上 $ 前缀或者用 ${} 包围一个表达式，来把值注入到字符串中。
 - 值对象类在Kotlin中以简洁的方式表示。
 - 熟悉的if现在是带返回值的表达式。
 - when表达式类似于Java中的switch但功能更强大。
 - 在检查过变量具有某种类型之后不必显示地转换它的类型:编译器使用智能转换字段帮你完成。
 - for、while、和 do-while 循环与java类似，但是for循环现在更加方便，特别是当你需要迭代map的时候，又或是迭代集合需要下标的时候。
 - 简洁的语法 1..5 会创建一个区间。区间和数列允许Kotlin在for循环中使用统一的语法和同一套抽象机制，并且还可以使用in运算符和!in运算符来检查值是否属于某个区间。
 - Kotlin中的异常处理和java非常相似，除了Kotlin不要求你声明函数可以抛出异常。



## 网址学习

Android 与 Kotlin 入门：
> https://www.kotlincn.net/docs/tutorials/kotlin-android.html

1.将 Java 代码转换为 Kotlin:
help - find action - Convert Java File to Kotlin File

2.点击提示中的 立即同步（Sync Now）

Kotlin有着极小的运行时文件体积：整个库的大小约 964KB（1.3.0 版本）。这意味着 Kotlin 对 apk 文件大小影响微乎其微。

---

Kotlin Android 扩展:

开发者仅需要在模块的 build.gradle 文件中启用 Gradle 安卓扩展插件即可：

```
apply plugin: 'kotlin-android-extensions'
```
导入合成属性
仅需要一行即可非常方便导入指定布局文件中所有控件属性：

```
import kotlinx.android.synthetic.main.＜布局＞.*
```

将有一个名为 hello 的属性：
```
activity.hello.text = "Hello World!"
```

[变量](https://www.kotlincn.net/docs/reference/basic-syntax.html)






































[1]:https://book.douban.com/subject/27093660/
[2]:https://github.com/JetBrains/kotlin