package com.naver.namexample.sample;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.naver.gfpsdk.AdEventListener;
import com.naver.gfpsdk.AdParam;
import com.naver.gfpsdk.GfpAdLoader;
import com.naver.gfpsdk.GfpError;
import com.naver.gfpsdk.GfpNativeSimpleAdOptions;
import com.naver.gfpsdk.GfpNativeSimpleAdView;
import com.naver.gfpsdk.GfpResponseInfo;
import com.naver.gfpsdk.internal.util.Joiner;
import com.naver.namexample.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class SmartchannelFragment extends Fragment {
    private GfpAdLoader adLoader;
    private final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault());
    private TextView logTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_smartchannel, container, false);
        GfpNativeSimpleAdView nativeSimpleAdView = view.findViewById(R.id.native_simple_ad_view);
        logTextView = view.findViewById(R.id.log_text_view);

        AdParam adParam = new AdParam.Builder()
                .setAdUnitId("YOUR_UNIT_ID")
                .build();

        adLoader = new GfpAdLoader.Builder(requireActivity(), adParam)
                .withAdListener(new AdEventListener() {
                    @Override
                    public void onAdClicked() {
                        logTextView.append(String.format(
                                "[%s] AD Clicked.%n",
                                sdf.format(new Date())
                        ));
                    }

                    @Override
                    public void onAdImpression() {
                        logTextView.append(String.format(
                                "[%s] AD impression.%n",
                                sdf.format(new Date())
                        ));
                    }

                    @Override
                    public void onAdMetaChanged(Map<String, String> params) {
                        if (params != null && !params.isEmpty()) {
                            Joiner.MapJoiner joiner = Joiner.on(',').withKeyValueSeparator(":");
                            logTextView.append(String.format(
                                    "[%s] AD MetaChanged.%n    Value: %s%n",
                                    sdf.format(new Date()),
                                    joiner.join(params)
                            ));
                        }
                    }

                    @Override
                    public void onError(GfpError error, GfpResponseInfo responseInfo) {
                        logTextView.append(String.format(
                                "[%s] Error occurred.%n    code[%d]%n    subCode[%s]%n    message[%s]%n",
                                sdf.format(new Date()),
                                error.getErrorCode(),
                                error.getErrorSubCode(),
                                error.getErrorMessage()
                        ));
                        Log.e("SmartchannelFragment", responseInfo.toString());
                    }
                })
                .withNativeSimpleAd(
                        new GfpNativeSimpleAdOptions.Builder()
                                .setAdChoicePlacement(GfpNativeSimpleAdOptions.ADCHOICES_BOTTOM_LEFT)
                                .build(),
                        nativeSimpleAd -> {
                            logTextView.append(String.format(
                                    "[%s] AD Loaded.%n    AdProviderName: %s%n",
                                    sdf.format(new Date()),
                                    nativeSimpleAd.getAdProviderName()
                            ));
                            nativeSimpleAdView.setNativeSimpleAd(nativeSimpleAd);
                        })
                .build();

        adLoader.loadAd();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (adLoader != null) {
            adLoader.cancel();
        }
    }
}
