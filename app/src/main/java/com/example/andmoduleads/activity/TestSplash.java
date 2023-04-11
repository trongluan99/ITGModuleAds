package com.example.andmoduleads.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ads.control.admob.AppOpenManager;
import com.ads.control.ads.ITGAd;
import com.ads.control.funtion.AdCallback;
import com.example.andmoduleads.R;

public class TestSplash extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        /*AppOpenManager.getInstance().loadSplashOpenHighFloor(TestSplash.class, this,
                "ca-app-pub-3940256099942544/3419835294",
                "ca-app-pub-3940256099942544/3419835294",
                "ca-app-pub-3940256099942544/3419835294", 25000, new AdCallback() {
                    @Override
                    public void onNextAction() {
                        super.onNextAction();
                        startActivity(new Intent(TestSplash.this, MainActivity.class));
                    }
                });*/

        ITGAd.getInstance().loadSmartBanner(this, getString(R.string.admod_banner_id));
    }
}
