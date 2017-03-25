package com.frame.huxh.evenbusdemo.evenbus;

import java.util.List;

/**
 * Created by Administrator on 2017/3/25.
 */

public class OtherMessageEven {
    private List<Object> mObjectList ;
    private int type ;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public OtherMessageEven(List<Object> objectList, String message, int type) {
        mObjectList = objectList;
        this.message = message;
        this.type = type;
    }

    private String message;

    public List<Object> getObjectList() {
        return mObjectList;
    }

    public void setObjectList(List<Object> objectList) {
        mObjectList = objectList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
