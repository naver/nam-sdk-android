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
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.naver.gfpsdk.AdParam;
import com.naver.gfpsdk.GfpError;
import com.naver.gfpsdk.GfpRewardedAd;
import com.naver.gfpsdk.GfpRewardedAdManager;
import com.naver.gfpsdk.RewardedAdListener;
import com.naver.namexample.R;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RewardedFragment extends Fragment {
    private static final String AD_UNIT_ID = "aos_rv_test";
    private GfpRewardedAdManager rewardedAdManager;
    private Button showButton;
    private final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault());
    private TextView logTextView;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rewarded, container, false);
        showButton = view.findViewById(R.id.show_button);
        showButton.setEnabled(false);

        showButton.setOnClickListener(
                v -> {
                    if (rewardedAdManager.isAdInvalidated()) {
                        logTextView.append(
                                String.format("[%s] Ad is not valid.%n", sdf.format(new Date())));
                        return;
                    }

                    rewardedAdManager.showAd(requireActivity());
                });

        logTextView = view.findViewById(R.id.log_text_view);

        AdParam adParam = new AdParam.Builder().setAdUnitId(AD_UNIT_ID).build();

        rewardedAdManager = new GfpRewardedAdManager(requireActivity(), adParam);
        rewardedAdManager.setAdListener(
                new RewardedAdListener() {
                    @Override
                    public void onAdLoaded(@NonNull GfpRewardedAd ad) {
                        logTextView.append(
                                String.format(
                                        "[%s] AD Loaded.%n    AdProviderName: %s%n",
                                        sdf.format(new Date()), ad.getAdProviderName()));
                        showButton.setEnabled(true);
                    }

                    @Override
                    public void onAdStarted(@NonNull GfpRewardedAd ad) {
                        logTextView.append(
                                String.format("[%s] AD Started.%n", sdf.format(new Date())));
                    }

                    @Override
                    public void onAdClicked(@NonNull GfpRewardedAd ad) {
                        logTextView.append(
                                String.format("[%s] AD Clicked.%n", sdf.format(new Date())));
                    }

                    @Override
                    public void onAdClosed(@NonNull GfpRewardedAd ad) {
                        logTextView.append(
                                String.format("[%s] AD Closed.%n", sdf.format(new Date())));
                        showButton.setEnabled(false);
                    }

                    @Override
                    public void onAdCompleted(@NonNull GfpRewardedAd ad) {
                        logTextView.append(
                                String.format("[%s] AD Completed.%n", sdf.format(new Date())));
                    }

                    @Override
                    public void onError(@NonNull GfpRewardedAd ad, @NonNull GfpError error) {
                        logTextView.append(
                                String.format(
                                        Locale.US,
                                        "[%s] Error occurred.%n    code[%d]%n    subCode[%s]%n    message[%s]%n",
                                        sdf.format(new Date()),
                                        error.getErrorCode(),
                                        error.getErrorSubCode(),
                                        error.getErrorMessage()));
                        Log.e("RewardedFragment", ad.getResponseInfo().toString());
                    }
                });

        rewardedAdManager.loadAd();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (rewardedAdManager != null) {
            rewardedAdManager.destroy();
        }
    }
}
