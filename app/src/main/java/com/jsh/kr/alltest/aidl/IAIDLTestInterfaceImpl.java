package com.jsh.kr.alltest.aidl;

import android.os.RemoteCallbackList;
import android.os.RemoteException;

import com.jsh.kr.alltestlib.AIDLStateCallback;
import com.jsh.kr.alltestlib.AIDLTestInterface;

public class IAIDLTestInterfaceImpl extends AIDLTestInterface.Stub {

   private static IAIDLTestInterfaceImpl instance;

   private boolean state;

   public static IAIDLTestInterfaceImpl getInstance() {
      if (instance == null) {
         instance = new IAIDLTestInterfaceImpl();
      }
      return instance;
   }

   private RemoteCallbackList<AIDLStateCallback> callbackList = new RemoteCallbackList<>();

   @Override
   public boolean isAvailable() throws RemoteException {
      return state;
   }

   @Override
   public boolean registerCallback(AIDLStateCallback callback) throws RemoteException {
      boolean ret = callbackList.register(callback);
      return ret;
   }

   @Override
   public boolean unregisterCallback(AIDLStateCallback callback) throws RemoteException {
      boolean ret = callbackList.unregister(callback);
      return ret;
   }

   public void broadcastToCurrentStateToClients(int state) {
      int max = callbackList.beginBroadcast();

      for (int idx = 0 ; idx < max ; idx++) {
         try {
            callbackList.getBroadcastItem(idx).onServiceStateChanged(state);
         } catch (RemoteException e) {
            e.printStackTrace();
         }
      }
      callbackList.finishBroadcast();
   }

   public void setState(boolean state) {
      this.state = state;
   }
}
