package com.frame.huxh.rxandroiddemo.rxJava_Demo;

import java.util.List;

/**
 * Created by Administrator on 2017/3/30.
 */

public class CommonBean {
    /**
     * account : 115.6
     * frozenAccount : 112.2
     * integral : 0
     * returnCode : 0000
     * returnList : []
     * returnMap : null
     * returnMessage : 用户登录成功
     * token : 4CEBC2904786DDAB9BCAB669F574674E
     * userName : 随便
     * thirdAccId:2ae148e107d24b39b4dd6cb3eb66124f
     * thirdToken:e4932705ac85468bb1c54aa1760f3f0c
     * thirdUserName:huxh01
     *
     */

    private String account;
    private String frozenAccount;

    @Override
    public String toString() {
        return "CommonBean{" +
                "account='" + account + '\'' +
                ", frozenAccount='" + frozenAccount + '\'' +
                ", integral='" + integral + '\'' +
                ", returnCode='" + returnCode + '\'' +
                ", returnMap=" + returnMap +
                ", returnMessage='" + returnMessage + '\'' +
                ", token='" + token + '\'' +
                ", userName='" + userName + '\'' +
                ", returnList=" + returnList +
                ", thirdToken='" + thirdToken + '\'' +
                ", thirdUserName='" + thirdUserName + '\'' +
                ", thirdAccId='" + thirdAccId + '\'' +
                '}';
    }

    private String integral;
    private String returnCode;
    private Object returnMap;
    private String returnMessage;
    private String token;
    private String userName;
    private List<?> returnList;
    private String thirdToken;
    private String thirdUserName;
    private String thirdAccId;

    public void setThirdUserName(String thirdUserName) {
        this.thirdUserName = thirdUserName;
    }


    public String getThirdToken() {
        return thirdToken;
    }

    public void setThirdToken(String thirdToken) {
        this.thirdToken = thirdToken;
    }

    public String getThirdAccId() {
        return thirdAccId;
    }

    public void setThirdAccId(String thirdAccId) {
        this.thirdAccId = thirdAccId;
    }

    public String getThirdUserName() {

        return thirdUserName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getFrozenAccount() {
        return frozenAccount;
    }

    public void setFrozenAccount(String frozenAccount) {
        this.frozenAccount = frozenAccount;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public Object getReturnMap() {
        return returnMap;
    }

    public void setReturnMap(Object returnMap) {
        this.returnMap = returnMap;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<?> getReturnList() {
        return returnList;
    }

    public void setReturnList(List<?> returnList) {
        this.returnList = returnList;
    }
}
