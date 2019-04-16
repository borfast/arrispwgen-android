package com.grounduphq.arrispwgen;

import android.content.Context;
import android.widget.Toast;

import com.google.ads.consent.ConsentForm;
import com.google.ads.consent.ConsentFormListener;
import com.google.ads.consent.ConsentInfoUpdateListener;
import com.google.ads.consent.ConsentInformation;
import com.google.ads.consent.ConsentStatus;
import com.google.ads.consent.DebugGeography;
import com.google.android.gms.ads.AdView;

import java.net.MalformedURLException;
import java.net.URL;

class AdsConsent {
    private final Context context;
    private AdView adView;


    private final String PUBLISHER_ID;
    private URL PRIVACY_URL;

    private ConsentForm consentForm;

    AdsConsent(Context ctx, AdView adView){
        this.context = ctx;
        this.adView = adView;

        PUBLISHER_ID = context.getResources().getString(R.string.admob_publisher_id);
        try {
            String url = context.getResources().getString(R.string.admob_privacy_url);
            PRIVACY_URL = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    // Initialises the consent information and displays consent form if needed
    ConsentInformation initialise() {
        ConsentInformation consentInformation = ConsentInformation.getInstance(context);

        // Geography appears as in EEA for test devices.
        consentInformation.setDebugGeography(DebugGeography.DEBUG_GEOGRAPHY_EEA);
        // Geography appears as not in EEA for debug devices.
        //consentInformation.setDebugGeography(DebugGeography.DEBUG_GEOGRAPHY_NOT_EEA);

        consentInformation.requestConsentInfoUpdate(new String[]{PUBLISHER_ID}, new ConsentInfoUpdateListener() {
            @Override
            public void onConsentInfoUpdated(ConsentStatus consentStatus) {
                // User's consent status successfully updated.
                if (consentStatus == ConsentStatus.UNKNOWN) {
                    showConsentForm();
                }
            }

            @Override
            public void onFailedToUpdateConsentInfo(String errorDescription) {
                // Consent form error. Would be nice to have proper error logging. Happens also when user has no internet connection
                if (BuildConfig.BUILD_TYPE.equals("debug")) {
                    Toast.makeText(context, errorDescription, Toast.LENGTH_LONG).show();
                }
            }
        });

        return consentInformation;
    }

    private void showConsentForm() {

        consentForm = new ConsentForm.Builder(context, PRIVACY_URL)
                .withListener(new ConsentFormListener() {
                    @Override
                    public void onConsentFormLoaded() {
                        // The consent form was loaded successfully. Let's show it.
                        consentForm.show();
                    }

                    @Override
                    public void onConsentFormOpened() {
                        // The consent form has been opened.
                    }

                    @Override
                    public void onConsentFormClosed(ConsentStatus consentStatus, Boolean userPrefersAdFree) {
                        // The consent form was closed. This could also be used to redirect to a paid version on the Play Store.
                        if (consentStatus != ConsentStatus.UNKNOWN) {
                            boolean personalised = consentStatus == ConsentStatus.PERSONALIZED;
                            Ads.loadAds(adView, personalised);
                        }
                    }

                    @Override
                    public void onConsentFormError(String errorDescription) {
                        // The consent form threw an error.
                        if (BuildConfig.BUILD_TYPE.equals("debug")) {
                            Toast.makeText(context, errorDescription, Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .withPersonalizedAdsOption()
                .withNonPersonalizedAdsOption()
                .build();
        consentForm.load();
    }

    // Resets the consent information, forcing the user to choose again.
    public void resetConsent() {
        ConsentInformation consentInformation = ConsentInformation.getInstance(context);
        consentInformation.reset();
    }
}
