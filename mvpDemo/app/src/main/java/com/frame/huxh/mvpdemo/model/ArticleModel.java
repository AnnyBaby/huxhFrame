package com.frame.huxh.mvpdemo.model;

import com.frame.huxh.mvpdemo.HttpService;

/**
 * Created by Administrator on 2017/4/6.
 */

public class ArticleModel {

    private HttpService mHttpService = new HttpService();

    //获取文章，也是我们的业务逻辑
    public void requestArticleData(){
        mHttpService.requsetArticleList();
    }
}
