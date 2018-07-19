package com.zqu.pa.common;

public class UserLoginException extends Exception {
    //异常信息
    private String message;
    
    public UserLoginException(String message){
        super(message);
        this.message = message;
        
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
