package com.jsh.kr.alltest.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class TestTextUtil {
    public static String makeCurrentTime() {
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss", Locale.KOREA);
        return format.format(new Date());
    }

    public static String makeRandomId() {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmm", Locale.getDefault());
        return format.format(new Date()) + makeRandomNumEng(2);
    }

    public static String makeRandomNumEng(int cnt) {
        // ASCII 0~9(48 ~ 57),  A~Z(65~90), a(97 ~ 122)
        char[] charArray = new char[cnt];

        for(int i = 0; i < cnt ; i++) {
            Random random = new Random();
            int randomNum = random.nextInt(62);

            LogUtil.d("makeRandomNumEng", "cnt : "+cnt + ", randomNum : "+randomNum);
            if (randomNum < 10) {
                LogUtil.d("makeRandomNumEng", "int value : "+(randomNum + 48) + ", char value : "+(char) (randomNum + 48));
                charArray[i] = (char) (randomNum + 48);
            } else if (randomNum < 36) {
                LogUtil.d("makeRandomNumEng", "int value : "+(randomNum - 10 + 65) + ", char value : "+(char) (randomNum - 10 + 65));
                charArray[i] = (char) (randomNum - 10 + 65);
            } else if (randomNum < 62) {
                LogUtil.d("makeRandomNumEng", "int value : "+(randomNum - 36 + 97) + ", char value : "+(char) (randomNum - 36 + 97));
                charArray[i] = (char) (randomNum - 36 + 97);
            }
        }

        return new String(charArray);
    }

}
