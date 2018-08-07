package com.zqu.pa.test;

import java.util.Date;

public class LeeTest {
    public static void main(String[] args) {
        Long nTime = new Date().getTime();
        String path = "/partyalbum/"+nTime+"/";
        System.out.println(path);
    }
}
