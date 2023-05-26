/*
 * NAM(Naver Ad Manager) SDK for Android
 *
 * Copyright 2022-present NAVER Corp.
 * All rights reserved.
 *
 * Unauthorized use, modification and redistribution of this software are strongly prohibited.
 */
package com.naver.namexample.sample;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.naver.gfpsdk.AdParam;
import com.naver.gfpsdk.BannerAdListener;
import com.naver.gfpsdk.BannerViewLayoutType;
import com.naver.gfpsdk.GfpBannerAd;
import com.naver.gfpsdk.GfpBannerAdOptions;
import com.naver.gfpsdk.GfpBannerAdView;
import com.naver.gfpsdk.GfpError;
import com.naver.gfpsdk.HostParam;
import com.naver.namexample.R;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ImageBannerFragment extends Fragment {
    private static final String AD_UNIT_ID = "AOS_nw_banner-N345765840";

    private GfpBannerAdView bannerAdView;
    private final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault());
    private TextView logTextView;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_banner, container, false);
        RelativeLayout bannerAdContainer = view.findViewById(R.id.banner_container);
        logTextView = view.findViewById(R.id.log_text_view);

        AdParam adParam = new AdParam.Builder().setAdUnitId(AD_UNIT_ID).build();

        bannerAdView = new GfpBannerAdView(requireActivity(), adParam);
        GfpBannerAdOptions bannerAdOptions =
                new GfpBannerAdOptions.Builder()
                        .setBannerViewLayoutType(BannerViewLayoutType.FLUID_WIDTH)
                        .setHostParam(
                                new HostParam.Builder()
                                        .addMetaParam("theme", "dark")
                                        // if you have specific dark mode, few ad will change it's
                                        // color (some button or
                                        // background).
                                        // not for everyone.
                                        .build())
                        .build();
        bannerAdView.setBannerAdOptions(bannerAdOptions);
        bannerAdContainer.addView(bannerAdView);

        bannerAdView.setAdListener(
                new BannerAdListener() {
                    @Override
                    public void onAdLoaded(GfpBannerAd ad) {
                        logTextView.append(
                                String.format(
                                        "[%s] AD Loaded.%n    AdProviderName: %s Size: %s%n",
                                        sdf.format(new Date()),
                                        ad.getAdProviderName(),
                                        bannerAdView.getBannerAdSize()));
                    }

                    @Override
                    public void onAdClicked(GfpBannerAd ad) {
                        logTextView.append(
                                String.format("[%s] AD Clicked.%n", sdf.format(new Date())));
                    }

                    @Override
                    public void onAdImpression(GfpBannerAd ad) {
                        logTextView.append(
                                String.format("[%s] AD impression.%n", sdf.format(new Date())));
                    }

                    @Override
                    public void onAdSizeChanged(GfpBannerAd ad) {
                        logTextView.append(
                                String.format(
                                        "[%s] AD SizeChanged.%n    Size: %s%n",
                                        sdf.format(new Date()), bannerAdView.getBannerAdSize()));
                    }

                    @Override
                    public void onError(GfpBannerAd ad, GfpError error) {
                        logTextView.append(
                                String.format(
                                        Locale.US,
                                        "[%s] Error occurred.%n    code[%d]%n    subCode[%s]%n    message[%s]%n",
                                        sdf.format(new Date()),
                                        error.getErrorCode(),
                                        error.getErrorSubCode(),
                                        error.getErrorMessage()));
                        Log.e("ImageBannerFragment", ad.getResponseInfo().toString());
                    }
                });
        logTextView.append(String.format("[%s] AD Requested.%n", sdf.format(new Date())));
        bannerAdView.loadAd();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (bannerAdView != null) {
            bannerAdView.destroy();
        }
    }
}
