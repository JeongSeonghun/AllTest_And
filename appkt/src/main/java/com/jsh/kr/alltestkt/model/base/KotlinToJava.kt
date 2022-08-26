package com.jsh.kr.alltestkt.model.base

import java.util.*

fun makeRandomText(): String {
    return "random text : "+ Random().nextInt(10)
}