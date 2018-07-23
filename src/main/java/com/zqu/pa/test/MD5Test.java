package com.zqu.pa.test;

import org.apache.shiro.crypto.hash.Md5Hash;

public class MD5Test {
    public static void main(String[] args){
        //MD5：盐+密码+次数
        Md5Hash md5Hash = new Md5Hash("789","789",1);
        String psw_MD5 = md5Hash.toString();
        System.out.println(psw_MD5);
    }
}
