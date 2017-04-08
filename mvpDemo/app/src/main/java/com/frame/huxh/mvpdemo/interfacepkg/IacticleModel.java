package com.frame.huxh.mvpdemo.interfacepkg;

import com.frame.huxh.mvpdemo.bean.ActicleBean;

import org.reactivestreams.Subscriber;

/**
 * Created by Administrator on 2017/4/8.
 */

public interface IacticleModel {
    void requestArticleData(Subscriber<ActicleBean> subscriber);
}
