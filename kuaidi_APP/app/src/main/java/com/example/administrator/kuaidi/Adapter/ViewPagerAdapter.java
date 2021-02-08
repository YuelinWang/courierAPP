package com.example.administrator.kuaidi.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private String[] dataList;
    private ArrayList<Fragment> arrayList;
    public ViewPagerAdapter(String[] dataList, ArrayList<Fragment> arrayList, FragmentManager fm) {
        super(fm);
        if(dataList == null){
            dataList = new String[]{"1","2","3"};
        }
        this.dataList = dataList;

        if(arrayList == null){
            arrayList = new ArrayList<>();
        }
        this.arrayList = arrayList;
    }

    @Override
    public Fragment getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return dataList[position];
    }
}
