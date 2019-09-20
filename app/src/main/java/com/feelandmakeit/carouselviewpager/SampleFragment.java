package com.feelandmakeit.carouselviewpager;

import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SampleFragment extends Fragment {

    public static SampleFragment newInstance(@ColorRes int colorRes) {
        SampleFragment fragment = new SampleFragment();
        Bundle args = new Bundle();
        args.putInt("COLOR", colorRes);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment, container, false);
        root.setBackgroundColor(ContextCompat.getColor(getContext(), getArguments().getInt("COLOR")));
        return root;
    }
}
