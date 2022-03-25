package com.jsh.kr.alltest.service;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;

import com.jsh.kr.alltest.util.LogUtil;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

// TODO: 권한 및 로직 추가 구현 필요
public class BackgroundLocationTestService extends Service implements LocationListener {

   private LocationManager testLocationManager;
   // unit milliseconds
   private final long minTime = 3 * 1000;
   // unit miter
   private final float minDistance = 10;

   public BackgroundLocationTestService() {

   }

   @Override
   public void onCreate() {
      super.onCreate();

      testLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

      if (testLocationManager == null) { return; }

      boolean isGPSEnable = testLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
      boolean isNetwork = testLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

      if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
              && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
         return;
      }

      if (isGPSEnable) {
         testLocationManager.requestLocationUpdates(
                 LocationManager.GPS_PROVIDER
                 , minTime
                 , minDistance
                 , this
         );

      } else if (isNetwork) {
         testLocationManager.requestLocationUpdates(
                 LocationManager.NETWORK_PROVIDER
                 , minTime
                 , minDistance
                 , this
         );

      }
   }

   @Override
   public int onStartCommand(Intent intent, int flags, int startId) {
//        return START_NOT_STICKY;
//        return START_STICKY;
      return super.onStartCommand(intent, flags, startId);
   }

   @Nullable
   @Override
   public IBinder onBind(Intent intent) {
      return null;
   }

   @Override
   public void onLocationChanged(Location location) {
      LogUtil.d(BackgroundLocationTestService.class.getSimpleName(), "location "+location);
   }

   @Override
   public void onStatusChanged(String s, int i, Bundle bundle) {

   }

   @Override
   public void onProviderEnabled(String s) {

   }

   @Override
   public void onProviderDisabled(String s) {

   }
}
