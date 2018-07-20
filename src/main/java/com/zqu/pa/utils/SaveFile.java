package com.zqu.pa.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

public class SaveFile {
    
    /**
     * path为除文件名外的路径
     * 例：
     * HttpServletRequest request
     * MultipartHttpServletRequest mult = (MultipartHttpServletRequest) request;
     *               ---------------------服务器根路径---------------------
     * String path = mult.getSession().getServletContext().getRealPath("")+"file\\xxxx\\";
     * 
     * MultipartFile file = mult.getFile("xxx");//页面传来的对应file
     * fileName为文件名xxx.xx
     * 
     * @param path
     * @param fileName
     * @param file
     * @return
     */
    public static boolean SaveFile(String path ,String fileName, MultipartFile file) {
        FileOutputStream fileOutputStream = null;
        InputStream inputStream;
        try {
            inputStream = file.getInputStream();
            fileOutputStream = new FileOutputStream(new File(path, fileName));
            //上传
            IOUtils.copy(inputStream, fileOutputStream);
            
            /*
            //如果上传图片后需要压缩成400x400的处理
            if(type==1) {
                if(IMGUtil.compressPic(new File(path,fileName), 400, 400, path+fileName))
                    return true;
            }
             */
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null)
                    fileOutputStream.close();
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
