package com.kotlin.jingbin.kotlinapp.generic

/**
 * Created by jingbin on 2020/12/16.
 */

interface List2<T> {
    operator fun get(index: Int): T
}