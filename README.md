
# ITGModuleAds
- Admob
- MAX Mediation(Applovin)
- Google Billing
- Adjust
- Appsflyer
- Firebase auto log tracking event, tROAS
# Import Ironsource
- Init IS trong SplashActivity
    String keyIS = "85460dcd" (Key Test)
    AppIronSource.getInstance().init(TestSplash.this, keyIS , true);
    
    // Load Inter Splash
    AppIronSource.getInstance().loadSplashInterstitial(this, new AdCallback() {
        @Override
        public void onNextAction() {
            super.onNextAction();
            startActivity(new Intent(this, MainActivity.class));
        }
    }, 30000);
        
    // Load Banner
    AppIronSource.getInstance().loadBanner(this);
    
    // Load Inter
    if(!AppIronSource.getInstance().isInterstitialReady()){
        AppIronSource.getInstance().loadInterstitial(this, new AdCallback());
    }
    
    // Show Inter
    if(AppIronSource.getInstance().isInterstitialReady()){
            AppIronSource.getInstance().showInterstitial(this, new AdCallback(){
                @Override
                public void onNextAction() {
                    super.onNextAction();
                    startActivity(new Intent(this, MainActivity.class));
                }
            });
        }else{
            startActivity(new Intent(this, MainActivity.class));
        }

# Import Adjust trong My Application
~~~
    AdjustConfig adjustConfig = new AdjustConfig(true, ADJUST_TOKEN);
    itgAdConfig.setAdjustConfig(adjustConfig);
~~~
# Import Module
~~~
    maven { url 'https://jitpack.io' }
    implementation 'com.github.trongluan99:ITGModuleAds:1.0.17'
    implementation 'com.google.android.play:core:1.10.3'
    implementation 'com.facebook.shimmer:shimmer:0.5.0'
    implementation 'com.google.android.gms:play-services-ads:22.0.0'
    implementation 'androidx.multidex:multidex:2.0.1'
~~~  
# Import Mediation Admob
~~~
    maven {url 'https://android-sdk.is.com/'}
    maven {url 'https://artifact.bytedance.com/repository/pangle/'}
    maven {url 'https://dl-maven-android.mintegral.com/repository/mbridge_android_sdk_oversea'}
    
    // Mediation
    implementation 'com.google.ads.mediation:applovin:11.8.2.0'
    implementation 'com.google.ads.mediation:pangle:5.0.1.0.0'
    implementation 'com.google.ads.mediation:facebook:6.13.7.0'
    implementation 'com.google.ads.mediation:ironsource:7.3.0.0'
    implementation 'com.unity3d.ads:unity-ads:4.6.0'
    implementation 'com.google.ads.mediation:unity:4.6.1.0'
    implementation 'com.google.ads.mediation:vungle:6.12.1.0'
    implementation 'com.google.ads.mediation:mintegral:16.4.21.0'
    
    // Setup trong MyApplication
    MBridgeSDK sdk = MBridgeSDKFactory.getMBridgeSDK();
    sdk.setConsentStatus(this, MBridgeConstans.IS_SWITCH_ON);
    
     Admob.getInstance().setAppLovin(true)
     Admob.getInstance().setColony(true)
     Admob.getInstance().setFan(true)
     Admob.getInstance().setVungle(true)
