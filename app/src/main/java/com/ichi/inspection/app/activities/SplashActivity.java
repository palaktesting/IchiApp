package com.ichi.inspection.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.crashlytics.android.ndk.CrashlyticsNdk;
import com.ichi.inspection.app.R;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics(), new CrashlyticsNdk());

        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
        finish();
    }
}