package com.frame.huxh.rxandroid20demo.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/3/31.
 */

public class ToastUtils {
    public static void toast(Context context ,String str){
        Toast.makeText(context,str,Toast.LENGTH_SHORT).show();
    }
}