~~~
# Summary
* [Setup ITGAd](#setup_ITGad)
    * [Setup id ads](#set_up_ads)
    * [Config ads](#config_ads)
    * [Ads Formats](#ads_formats)

* [Billing App](#billing_app)
* [Ads rule](#ads_rule)

# <a id="setup_ITGad"></a>Setup ITGAd
## <a id="set_up_ads"></a>Setup enviroment with id ads for project

We recommend you to setup 2 environments for your project, and only use test id during development, ids from your admob only use when needed and for publishing to Google Store
* The name must be the same as the name of the marketing request
* Config variant test and release in gradle
* appDev: using id admob test while dev
* appProd: use ids from your admob,  build release (build file .aab)

~~~    
      productFlavors {
      appDev {
                manifestPlaceholders = [ad_app_id: "ca-app-pub-3940256099942544~3347511713"]
                buildConfigField "String", "inter", "\"ca-app-pub-3940256099942544/1033173712\""
                buildConfigField "String", "banner", "\"ca-app-pub-3940256099942544/6300978111\""
                buildConfigField "String", "native", "\"ca-app-pub-3940256099942544/2247696110\""
                buildConfigField "String", "open_resume", "\"ca-app-pub-3940256099942544/3419835294\""
                buildConfigField "String", "RewardedAd", "\"ca-app-pub-3940256099942544/5224354917\""
                buildConfigField "Boolean", "build_debug", "true"
           }
       appProd {
            // ADS CONFIG BEGIN (required)
                manifestPlaceholders = [ad_app_id: "ca-app-pub-3940256099942544~3347511713"]
                buildConfigField "String", "inter", "\"ca-app-pub-3940256099942544/1033173712\""
                buildConfigField "String", "banner", "\"ca-app-pub-3940256099942544/6300978111\""
                buildConfigField "String", "native", "\"ca-app-pub-3940256099942544/2247696110\""
                buildConfigField "String", "open_resume", "\"ca-app-pub-3940256099942544/3419835294\""
                buildConfigField "String", "RewardedAd", "\"ca-app-pub-3940256099942544/5224354917\""
                buildConfigField "Boolean", "build_debug", "false"
            // ADS CONFIG END (required)
           }
      }
~~~
AndroidManiafest.xml
~~~
        <meta-data
            android:name="com.google.android.gms.ads.APPLICATION_ID"
            android:value="${ad_app_id}" />

        // Config SDK Facebook
        <meta-data
            android:name="com.facebook.sdk.ApplicationId"
            android:value="@string/facebook_app_id" />
        <meta-data
            android:name="com.facebook.sdk.ClientToken"
            android:value="@string/facebook_client_token" />

        <meta-data android:name="com.facebook.sdk.AutoInitEnabled"
            android:value="true"/>
        <meta-data android:name="com.facebook.sdk.AutoLogAppEventsEnabled"
            android:value="true"/>

        <meta-data android:name="com.facebook.sdk.AdvertiserIDCollectionEnabled"
            android:value="true"/>
~~~
## <a id="config_ads"></a>Config ads
Create class Application

Configure your mediation here. using PROVIDER_ADMOB or PROVIDER_MAX

*** Note:Cannot use id ad test for production enviroment 
~~~
class App extends AdsMultiDexApplication(){
    @Override
    public void onCreate() {
        super.onCreate();
    ...
        String environment = BuildConfig.build_debug ? ITGAdConfig.ENVIRONMENT_DEVELOP : ITGAdConfig.ENVIRONMENT_PRODUCTION;
        itgAdConfig = new ITGAdConfig(this, ITGAdConfig.PROVIDER_ADMOB, environment);

        // Optional: setup Adjust event
        AdjustConfig adjustConfig = new AdjustConfig(true,ADJUST_TOKEN);
        // adjustConfig.setEventAdImpression(EVENT_AD_IMPRESSION_ADJUST);
        // adjustConfig.setEventNamePurchase(EVENT_PURCHASE_ADJUST);
        itgAdConfig.setAdjustConfig(adjustConfig);

        // Optional: setup Appsflyer event
        AppsflyerConfig appsflyerConfig = new AppsflyerConfig(true,APPSFLYER_TOKEN);
        itgAdConfig.setAppsflyerConfig(appsflyerConfig);
    
        // Optional: setup client token SDK Facebook
        itgAdConfig.setFacebookClientToken(FACEBOOK_CLIENT_TOKEN)

        // Optional: enable ads resume
        itgAdConfig.setIdAdResume(BuildConfig.ads_open_app);

        // Optional: setup list device test - recommended to use
        listTestDevice.add(DEVICE_ID_TEST);
        itgAdConfig.setListDeviceTest(listTestDevice);

        ITGAd.getInstance().init(this, itgAdConfig, false);

        // Auto disable ad resume after user click ads and back to app
        Admob.getInstance().setDisableAdResumeWhenClickAds(true);
        // If true -> onNextAction() is called right after Ad Interstitial showed
        Admob.getInstance().setOpenActivityAfterShowInterAds(true);
    AppOpenManager.getInstance().disableAppResumeWithActivity(SplashActivity.class);
    }
}
~~~
AndroidManiafest.xml
~~~
<application
android:name=".App"
...
>
~~~

## <a id="ads_formats"></a>Ads formats
SplashActivity
### Ad Splash Interstitial
~~~ 
    ITGAdCallback adCallback = new ITGAdCallback() {
        @Override
        public void onNextAction() {
            super.onNextAction();
            Log.d(TAG, "onNextAction");
            startMain();
        }
    };
~~~
~~~
        ITGAd.getInstance().setInitCallback(new ITGInitCallback() {
            @Override
            public void initAdSuccess() {
                ITGAd.getInstance().loadSplashInterstitialAds(SplashActivity.this, idAdSplash, TIME_OUT, TIME_DELAY_SHOW_AD, true, adCallback);
            }
        });
~~~
SplashActivity
### Ad Splash App Open High and Interstitial
~~~ 
    AppOpenManager.getInstance().loadSplashOpenAndInter(SplashActivity.class,SplashActivity.this, BuildConfig.open_lunch_high,BuildConfig.inter_splash,25000, new AdCallback(){
            @Override
            public void onNextAction() {
                super.onNextAction();
                
                // startMain();
            
            }
        });

~~~ 

### Interstitial
Load ad interstital before show 
Check null when Load Inter
~~~
  private fun loadInterCreate() {
    ApInterstitialAd mInterstitialAd = ITGAd.getInstance().getInterstitialAds(this, idInter);
  }
~~~
Show and auto release ad interstitial
~~~
         if (mInterstitialAd.isReady()) {
                ITGAd.getInstance().forceShowInterstitial(this, mInterstitialAd, new ITGAdCallback() {
            @Override
            public void onNextAction() {
                super.onNextAction();
                Log.d(TAG, "onNextAction");
               startActivity(new Intent(MainActivity.this, MaxSimpleListActivity.class));
            }
                
                }, true);
            } else {
                loadAdInterstitial();
            }
~~~
### Ad Banner

#### Latest way:
~~~
    <com.ads.control.ads.bannerAds.ITGBannerAdView
        android:id="@+id/bannerView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true"
        app:layout_constraintBottom_toBottomOf="parent" />
~~~
call load ad banner
~~~
    bannerAdView.loadBanner(this, idBanner);
~~~
#### The older way:
~~~
  <include
  android:id="@+id/include"
  layout="@layout/layout_banner_control"
  android:layout_width="match_parent"
  android:layout_height="wrap_content"
  android:layout_alignParentBottom="true"
  app:layout_constraintBottom_toBottomOf="parent" />
~~~
call load ad banner
~~~
  ITGAd.getInstance().loadBanner(this, idBanner);
~~~

### Ad Native
Load ad native before show
~~~
        ITGAd.getInstance().loadNativeAdResultCallback(this,ID_NATIVE_AD, com.ads.control.R.layout.custom_native_max_small,new ITGAdCallback(){
            @Override
            public void onNativeAdLoaded(@NonNull ApNativeAd nativeAd) {
                super.onNativeAdLoaded(nativeAd);
               //save or show native 
            }
        });
~~~
Populate native ad to view
~~~
    ITGAd.getInstance().populateNativeAdView(MainApplovinActivity.this,nativeAd,flParentNative,shimmerFrameLayout);
~~~
auto load and show native contains loading

in layout XML
~~~
      <com.ads.control.ads.nativeAds.ITGNativeAdView
        android:id="@+id/ITGNativeAds"
        android:layout_width="match_parent"
        android:layout_height="@dimen/_150sdp"
        android:background="@drawable/bg_card_ads"
        app:layoutCustomNativeAd="@layout/custom_native_admod_medium_rate"
        app:layoutLoading="@layout/loading_native_medium"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
~~~
Call load native ad
~~~
 ITGNativeAdView.loadNativeAd(this, idNative);
~~~
Load Ad native for recyclerView
~~~~
    // ad native repeating interval
    ITGAdAdapter     adAdapter = ITGAd.getInstance().getNativeRepeatAdapter(this, idNative, layoutCustomNative, com.ads.control.R.layout.layout_native_medium,
                originalAdapter, listener, 4);
    
    // ad native fixed in position
        ITGAdAdapter   adAdapter = ITGAd.getInstance().getNativeFixedPositionAdapter(this, idNative, layoutCustomNative, com.ads.control.R.layout.layout_native_medium,
                originalAdapter, listener, 4);
    
        recyclerView.setAdapter(adAdapter.getAdapter());
        adAdapter.loadAds();
~~~~
### Ad Reward
Get and show reward
~~~
  ApRewardAd rewardAd = ITGAd.getInstance().getRewardAd(this, idAdReward);

   if (rewardAd != null && rewardAd.isReady()) {
                ITGAd.getInstance().forceShowRewardAd(this, rewardAd, new ITGAdCallback());
            }
});
~~~
### Ad resume
App
~~~ 
  override fun onCreate() {
    super.onCreate()
    AppOpenManager.getInstance().enableAppResume()
    ITGAdConfig.setIdAdResume(AppOpenManager.AD_UNIT_ID_TEST);
    ...
  }
    

~~~


# <a id="billing_app"></a>Billing app
## Init Billing
Application
~~~
    @Override
    public void onCreate() {
        super.onCreate();
        AppPurchase.getInstance().initBilling(this,listINAPId,listSubsId);
    }
~~~
## Check status billing init
~~~
 if (AppPurchase.getInstance().getInitBillingFinish()){
            loadAdsPlash();
        }else {
            AppPurchase.getInstance().setBillingListener(new BillingListener() {
                @Override
                public void onInitBillingListener(int code) {
                         loadAdsPlash();
                }
            },7000);
        }
~~~
## Check purchase status
    //check purchase with PRODUCT_ID
     AppPurchase.getInstance().isPurchased(this,PRODUCT_ID);
     //check purchase all
     AppPurchase.getInstance().isPurchased(this);
##  Purchase
     AppPurchase.getInstance().purchase(this,PRODUCT_ID);
     AppPurchase.getInstance().subscribe(this,SUBS_ID);
## Purchase Listener
             AppPurchase.getInstance().setPurchaseListioner(new PurchaseListioner() {
                 @Override
                 public void onProductPurchased(String productId,String transactionDetails) {

                 }

                 @Override
                 public void displayErrorMessage(String errorMsg) {

                 }
             });

## Get id purchased
      AppPurchase.getInstance().getIdPurchased();
## Consume purchase
      AppPurchase.getInstance().consumePurchase(PRODUCT_ID);
## Get price
      AppPurchase.getInstance().getPrice(PRODUCT_ID)
      AppPurchase.getInstance().getPriceSub(SUBS_ID)
### Show iap dialog
    InAppDialog dialog = new InAppDialog(this);
    dialog.setCallback(() -> {
         AppPurchase.getInstance().purchase(this,PRODUCT_ID);
        dialog.dismiss();
    });
    dialog.show();



# <a id="ads_rule"></a>Ads rule
## Always add device test to idTestList with all of your team's device
To ignore invalid ads traffic
https://support.google.com/adsense/answer/16737?hl=en
## Before show full-screen ad (interstitial, app open ad), alway show a short loading dialog
To ignore accident click from user. This feature is existed in library
## Never reload ad on onAdFailedToLoad
To ignore infinite loop
