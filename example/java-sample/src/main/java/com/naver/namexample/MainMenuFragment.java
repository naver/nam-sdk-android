/*
 * NAM(Naver Ad Manager) SDK for Android
 *
 * Copyright 2022-present NAVER Corp.
 * All rights reserved.
 *
 * Unauthorized use, modification and redistribution of this software are strongly prohibited.
 */
package com.naver.namexample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;
import com.naver.namexample.sample.ImageBannerFragment;
import com.naver.namexample.sample.InStreamFragment;
import com.naver.namexample.sample.NativeBannerFragment;
import com.naver.namexample.sample.SmartChannelFragment;

public class MainMenuFragment extends ListFragment {
    private final SampleAdInfo[] adList =
            new SampleAdInfo[] {
                new SampleAdInfo("이미지형 배너", SampleAdType.IMAGE_BANNER),
                new SampleAdInfo("네이티브형 배너", SampleAdType.NATIVE_BANNER),
                new SampleAdInfo("스마트채널", SampleAdType.SMART_CHANNEL),
                new SampleAdInfo("동영상형", SampleAdType.INSTREAM)
            };

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_menu, container, false);
        ArrayAdapter<SampleAdInfo> adapter =
                new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, adList);
        setListAdapter(adapter);
        return rootView;
    }

    @Override
    public void onListItemClick(ListView listView, @NonNull View view, int position, long id) {
        SampleAdInfo adInfo = (SampleAdInfo) listView.getItemAtPosition(position);
        Fragment newFragment = null;

        switch (adInfo.getAdType()) {
            case IMAGE_BANNER:
                newFragment = new ImageBannerFragment();
                break;
            case NATIVE_BANNER:
                newFragment = new NativeBannerFragment();
                break;
            case SMART_CHANNEL:
                newFragment = new SmartChannelFragment();
                break;
            case INSTREAM:
                newFragment = new InStreamFragment();
            default:
                Toast.makeText(
                                this.getContext(),
                                "[ERROR] Please check again your ad type",
                                Toast.LENGTH_SHORT)
                        .show();
        }

        if (newFragment != null) {
            FragmentTransaction transaction =
                    requireActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    enum SampleAdType {
        IMAGE_BANNER,
        NATIVE_BANNER,
        SMART_CHANNEL,
        INSTREAM
    }

    static class SampleAdInfo {
        private final String adName;
        private final SampleAdType adType;

        public SampleAdInfo(String adName, SampleAdType adType) {
            this.adName = adName;
            this.adType = adType;
        }

        public SampleAdType getAdType() {
            return adType;
        }

        @NonNull
        @Override
        public String toString() {
            return adName;
        }
    }
}
