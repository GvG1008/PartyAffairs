package com.zqu.pa.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringTimeToLong {
    /**
     * 转换时间日期格式字串为long型  
     * @param time 格式为：yyyy-MM-dd HH:mm:ss的时间日期类型
     */
    public static Long convertTimeToLong(String time) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = sdf.parse(time);
            return date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

}
