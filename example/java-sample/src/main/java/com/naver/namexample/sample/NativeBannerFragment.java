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
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.naver.gfpsdk.AdEventListener;
import com.naver.gfpsdk.AdParam;
import com.naver.gfpsdk.GfpAdChoicesView;
import com.naver.gfpsdk.GfpAdLoader;
import com.naver.gfpsdk.GfpError;
import com.naver.gfpsdk.GfpMediaView;
import com.naver.gfpsdk.GfpNativeAd;
import com.naver.gfpsdk.GfpNativeAdOptions;
import com.naver.gfpsdk.GfpNativeAdView;
import com.naver.gfpsdk.GfpResponseInfo;
import com.naver.gfpsdk.Image;
import com.naver.namexample.R;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NativeBannerFragment extends Fragment {
    private static final String AD_UNIT_ID = "INSERT YOUR AD UNIT ID";

    private GfpAdLoader adLoader;
    private RelativeLayout nativeContainer;
    private final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault());
    private TextView logTextView;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_native_banner, container, false);
        nativeContainer = view.findViewById(R.id.native_container);
        logTextView = view.findViewById(R.id.log_text_view);

        AdParam adParam = new AdParam.Builder().setAdUnitId(AD_UNIT_ID).build();

        GfpNativeAdOptions nativeAdOptions =
                new GfpNativeAdOptions.Builder().setHasMediaView(true).build();

        adLoader =
                new GfpAdLoader.Builder(requireActivity(), adParam)
                        .withTimeoutMillis(60_000L)
                        .withAdListener(
                                new AdEventListener() {
                                    @Override
                                    public void onAdClicked() {
                                        logTextView.append(
                                                String.format(
                                                        "[%s] AD Clicked.%n",
                                                        sdf.format(new Date())));
                                    }

                                    @Override
                                    public void onAdImpression() {
                                        logTextView.append(
                                                String.format(
                                                        "[%s] AD impression.%n",
                                                        sdf.format(new Date())));
                                    }

                                    @Override
                                    public void onAdMuted() {
                                        logTextView.append(
                                                String.format(
                                                        "[%s] AD Muted.%n",
                                                        sdf.format(new Date())));
                                    }

                                    @Override
                                    public void onError(
                                            GfpError error, GfpResponseInfo responseInfo) {
                                        logTextView.append(
                                                String.format(
                                                        Locale.US,
                                                        "[%s] Error occurred.%n    code[%d]%n    subCode[%s]%n    message[%s]%n",
                                                        sdf.format(new Date()),
                                                        error.getErrorCode(),
                                                        error.getErrorSubCode(),
                                                        error.getErrorMessage()));
                                        Log.e("NativeBannerFragment", responseInfo.toString());
                                    }
                                })
                        .withNativeAd(
                                nativeAdOptions,
                                nativeAd -> {
                                    logTextView.append(
                                            String.format(
                                                    "[%s] AD Loaded.%n    AdProviderName: %s%n",
                                                    sdf.format(new Date()),
                                                    nativeAd.getAdProviderName()));
                                    inflateAd(nativeAd);
                                })
                        .build();

        logTextView.append(String.format("[%s] AD Requested.%n", sdf.format(new Date())));
        adLoader.loadAd();

        return view;
    }

    private void inflateAd(GfpNativeAd nativeAd) {
        nativeContainer.removeAllViews();
        GfpNativeAdView nativeAdView =
                (GfpNativeAdView)
                        getLayoutInflater()
                                .inflate(R.layout.content_native_ad, nativeContainer, false);
        nativeContainer.addView(nativeAdView);

        FrameLayout assetsContainer = nativeAdView.findViewById(R.id.assets_container);
        GfpMediaView mediaView = nativeAdView.findViewById(R.id.ad_media);
        GfpAdChoicesView adChoicesView = nativeAdView.findViewById(R.id.ad_choices_view);
        ImageView iconView = nativeAdView.findViewById(R.id.ad_app_icon);
        TextView titleView = nativeAdView.findViewById(R.id.ad_headline);
        TextView bodyView = nativeAdView.findViewById(R.id.ad_body);
        TextView advertiserView = nativeAdView.findViewById(R.id.ad_advertiser);
        TextView socialContextView = nativeAdView.findViewById(R.id.ad_social_context);
        Button callToActionButton = nativeAdView.findViewById(R.id.ad_call_to_action);

        nativeAdView.setAssetsContainer(assetsContainer);
        nativeAdView.setMediaView(mediaView);
        nativeAdView.setAdChoicesView(adChoicesView);
        nativeAdView.setIconView(iconView);
        nativeAdView.setTitleView(titleView);
        nativeAdView.setBodyView(bodyView);
        nativeAdView.setAdvertiserView(advertiserView);
        nativeAdView.setSocialContextView(socialContextView);
        nativeAdView.setCallToActionView(callToActionButton);

        titleView.setText(nativeAd.getTitle());
        bodyView.setText(nativeAd.getBody());
        callToActionButton.setText(nativeAd.getCallToAction());
        advertiserView.setText(nativeAd.getAdvertiserName());

        Image icon = nativeAd.getIcon();
        if (icon != null) {
            iconView.setImageDrawable(icon.getDrawable());
            iconView.setVisibility(View.VISIBLE);
        } else {
            iconView.setVisibility(View.GONE);
        }

        if (nativeAd.getSocialContext() != null) {
            socialContextView.setText(nativeAd.getSocialContext());
            socialContextView.setVisibility(View.VISIBLE);
        } else {
            socialContextView.setVisibility(View.GONE);
        }

        nativeAdView.setNativeAd(nativeAd);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (adLoader != null) {
            adLoader.cancel();
        }
    }
}
