package com.zqu.pa.utils;

import java.text.SimpleDateFormat;

public class DateUtil {

    public static String formatTime(Long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(time);
    }
}
