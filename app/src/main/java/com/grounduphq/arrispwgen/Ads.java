package com.grounduphq.arrispwgen;

import android.os.Bundle;

import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

class Ads {

    static void loadAds(AdView adView, boolean nonPersonalisedAds) {
        // Load an ad into the AdMob banner view.
        AdRequest.Builder adRequestBuilder = new AdRequest.Builder().setRequestAgent("android_studio:ad_template");
        // Use non-personalised ads if the user so chose
        if (nonPersonalisedAds) {
            Bundle extras = new Bundle();
            extras.putString("npa", "1");
            adRequestBuilder.addNetworkExtrasBundle(AdMobAdapter.class, extras);
        }
        AdRequest adRequest = adRequestBuilder.build();
        adView.loadAd(adRequest);
    }
}
