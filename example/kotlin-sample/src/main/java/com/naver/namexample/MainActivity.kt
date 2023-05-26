/*
 * NAM(Naver Ad Manager) SDK for Android
 *
 * Copyright 2022-present NAVER Corp.
 * All rights reserved.
 *
 * Unauthorized use, modification and redistribution of this software are strongly prohibited.
 */
package com.naver.namexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.naver.gfpsdk.GenderType
import com.naver.gfpsdk.GfpSdk.getSdkProperties
import com.naver.gfpsdk.GfpSdk.getUserProperties
import com.naver.gfpsdk.GfpSdk.setSdkProperties
import com.naver.gfpsdk.GfpSdk.setUserProperties
import com.naver.gfpsdk.provider.DfpProviderOptions
import com.naver.gfpsdk.provider.FanProviderOptions
import com.naver.gfpsdk.provider.NdaProviderOptions

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSdkProperties(
            getSdkProperties().buildUpon()
                // if you enable DFP (Google) ads
                .addProviderOptions(
                    DfpProviderOptions.Builder()
                        // for test (not for production level)
                        .setTestMode(true).build(),
                )
                // if you enable FAN (Meta/Facebook) ads
                .addProviderOptions(
                    FanProviderOptions.Builder()
                        // for test (not for production level)
                        .setTestMode(true).build(),
                )
                // if you enable Naver DSP ads
                .addProviderOptions(
                    NdaProviderOptions.Builder()
                        .build(),
                )
                // timeout for 'ImageBanner' type
                .bannerAdRequestTimeout(60000L)
                // timeout for 'NativeBanner' and 'Smartchannel' types
                .unifiedAdRequestTimeout(60000L)
                .build(),
        )

        // It will be used in targeting rule. please contact NAM admin before using this.
        setUserProperties(
            getUserProperties().buildUpon()
                // if you have own service id, can track an error log with it.
                // .id("test_id")
                .yob(1987)
                .country("KR")
                .language("KO")
                .gender(GenderType.MALE)
                .build(),
        )

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, MainMenuFragment())
            .commit()
    }
}
