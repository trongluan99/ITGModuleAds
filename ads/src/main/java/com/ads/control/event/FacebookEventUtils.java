package com.ads.control.event;

import android.content.Context;
import android.os.Bundle;

import com.ads.control.config.ITGAdConfig;
import com.facebook.appevents.AppEventsLogger;

public class FacebookEventUtils {
    public static void logEventWithAds(Context context, Bundle params) {
        AppEventsLogger.newLogger(context).logEvent("paid_ad_impression", params);
    }

    static void logPaidAdImpressionValue(Context context, Bundle bundle, int mediationProvider) {
        if (mediationProvider == ITGAdConfig.PROVIDER_MAX)
            AppEventsLogger.newLogger(context).logEvent("max_paid_ad_impression_value", bundle);
        else
            AppEventsLogger.newLogger(context).logEvent("paid_ad_impression_value", bundle);
    }

    public static void logClickAdsEvent(Context context, Bundle bundle) {
        AppEventsLogger.newLogger(context).logEvent("event_user_click_ads", bundle);
    }

    public static void logCurrentTotalRevenueAd(Context context, String eventName, Bundle bundle) {
        AppEventsLogger.newLogger(context).logEvent(eventName, bundle);
    }

    public static void logTotalRevenue001Ad(Context context, Bundle bundle) {
        AppEventsLogger.newLogger(context).logEvent("paid_ad_impression_value_001", bundle);
    }
}
