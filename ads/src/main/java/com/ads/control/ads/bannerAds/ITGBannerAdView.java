package com.ads.control.ads.bannerAds;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ads.control.R;
import com.ads.control.ads.ITGAd;
import com.ads.control.ads.ITGAdCallback;
import com.ads.control.funtion.AdCallback;

public class ITGBannerAdView extends RelativeLayout {

    private String TAG = "ITGBannerAdView";

    public ITGBannerAdView(@NonNull Context context) {
        super(context);
        init();
    }

    public ITGBannerAdView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ITGBannerAdView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }


    public ITGBannerAdView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.layout_banner_control, this);
    }

    public void loadBanner(Activity activity, String idBanner) {
        loadBanner(activity, idBanner, new ITGAdCallback());
    }

    public void loadBanner(Activity activity, String idBanner, ITGAdCallback ITGAdCallback) {
        ITGAd.getInstance().loadBanner(activity, idBanner, ITGAdCallback);
    }

    public void loadInlineBanner(Activity activity, String idBanner, String inlineStyle) {
        ITGAd.getInstance().loadInlineBanner(activity, idBanner, inlineStyle);
    }

    public void loadInlineBanner(Activity activity, String idBanner, String inlineStyle, AdCallback adCallback) {
        ITGAd.getInstance().loadInlineBanner(activity, idBanner, inlineStyle, adCallback);
    }

    public void loadBannerFragment(Activity activity, String idBanner) {
        ITGAd.getInstance().loadBannerFragment(activity, idBanner, getRootView());
    }

    public void loadBannerFragment(Activity activity, String idBanner, AdCallback adCallback) {
        ITGAd.getInstance().loadBannerFragment(activity, idBanner, getRootView(), adCallback);
    }

    public void loadInlineBannerFragment(Activity activity, String idBanner, String inlineStyle) {
        ITGAd.getInstance().loadBannerInlineFragment(activity, idBanner, getRootView(), inlineStyle);
    }

    public void loadInlineBannerFragment(Activity activity, String idBanner, String inlineStyle, AdCallback adCallback) {
        ITGAd.getInstance().loadBannerInlineFragment(activity, idBanner, getRootView(), inlineStyle, adCallback);
    }
}