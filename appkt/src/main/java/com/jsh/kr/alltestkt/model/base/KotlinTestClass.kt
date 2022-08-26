package com.jsh.kr.alltestkt.model.base

class KotlinTestClass {
    companion object {
        private val TAG = KotlinTestClass::class.java.simpleName

        fun makeText(num : Int) : String{
            return "$TAG make text $num"
        }

        fun makeText(text1: String, text2: String): String {
            return "make text - $text1 $text2";
        }

        @JvmStatic
        fun makeTextJavaStatic(num :Int) : String {
            return "${KotlinTestObject.TAG} make text java static $num"
        }
    }
}