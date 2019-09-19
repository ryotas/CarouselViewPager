package com.feelandmakeit.widget.carouselviewpager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

public class CarouselViewPager extends ViewPager {

    private CarouselPagerAdapter wrappedAdapter;
    private boolean isSmoothScroll;

    public CarouselViewPager(Context context) {
        super(context);
        super.addOnPageChangeListener(pageChangeListener);
    }

    public CarouselViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        super.addOnPageChangeListener(pageChangeListener);
    }

    @Override
    public void setAdapter(@Nullable PagerAdapter adapter) {
        wrappedAdapter = new CarouselPagerAdapter(adapter);
        super.setAdapter(wrappedAdapter);
        if (adapter instanceof FragmentPagerAdapter || adapter instanceof FragmentStatePagerAdapter) {
            setOffscreenPageLimit(wrappedAdapter.getCount());
        }
        setCurrentItem(innerPosToCarouselPos(0), false);
    }

    @Nullable
    @Override
    public PagerAdapter getAdapter() {
        return wrappedAdapter.getInnerAdapter();
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(innerPosToCarouselPos(item));
    }

    @Override
    public void addOnPageChangeListener(final @NonNull OnPageChangeListener listener) {
        removeOnPageChangeListener(pageChangeListener);
        OnPageChangeListener wrappedListener = new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float offset, int offsetPixels) {
                listener.onPageScrolled(carouselPosToInnerPos(position), offset, offsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                listener.onPageSelected(carouselPosToInnerPos(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                listener.onPageScrollStateChanged(state);
            }
        };
        super.addOnPageChangeListener(wrappedListener);
        setCurrentItem(0, false);
    }

    private ViewPager.OnPageChangeListener pageChangeListener = new SimpleOnPageChangeListener(){
        private Runnable action;

        @Override
        public void onPageSelected(int position) {
            if (position == 0) {
                action = setAction(innerPosToCarouselPos(wrappedAdapter.getInnerAdapter().getCount() - 1));
            } else if (position == wrappedAdapter.getCount() - 1) {
                action = setAction(innerPosToCarouselPos(0));
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == SCROLL_STATE_IDLE) {
                if (action != null) {
                    action.run();
                    action = null;
                }
            }
        }

        private Runnable setAction(final int item) {
            return new Runnable() {
                @Override
                public void run() {
                    setCurrentItem(item, false);
                }
            };
        }
    };

    /**
     * Change inner wrappedAdapter position to carousel wrappedAdapter position
     * @param position Inner wrappedAdapter position
     * @return Carousel wrappedAdapter position
     */
    private int innerPosToCarouselPos(int position) {
        return position + 1;
    }

    /**
     * Change carousel wrappedAdapter position to inner wrappedAdapter position
     * @param position Carousel wrappedAdapter position
     * @return Inner wrappedAdapter position
     */
    private int carouselPosToInnerPos(int position) {
        if (position == 0) {
            return wrappedAdapter.getInnerAdapter().getCount() - 1;
        } else if(position == wrappedAdapter.getCount() - 1) {
            return 0;
        } else {
            return position - 1;
        }
    }
}
