package com.zqu.pa.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class GetFilePath {

    /**
     * 获取当前时间精确到毫秒数+文件名xxx.xxx作为文件名
     * @param filename
     * @return
     */
    public static String getFileName(String filename) {

        filename = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()).toString()+"_"+filename;

        return filename;
    }
}
