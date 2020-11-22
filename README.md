# kotlin-learning

|书籍：[《Kotlin实战》][1]、GitHub：[JetBrains/kotlin][2]、[Kotlin 语言中文站][3]|
|:----:|

>
 - 书籍：[《Kotlin实战》][1]
 - GitHub：[JetBrains/kotlin][2]
 - [Kotlin 语言中文站][3]

**第一部分 Kotlin 简介**

 - [1.Kotlin: 定义和目的](https://github.com/youlookwhat/kotlin-learning/blob/master/1.Kotlin%20%E5%AE%9A%E4%B9%89%E5%92%8C%E7%9B%AE%E7%9A%84.md)
 - [2.Kotlin 基础](https://github.com/youlookwhat/kotlin-learning/blob/master/2.Kotlin%20%E5%9F%BA%E7%A1%80.md)
 - [3.函数的定义和目的](https://github.com/youlookwhat/kotlin-learning/blob/master/3.%E5%87%BD%E6%95%B0%E7%9A%84%E5%AE%9A%E4%B9%89%E4%B8%8E%E8%B0%83%E7%94%A8.md)
 - [4.类、对象和接口](https://github.com/youlookwhat/kotlin-learning/blob/master/4.%E7%B1%BB%E3%80%81%E5%AF%B9%E8%B1%A1%E5%92%8C%E6%8E%A5%E5%8F%A3.md)
 - 5.lambda 编程
 - 6.Kotlin 的类型系统

## 1.Kotlin: 定义和目的
本章内容包括：
> - Kotlin 的基本示范
> - Kotlin 语言的主要特征
> - Android 和服务端开发的可能性
> - Kotlin 与其他语言的区别
> - 用 Kotlin 编写并运行代码

### 总结
 - Kotlin 是静态类型语言并支持类型推导，允许维护正确性与性能的同时保持源代码的整洁。
 - Kotlin 支持面向对象很函数式的两种编程风格，通过头等函数使更高级别的抽象成为可能，通过支持不可变值简化了测试和多线程开发。
 - 在服务端应用程序中它工作的很好，全面支持现在所有的java框架，为常见的任务提供了新工具，如生成HTML和持久化。
 - 在 Android 上它也可以工作，这得益于紧凑的运行时、对 Andrid API 特殊的编辑器支持以及丰富的库，为常见Android开发任务提供了 Kotlin 友好的函数。
 - 它是免费和开源的，全面支持主流的 IDE 和构建系统。
 - Kotlin 是务实的、安全的、简洁的，与 Java 可互操作，意味着它专注于已经使用证明过的解决方案处理常见任务，防止常见的像 NullPointerException 这样的错误，支持紧凑的易读的代码，以及提供与 Java 无限制的集成。


## 2.Kotlin基础

本章内容包括：
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


## 3.函数的定义与调用

本章内容包括：
> - 用于处理集合、字符串和正则表达式的函数
> - 使用命名参数、默认参数，以及中辍调用的语法
> - 通过扩展函数和属性来适配Java库
> - 使用顶层函数、局部函数和属性架构代码

### 总结
 - Kotlin 没有定义自己的集合类，而是在Java集合类的基础上提供了更丰富的API。
 - Kotlin 可以给函数参数定义默认值，这样大大降低了重载函数的必要性，而且命名参数让多参数函数的调用更加易读。
 - Kotlin 允许更灵活的代码结构：函数和属性都可以直接在文件中声明，而不仅仅在类中作为成员。
 - Kotlin 可以用扩展函数和属性来扩展任何类的API,包括在外部中定义的类，而不需要修改其源代码，也没有运行时的开销。
 - 中辍调用提供了处理单个参数的，类似调用运算符方法的简明语法。
 - Koltin 为普通字符串和正则表达式都提供了大量的方便字符串处理的函数。
 - 三重引号的字符串提供了一种简洁的方式，解决了原本在Java中需要进行大量啰嗦的转义和字符串连接的问题。
 - 局部函数帮助你保持代码的整洁的同时，避免重复。

## 4.类、对象和接口

本章内容包括：
> - 类和接口
> - 非默认属性和构造方法
> - 数据类
> - 类委托
> - 使用 object 关键字

### 总结
- Kotlin 的接口与 Java 的相似，但是可以包含默认实现 (Java 从第8版才开始支持）和属性。
- 所有的声明默认都是 final public
- 要想使声明不是 final 的，将其标记为 open
- internal 声明在同一模块中可见。
- 嵌套类默认不是内部类。使用 inner 关键字来存储外部类的引用。
- sealed 类的子类只能嵌套在自身的声明中（Kotlin 1.1 允许将子类放置在同一文件的任意地方）。
- 初始化语句块和从构造方法为初始化类实例提供了灵活性
- 使用 field 标识符在访问器方法体中引用属性的支持字段
- 数据类提供了编译器生成的 equals hashCode toString copy 和其他方法。
- 类委托帮助避免在代码中出现许多相似的委托方法。
- 对象声明是Kotlin 中定义单例类的方法。
- 伴生对象（与包级别函数和属性 起）替代了 Java 静态方法和字段定义
- 伴生对象与其他对象一样，可以实现接口，也可以拥有有扩展函数和属性
- 对象表达式是 Kotlin 中针对 Java 匿名内部类的替代品，并增加了诸如实现个接口的能力和修改在创建对象的作用域中定义的变 的能力等功能


[1]:https://book.douban.com/subject/27093660/
[2]:https://github.com/JetBrains/kotlin
[3]:https://www.kotlincn.net/docs/reference/android-overview.html