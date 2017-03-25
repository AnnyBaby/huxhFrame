package com.frame.huxh.evenbusdemo.evenbus;

/**
 * Created by Administrator on 2017/3/24.
 */

public class MessageEvent  {
    public String message;
    public int type;


    public MessageEvent (String message ,int type){
        this.message = message;
        this.type = type;
    }
}
