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
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.naver.gfpsdk.GenderType;
import com.naver.gfpsdk.GfpSdk;
import com.naver.gfpsdk.provider.DfpProviderOptions;
import com.naver.gfpsdk.provider.FanProviderOptions;
import com.naver.gfpsdk.provider.NdaProviderOptions;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prepareSdk();

        if (savedInstanceState == null) {
            Fragment mainFragment = new MainMenuFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragment_container, mainFragment);
            transaction.commit();
        }
    }

    /**
     * Following codes are related with common setting. Please set 'SdkProperties' in Application
     * level for better performance.
     */
    private void prepareSdk() {
        GfpSdk.setSdkProperties(
                GfpSdk.getSdkProperties()
                        .buildUpon()
                        // if you enable DFP (Google) ads
                        .addProviderOptions(
                                new DfpProviderOptions.Builder()
                                        // for test (not for production level)
                                        .setTestMode(true)
                                        .build())
                        // if you enable FAN (Meta/Facebook) ads
                        .addProviderOptions(
                                new FanProviderOptions.Builder()
                                        // for test (not for production level)
                                        .setTestMode(true)
                                        .build())
                        // if you enable Naver DSP ads
                        .addProviderOptions(new NdaProviderOptions.Builder().build())
                        // timeout for 'ImageBanner' type
                        .bannerAdRequestTimeout(60_000L)
                        // timeout for 'NativeBanner' and 'SmartChannel' types
                        .unifiedAdRequestTimeout(60_000L)
                        .build());

        // It will be used in targeting rule. please contact NAM admin before using this.
        GfpSdk.setUserProperties(
                GfpSdk.getUserProperties()
                        .buildUpon()
                        // if you have own service id, can track an error log with it.
                        // .id("test_id")
                        .yob(1987)
                        .country("KR")
                        .language("KO")
                        .gender(GenderType.MALE)
                        .build());
    }
}
