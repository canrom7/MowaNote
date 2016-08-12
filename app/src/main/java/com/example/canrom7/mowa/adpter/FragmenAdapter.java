package com.example.canrom7.mowa.adpter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Canrom7 on 2016/8/7 .
 * Mailbox canrom7@163.com .
 */
public class FragmenAdapter extends FragmentPagerAdapter{
    private List<Fragment> mFragmentList;

    public FragmenAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.mFragmentList=fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return this.mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return this.mFragmentList.size();
    }
}
