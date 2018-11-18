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


## 2.Kotlin基础

> - 声明函数、变量、类、枚举以及类型
> - Kotlin中的控制结构
> - 智能转换
> - 抛出和处理异常

[函数学习](https://github.com/youlookwhat/kotlin-learning/blob/master/kotlin/app/src/main/java/com/kotlin/jingbin/kotlinapp/MainActivity.kt)

### 函数和变量
#### 函数

```kotlin
	/**
     *  求最大值
     * if是表达式而不是语句，表达式有值，语句没有。
     * java中所有的控制结构都是语句
     * kotlin中除了循环以外大多数控制结构都是表达式
     */
    private fun max(a: Int, b: Int): Int {
        return if (a > b) a else b
    }

    /**
     * 如果函数体写在花括号中，我们说这个函数有代码块体。
     * 如果直接返回了一个表达式体，他就有表达式体。
     */
    fun max2(a: Int, b: Int): Int = if (a > b) a else b
```

#### 变量
##### 可变变量和不可变变量

 - val - 不可变引用。 相当于Java的final变量。
 - var - 可变引用。   普通的Java变量。

在定义了val变量的代码块执行期间，val变量只能进行唯一一次初始化。但是，如果编译器能确保只有唯一一条初始化语句被执行，可以根据条件使用不同的值来初始化它：

```kotlin
val message:String
if (CanPerformOperation()){
   message = "Success"
   // ...
} else{
   message = "Failed"
}
```

注意：尽管val引用自身是不可变的，但是它指向的对象可能是可变的。例如：

```kotlin
val languages = arrayListOf("Java")  // 声明不可变引用
languages.add("Kotlin")              // 改变引用指向的对象
```

错误：类型不匹配

```kotlin
var answer = 42
answer = "no answer"
```

#### 字符串模板
```kotlin
		var a1 = 1
        val s1 = "a is $a1"
        a1 = 3
        // 模板中的任意表达式
        val s2 = "${s1.replace("is", "was")},but no is $a1"
        Log.e("s2", s2)
        
// a was 1, but now is 3
```

和许多脚本语言一样，只需要在变量名称前加上 $ ，就可以在字符串字面值中引用局部变量。
引用"$"需要转义``“\$”``

### 类和属性
#### 类
```java
public class Person {
    
    private final String name;

    public Person(String name) {
        this.name = name;
    }
}
```

--->

```java
class Person(private val name: String)
```
**在Kotlin中，public是默认的可见性，所以你能省略它。**

#### 属性
```java
class PersonProperty {

    // 只读属性：生成一个字段和一个简单的getter
    val name: String = "kotlin_hahaha"

    // 可写属性：一个字段、一个getter和一个setter
    var isMarried: Boolean = false

    fun set() {
        isMarried = true
    }
}
```

#### 自定义属性
```java
/**
 * Created by jingbin on 2018/11/18.
 * 自定义访问器
 * 也可以使用函数返回，实现和性能没有差别，唯一的差别是可读性
 * 通常来说：
 * 如果描述的是类的特征(属性)，应该把它声明成属性。
 */
class Rectangle(val height: Int, val width: Int) {

    // 函数表达式 可以赋值
    val isSquare: Boolean
    // 声明属性的getter
        get() {
            return height == width
        }

}
```



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