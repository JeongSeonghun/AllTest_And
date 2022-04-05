package com.jsh.kr.alltestwear.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.CapabilityClient;
import com.google.android.gms.wearable.CapabilityInfo;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.Wearable;

import java.util.Set;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class BaseWearActivity extends Activity
//        implements GoogleApiClient.OnConnectionFailedListener
//        , GoogleApiClient.ConnectionCallbacks
//        , CapabilityApi.CapabilityListener
        implements CapabilityClient.OnCapabilityChangedListener {

   private final String TAG = this.getClass().getSimpleName();

   private GoogleApiClient mGoogleApiClient;
   private Node mNode;

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

//        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .addApi(Wearable.API)
//                .addConnectionCallbacks(this)
//                .addOnConnectionFailedListener(this)
//                .build();
//        mGoogleApiClient.connect();

//        Wearable.CapabilityApi.addCapabilityListener(mGoogleApiClient, this, "capability_mobile");

      Wearable.getCapabilityClient(this).addListener(this, "capability_mobile");

   }

   @Override
   protected void onPause() {
      super.onPause();

      Log.d(TAG, "onPause()");
   }

   @Override
   protected void onStop() {
      super.onStop();

      Log.d(TAG, "onStop()");

//        Wearable.CapabilityApi.removeCapabilityListener(mGoogleApiClient, this, "capability_mobile");
      Wearable.getCapabilityClient(this).removeListener(this, "capability_mobile");
   }

   @Override
   protected void onDestroy() {
//        Wearable.CapabilityApi.removeCapabilityListener(mGoogleApiClient, this, "capability_mobile");
      Wearable.getCapabilityClient(this).removeListener(this, "capability_mobile");
//        if (mGoogleApiClient.isConnected()) {
//            mGoogleApiClient.disconnect();
//        }
      super.onDestroy();
   }

   @Override
   public void onWindowFocusChanged(boolean hasFocus) {
      super.onWindowFocusChanged(hasFocus);
      Log.d(TAG, "onWindowFocusChanged() : " + hasFocus);
   }

   @Override
   public void onCapabilityChanged(@NonNull CapabilityInfo capabilityInfo) {
      mNode = pickBestNodeId(capabilityInfo.getNodes());
   }

   /**
    * pick best node.
    * @param nodes Set of nodes
    * @return single node
    */
   private Node pickBestNodeId(Set<Node> nodes) {
      Node bestNodeId = null;
      for (Node node : nodes) {
         bestNodeId = node;
      }
      return bestNodeId;
   }
}
