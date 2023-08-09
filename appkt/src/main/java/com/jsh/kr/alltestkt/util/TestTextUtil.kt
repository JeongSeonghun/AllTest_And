package com.jsh.kr.alltestkt.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.Random

object TestTextUtil {
    fun makeCurrentTime(): String? {
        val format = SimpleDateFormat("hh:mm:ss", Locale.KOREA)
        return format.format(Date())
    }

    fun makeRandomId(): String? {
        val format = SimpleDateFormat("MMddHHmm", Locale.getDefault())
        return format.format(Date()) + makeRandomNumEng(2)
    }

    fun makeRandomNumEng(cnt: Int): String {
        // ASCII 0~9(48 ~ 57),  A~Z(65~90), a(97 ~ 122)
        val charArray = CharArray(cnt)
        for (i in 0 until cnt) {
            val random = Random()
            val randomNum = random.nextInt(62)
            LogUtil.d(
                "makeRandomNumEng",
                "cnt : $cnt, randomNum : $randomNum"
            )
            if (randomNum < 10) {
                LogUtil.d(
                    "makeRandomNumEng",
                    "int value : " + (randomNum + 48) + ", char value : " + (randomNum + 48).toChar()
                )
                charArray[i] = (randomNum + 48).toChar()
            } else if (randomNum < 36) {
                LogUtil.d(
                    "makeRandomNumEng",
                    "int value : " + (randomNum - 10 + 65) + ", char value : " + (randomNum - 10 + 65).toChar()
                )
                charArray[i] = (randomNum - 10 + 65).toChar()
            } else if (randomNum < 62) {
                LogUtil.d(
                    "makeRandomNumEng",
                    "int value : " + (randomNum - 36 + 97) + ", char value : " + (randomNum - 36 + 97).toChar()
                )
                charArray[i] = (randomNum - 36 + 97).toChar()
            }
        }
        return String(charArray)
    }
}