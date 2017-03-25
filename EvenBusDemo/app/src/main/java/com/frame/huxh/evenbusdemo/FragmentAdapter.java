package com.frame.huxh.evenbusdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/25.
 */

public class FragmentAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> mFragmentList;
    public FragmentAdapter(FragmentManager fm , ArrayList<Fragment> fragmentList) {
        super(fm);
        this.mFragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return "";
    }
}
