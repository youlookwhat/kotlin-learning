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
        // a was 1, but now is 3
        Log.e("s2", s2)
```

和许多脚本语言一样，只需要在变量名称前加上 $ ，就可以在字符串字面值中引用局部变量。
引用"$"需要转义``“\$”``

### 类和属性
#### 类
在Kotlin中，public是默认的可见性，所以你能省略它。
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

#### 自定义访问器
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

#### Kotlin源码布局：目录和包
> 1.把类和函数的声明放在包中，可以同级

```kotlin
class Rectangle(val height: Int, val width: Int) {

    // 函数表达式 可以赋值
    val isSquare: Boolean
    // 声明属性的getter
        get() {
            return height == width
        }

}

fun createRandomRectangle(): Rectangle {
    val random = Random()
    return Rectangle(random.nextInt(), random.nextInt())
}
```

Kotlin不区分导入的是类还是函数，而且，它允许使用import关键字导入任何种类的声明。可以直接导入顶层函数的名称。

> 2.导入其他包中的函数

```kotlin
// 导入函数的名称
import com.kotlin.jingbin.kotlinapp.classproperty.createRandomRectangle
// 导入其他包中的函数
LogUtil.e(createRandomRectangle().isSquare)

```

包层级和java类似。

### 表示和处理选择: 枚举和"when"
when结构，java中switch结构的替代品，但是更强大。智能转换。
#### 枚举
##### 1.声明简单的枚举类

```kotlin
enum class SimpleColor {
    RED, ORANGE
}
```

##### 2.声明一个带属性的枚举类

```kotlin
enum class Color(
        // 声明枚举常量的属性
        val r: Int, val g: Int, val b: Int) {
    // 在每一个常量创建的时候指定属性值
    RED(255, 0, 0),
    ORANGE(255, 165, 0),
    WELLOW(255, 255, 0),
    GREEN(0, 255, 0),
    BULE(0, 0, 255),
    INDIGO(75, 0, 130),
    VIILET(238, 130, 238);// 分号

    fun rgb() = (r * 256 + g) * 256 + b
}
```

##### 3.使用“when”处理枚举类

```kotlin
 /**
     * 使用when处理枚举类:
     * 直接返回一个“when"表达式
     */
    fun getMnemonic(color: Color) = {
        when (color) {
            RED -> "Richard"
            ORANGE -> "Of"
            WELLOW -> "Haha"
            // 合并多个选项
            BULE, GREEN -> "望穿"
            VIILET, INDIGO -> "秋水"
        }
    }
```

#### when
##### 1、在 when 结构中使用任意对象 
```kotlin
fun mix(c1: Color, c2: Color) = {
        // when 表达式的实参可以是任何对象，它被检查是否与分支条件对等
        when (setOf(c1, c2)) {
            setOf(Color.RED, Color.YELLOW) -> Color.ORANGE
            setOf(Color.BLUE, Color.YELLOW) -> Color.GREEN
            setOf(Color.BLUE, Color.VIOLET) -> Color.INDIGO
        // 如果没有任何其他分支匹配这里就会执行
            else -> throw Exception("Dirty color")
        }
    }
```

##### 2、不带参数的 when
```kotlin
fun minOptimized(c1: Color, c2: Color) = {
        // 没有实参传给 when
        when {
            (c1 == Color.RED && c2 == Color.YELLOW) || (c2 == Color.RED && c1 == Color.YELLOW) -> Color.ORANGE
            (c1 == Color.BLUE && c2 == Color.YELLOW) || (c2 == Color.BLUE && c1 == Color.YELLOW) -> Color.GREEN
            (c1 == Color.BLUE && c2 == Color.VIOLET) || (c2 == Color.BLUE && c1 == Color.VIOLET) -> Color.INDIGO

            else -> throw Exception("Dirty color")
        }
    }
