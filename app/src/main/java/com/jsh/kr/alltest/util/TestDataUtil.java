package com.jsh.kr.alltest.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TestDataUtil {
    public static Date makeDate(String date) {
        SimpleDateFormat form = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        try {
            return form.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
