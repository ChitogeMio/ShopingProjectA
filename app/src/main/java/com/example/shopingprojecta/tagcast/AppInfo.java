package com.example.shopingprojecta.tagcast;

import android.Manifest;
import android.app.Application;
import android.content.pm.PackageManager;

import androidx.core.content.ContextCompat;
import androidx.core.content.PackageManagerCompat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jp.tagcast.helper.TGCAdapter;

public class AppInfo extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // khoi tao tagcast adapter
        TGCAdapter tgcAdapter = TGCAdapter.getInstance(getApplicationContext());

        tgcAdapter.setApiKey("b4e40a3a5756b13b511aab6a937ef4a2");
        // dặt OptimizationMode
        tgcAdapter.setOptimizationMode(true);
        if (checkPermission().size() == 0) {
            // xu ly ban dau
            tgcAdapter.prepare();
        }

    }

    public List<String> checkPermission() {

        List<String> permission = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED) {
            permission.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED) {
            permission.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        return permission;
    }
}