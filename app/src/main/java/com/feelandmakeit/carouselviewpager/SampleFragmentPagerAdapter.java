package com.feelandmakeit.carouselviewpager;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {

    private String[] pageTitles = {"one", "two", "three", "four", "five"};

    public SampleFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return SampleFragment.newInstance(R.color.red);
            case 1:
                return SampleFragment.newInstance(R.color.green);
            case 2:
                return SampleFragment.newInstance(R.color.blue);
            case 3:
                return SampleFragment.newInstance(R.color.yellow);
            case 4:
                return SampleFragment.newInstance(R.color.sky);
        }
        return null;
    }

    @Override
    public int getCount() {
        return pageTitles.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitles[position];
    }
}