```

##### 3、智能转换：合并类型检查和转换
```kotlin
// 3.1表达式层次结构
    interface Expr

    // 简单的值对象类，只有一个属性value，实现了Expr接口
    class Num(val value: Int) : Expr

    // sum运算的实参可以是任何Expr: Num或者另一个Sum
    class Sum(val left: Expr, val right: Expr) : Expr

    /**
     * 3.2 使用 if 层叠对表达式求值
     * 在 Kotlin 中，如果你检查过一个变量是某种类型，后面就不再需要转换它，可以就把它当作你检查过的类型使用。
     * 事实上编译器为你执行了类型转换，我们把这种行为称为智能转换。
     * */
    fun eval(e: Expr): Int {
        // is - instanceOf
        if (e is Num) {
            // 显示的转换成类型 Num是多余的
            val num = e as Num
            return num.value
        }
        if (e is Sum) {
            // 变量 e 被智能转换了类型
            return eval(e.left) + eval(e.right)
        }
        throw IllegalAccessException("Unknown expression")
```

##### 4、重构：用“when”代替“if”
```kotlin
/**
     * Kotlin 中没有三元运算符，因为if有返回值
     * 意味着: 可以用表达式语法重写eval函数，去掉return语句和花括号，使用if表达式作为函数体
     */
    // 4.1 使用用返回值的 if 表达式
    fun eval2(e: Expr): Int =
            if (e is Num) {
                e.value
            } else if (e is Sum) {
                eval2(e.right) + eval2(e.left)
            } else {
                throw IllegalAccessException("Unknown expression")
            }

    // 4.2 使用 when 代替 if 层叠
    fun eval3(e: Expr): Int =
            when (e) {
                is Num -> e.value
                is Sum -> eval3(e.right) + eval3(e.left)
                else -> throw IllegalAccessException("Unknown expression")
            }
```

##### 5、代码块作为 “if” 和 “when” 的分支
```kotlin
/**
     * 一个函数要么具有不是代码块的表达式函数体，
     * 要么具有包含显示return语句的代码块函数体
     */
    // 在分支中含有混合操作的 when
    fun evalWithLogging(e: Expr): Int =
            when (e) {
                is Num -> {
                    LogUtil.e("num: ${e.value}")
                    e.value
                }
                is Sum -> {
                    val left = this.evalWithLogging(e.left)
                    val right = this.evalWithLogging(e.right)
                    LogUtil.e("Sum: $left + $right")
                    // 代码块中最后的表达式就是结果
                    left + right
                }
                else -> throw IllegalAccessException("Unknown expression")
            }
```

### 迭代事物: “when”循环和“for”循环
#### 1、“while” 循环
Kotlin 有 while 循环和 do-while 循环，他们的语法和Java中相应的循环没有什么区别

#### 2、迭代数字：区间和数列
```kotlin
 /**
     * 区间：区间本质上就是两个值之间的间隔，这两个值通常是数字：一个起始值，一个结束值。
     * 使用 .. 运算符来表示区间
     * 数列：你能用整数区间做的最基本的事情就是循环迭代其中所有的值。
     * 如果你能迭代区间中所有的值，这样的区间被称作数列。
     * */

    val oneToTen = 1..10

    // 使用 when 实现 Fizz-Buzz 游戏
    fun fizzBuzz(i: Int) = when {
        i % 15 == 0 -> "FizzBuzz"
        i % 3 == 0 -> "Fizz"
        i % 5 == 0 -> "Buzz"
        else -> "$i"
    }

        for (i in 1..100) {
//            LogUtil.e(fizzBuzz(i))
        }
        // 倒序 只计偶数 [使用 until 函数可以标识：不包含指定结束值的半闭合区间]
        for (i in 100 downTo 0 step 2) {
            LogUtil.e(fizzBuzz(i))
        }
```

#### 3、迭代map
```kotlin
		// 使用 TreeMap 让键排序
        val binaryReps = TreeMap<Char, String>()
        // 创建字符区间 包括 F
        for (c in 'A'..'F') {
            // 把 ASCII 码转换成二进制
            val binaryString = Integer.toBinaryString(c.toInt())
            binaryReps[c] = binaryString
        }
        // 迭代 map ，把键和值赋值给两个变量
        for ((letter, binary) in binaryReps) {
            LogUtil.e("$letter = $binary")
        }

        // 迭代集合时 使用下标
        val list = arrayListOf("10", "11", "1001")
        for ((index, element) in list.withIndex()) {
            LogUtil.e("$index = $element")
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