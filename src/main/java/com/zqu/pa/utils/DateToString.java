package com.zqu.pa.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateToString {

    public static String getDateString(String format, Date ts) {
        DateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(ts);
    }
}
