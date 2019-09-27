package com.mocoplex.jarsample.banner;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mocoplex.adnext.AdlibManager;
import com.mocoplex.jarsample.R;

public class LargeBannerDynamicActivity extends AppCompatActivity {

    private AdlibManager adlibManager;

    private View adView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adlib_banner_dynamic_large);

        String key = "5625f9a70cf2ffe2e93dbd72";
        adlibManager = new AdlibManager(key);
        adlibManager.onCreate(this);
    }

    @Override
    protected void onResume() {
        adlibManager.onResume(this);
        super.onResume();
    }

    @Override
    protected void onPause() {
        adlibManager.onPause(this);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        adlibManager.onDestroy(this);
        super.onDestroy();
    }

    public void onClick(View v) {
        if (v.getId() == R.id.request) {
            requestAd();
        } else if (v.getId() == R.id.addview) {
            addView();
        }

    }

    // 띠배너 Dynamic 광고 요청
    private void requestAd() {
        Handler handler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                try {
                    switch (msg.what) {
                        case AdlibManager.DID_SUCCEED:
                            Log.d("ADNext", "[Dynamic] onReceiveAd ");
                            adView = (View) msg.obj;
                            break;
                        case AdlibManager.DID_ERROR:
                            Log.d("ADNext", "[Dynamic] onFailedToReceiveAd " + (String) msg.obj);
                            Toast.makeText(LargeBannerDynamicActivity.this, "광고수신 실패 :)", Toast.LENGTH_SHORT).show();
                            break;

                        case AdlibManager.DID_CLICK:
                            Log.d("ADNext", "[Dynamic] onClickAd");
                            break;
                    }
                } catch (Exception e) {

                }
            }

        };
        // 다이나믹 광고의 경우 미디에이션을 지원하지 않습니다
        // 띠배너 종류 사이즈 320 * 50 , 320 * 70
        if (adlibManager != null) adlibManager.requestDynamicBannerView(1024, 748, handler);
    }

    // 뷰가 노출 되어야하는 시점에 호출
    private void addView() {
        if (adView != null) {
            ViewGroup vg = (ViewGroup) findViewById(R.id.container);
            vg.addView(adView);
        }
    }
}
