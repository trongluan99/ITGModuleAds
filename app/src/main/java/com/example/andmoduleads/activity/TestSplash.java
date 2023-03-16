package com.example.andmoduleads.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ads.control.ads.AperoAd;
import com.ads.control.ads.AperoAdCallback;
import com.example.andmoduleads.R;

public class TestSplash extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        AperoAd.getInstance().loadSplashInterstitialAdsHighFloor(TestSplash.this,
                "ca-app-pub-3940256099942544/1033173712", "ca-app-pub-3940256099942544/1033173712",
                25000, 15000, new AperoAdCallback() {
                    @Override
                    public void onNextAction() {
                        super.onNextAction();
                        startActivity(new Intent(TestSplash.this, MainActivity.class));
                    }
                });
    }
}
