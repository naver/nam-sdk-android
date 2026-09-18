/*
 * NAM(Naver Ad Manager) SDK for Android
 *
 * Copyright 2022-present NAVER Corp.
 * All rights reserved.
 *
 * Unauthorized use, modification and redistribution of this software are strongly prohibited.
 */
package com.naver.namexample

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.naver.ads.NasLogger
import com.naver.gfpsdk.GenderType
import com.naver.gfpsdk.GfpSdk.getSdkProperties
import com.naver.gfpsdk.GfpSdk.getUserProperties
import com.naver.gfpsdk.GfpSdk.setSdkProperties
import com.naver.gfpsdk.GfpSdk.setUserProperties
import com.naver.gfpsdk.mediation.DfpProviderOptions
import com.naver.gfpsdk.mediation.FanProviderOptions
import com.naver.gfpsdk.mediation.NdaProviderOptions

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Android 15(API 35) and above draw every app edge-to-edge. Opting in explicitly keeps
        // the behavior identical on older API levels.
        // The toolbar paints the status bar area with 'colorPrimary', which is dark in the day
        // theme and light in the night theme, so the system icons follow the opposite of it.
        enableEdgeToEdge(
            statusBarStyle = if (isNightMode()) {
                SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT)
            } else {
                SystemBarStyle.dark(Color.TRANSPARENT)
            }
        )
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById<Toolbar>(R.id.toolbar))
        applyWindowInsets()

        NasLogger.setLogLevel(NasLogger.LogLevel.DEBUG)
        // un-necessary on release

        setSdkProperties(
            getSdkProperties().buildUpon()
                // if you enable DFP (Google) ads
                .addProviderOptions(
                    DfpProviderOptions.Builder()
                        // for test (not for production level)
                        .setTestMode(true).build()
                )
                // if you enable FAN (Meta/Facebook) ads
                .addProviderOptions(
                    FanProviderOptions.Builder()
                        // for test (not for production level)
                        .setTestMode(true).build()
                )
                // if you enable Naver DSP ads
                .addProviderOptions(
                    NdaProviderOptions.Builder()
                        .build()
                )
                // timeout for 'ImageBanner' type
                .bannerAdRequestTimeout(60000L)
                // timeout for 'NativeBanner' and 'Smartchannel' types
                .unifiedAdRequestTimeout(60000L)
                .build()
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
                .build()
        )

        if (savedInstanceState != null) {
            return
        }

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, MainMenuFragment())
            .commit()
    }

    private fun isNightMode(): Boolean =
        resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK ==
            Configuration.UI_MODE_NIGHT_YES

    /**
     * Keeps the sample content out of the system bars and the display cutout, which the system no
     * longer does on behalf of an edge-to-edge app.
     */
    private fun applyWindowInsets() {
        val toolbar = findViewById<View>(R.id.toolbar)
        ViewCompat.setOnApplyWindowInsetsListener(toolbar) { view, windowInsets ->
            val insets = windowInsets.getInsets(
                WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout()
            )
            view.updatePadding(left = insets.left, top = insets.top, right = insets.right)
            windowInsets
        }

        val container = findViewById<View>(R.id.fragment_container)
        ViewCompat.setOnApplyWindowInsetsListener(container) { view, windowInsets ->
            val insets = windowInsets.getInsets(
                WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout()
            )
            view.updatePadding(left = insets.left, right = insets.right, bottom = insets.bottom)
            windowInsets
        }
    }
}
