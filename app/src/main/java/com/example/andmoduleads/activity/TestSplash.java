package com.example.andmoduleads.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ads.control.funtion.AdCallback;
import com.ads.control.ironsource.AppIronSource;
import com.example.andmoduleads.R;
import com.google.android.gms.ads.interstitial.InterstitialAd;

public class TestSplash extends AppCompatActivity {
    private InterstitialAd mInterHighFloor;
    private InterstitialAd mInterAllPrice;

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

        AppIronSource.getInstance().init(TestSplash.this, "85460dcd", true);
        AppIronSource.getInstance().loadBanner(this);
        AppIronSource.getInstance().loadSplashInterstitial(this, new AdCallback() {
            @Override
            public void onNextAction() {
                super.onNextAction();
                startActivity(new Intent(TestSplash.this, MainActivity.class));
            }
        }, 30000);

        /*ITGAd.getInstance().loadBanner(this, getString(R.string.admod_banner_id), new ITGAdCallback(){
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                Log.e("TAG", "onAdLoaded: " );
            }

            @Override
            public void onAdFailedToLoad(@Nullable ApAdError adError) {
                super.onAdFailedToLoad(adError);
                Log.e("TAG", "onAdFailedToLoad: " );
            }
        });

        Admob.getInstance().getInterstitialAds(this,
                "ca-app-pub-3940256099942544/1033173712",
                "ca-app-pub-3940256099942544/1033173712", new AdCallback() {
                    @Override
                    public void onInterstitialLoad(@Nullable InterstitialAd interstitialAdHighFloor, @Nullable InterstitialAd interstitialAdAllPrice) {
                        super.onInterstitialLoad(interstitialAdHighFloor, interstitialAdAllPrice);
                        mInterHighFloor = interstitialAdHighFloor;
                        mInterAllPrice = interstitialAdAllPrice;

                        if (mInterHighFloor != null) {
                            Log.e("TAGXXXXX", "onInterstitialLoad: mInterHighFloor");
                        }

                        if (mInterAllPrice != null) {
                            Log.e("TAGXXXXX", "onInterstitialLoad: mInterAllPrice");
                        }

                    }
                });*/
    }
}
