package com.jsh.kr.alltestkt.model.base

object KotlinTestObject {
    const val TAG = "KotlinTestObject"

    fun makeText(num : Int) : String {
        return "$TAG make text $num"
    }

    @JvmStatic
    fun makeTextJavaStatic(num :Int) : String {
        return "$TAG make text java static $num"
    }
}