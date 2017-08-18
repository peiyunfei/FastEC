package com.pyf.latte.ec.main.index.detail;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/8/18
 */
public class TabPagerAdapter extends FragmentStatePagerAdapter {

    private final List<String> TABS_NAME = new ArrayList<>();
    private final ArrayList<ArrayList<String>> PICTURES = new ArrayList<>();

    public TabPagerAdapter(FragmentManager fm, JSONObject data) {
        super(fm);
        JSONArray tabs = data.getJSONArray("tabs");
        int size = tabs.size();
        for (int i = 0; i < size; i++) {
            JSONObject jsonObject = tabs.getJSONObject(i);
            String name = jsonObject.getString("name");
            TABS_NAME.add(name);
            JSONArray jsonArray = jsonObject.getJSONArray("pictures");
            int arraySize = jsonArray.size();
            ArrayList<String> eachPicture = new ArrayList<>();
            for (int j = 0; j < arraySize; j++) {
                String pictureUrl = jsonArray.getString(j);
                eachPicture.add(pictureUrl);
            }
            PICTURES.add(eachPicture);
        }
        Log.i("TAG", "PICTURES.size:" + PICTURES.size());
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return ImageDelegate.create(PICTURES.get(0));
        } else if (position == 1) {
            return ImageDelegate.create(PICTURES.get(1));
        }
        return null;
    }

    @Override
    public int getCount() {
        return TABS_NAME.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TABS_NAME.get(position);
    }
}
