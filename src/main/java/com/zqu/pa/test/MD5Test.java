package com.zqu.pa.test;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class MD5Test {
    public static void main(String[] args){

        String hashAlgorithmName = "MD5"; //方法
        String credentials = "10011"; //加密前密码
        int hashIterations = 1;  //次数
        ByteSource credentialsSalt = ByteSource.Util.bytes("10010"); //盐值
        Object obj = new SimpleHash(hashAlgorithmName, credentials, credentialsSalt, hashIterations);
        System.out.println(obj);
    }
}
