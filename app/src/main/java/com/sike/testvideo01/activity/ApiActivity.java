package com.sike.testvideo01.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.sike.testvideo01.R;

public class ApiActivity extends AppCompatActivity {

    private static final String VOD_URL =
            "http://mov.bn.netease.com/open-movie/nos/flv/2017/01/03/SC8U8K7BC_hd.flv";

    // 拉流地址
    private static final String LIVE_URL =
            "rtmp://live.hkstv.hk.lxdns.com/live/hks";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.str_api);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

}
