package com.feelandmakeit.widget.carouselviewpager;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * Wrapped adapter class
 */
class CarouselPagerAdapter extends PagerAdapter {

    private PagerAdapter innerAdapter;

    CarouselPagerAdapter(PagerAdapter adapter) {
        innerAdapter = adapter;
    }

    PagerAdapter getInnerAdapter() {
        return innerAdapter;
    }

    @Override
    public int getCount() {
        // add the edge of pages
        return innerAdapter.getCount() + 2;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return innerAdapter.isViewFromObject(view, o);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        return innerAdapter.instantiateItem(container, getActualPosition(position));
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        innerAdapter.destroyItem(container, getActualPosition(position), object);
    }

    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        innerAdapter.setPrimaryItem(container, getActualPosition(position), object);
    }

    @Override
    public void startUpdate(@NonNull ViewGroup container) {
        innerAdapter.startUpdate(container);
    }

    @Override
    public void finishUpdate(@NonNull ViewGroup container) {
        innerAdapter.finishUpdate(container);
    }

    /**
     * Change this adapter position to inner adapter position
     * @param position this adapter position
     * @return inner adapter position
     */
    private int getActualPosition(int position) {
        if (position == 0) {
            // When the page on the left edge is reached
            return innerAdapter.getCount() - 1;
        } else if (position == getCount() - 1) {
            // When the page on the right edge is reached
            return 0;
        } else {
            return position - 1;
        }
    }
}
