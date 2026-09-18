/*
 * NAM(Naver Ad Manager) SDK for Android
 *
 * Copyright 2022-present NAVER Corp.
 * All rights reserved.
 *
 * Unauthorized use, modification and redistribution of this software are strongly prohibited.
 */
package com.naver.namexample;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.activity.SystemBarStyle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.naver.gfpsdk.GenderType;
import com.naver.gfpsdk.GfpSdk;
import com.naver.gfpsdk.mediation.DfpProviderOptions;
import com.naver.gfpsdk.mediation.FanProviderOptions;
import com.naver.gfpsdk.mediation.NdaProviderOptions;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Android 15(API 35) and above draw every app edge-to-edge. Opting in explicitly keeps
        // the behavior identical on older API levels.
        // The toolbar paints the status bar area with 'colorPrimary', which is dark in the day
        // theme and light in the night theme, so the system icons follow the opposite of it.
        EdgeToEdge.enable(
                this,
                isNightMode()
                        ? SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT)
                        : SystemBarStyle.dark(Color.TRANSPARENT));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.toolbar));
        applyWindowInsets();
        prepareSdk();

        if (savedInstanceState == null) {
            Fragment mainFragment = new MainMenuFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragment_container, mainFragment);
            transaction.commit();
        }
    }

    private boolean isNightMode() {
        return (getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK)
                == Configuration.UI_MODE_NIGHT_YES;
    }

    /**
     * Keeps the sample content out of the system bars and the display cutout, which the system no
     * longer does on behalf of an edge-to-edge app.
     */
    private void applyWindowInsets() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        ViewCompat.setOnApplyWindowInsetsListener(
                toolbar,
                (view, windowInsets) -> {
                    Insets insets = getContentInsets(windowInsets);
                    view.setPadding(insets.left, insets.top, insets.right, view.getPaddingBottom());
                    return windowInsets;
                });

        View container = findViewById(R.id.fragment_container);
        ViewCompat.setOnApplyWindowInsetsListener(
                container,
                (view, windowInsets) -> {
                    Insets insets = getContentInsets(windowInsets);
                    view.setPadding(insets.left, view.getPaddingTop(), insets.right, insets.bottom);
                    return windowInsets;
                });
    }

    private static Insets getContentInsets(WindowInsetsCompat windowInsets) {
        return windowInsets.getInsets(
                WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.displayCutout());
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
