package com.jsh.kr.alltest.wear.message;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.wearable.MessageClient;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeClient;
import com.google.android.gms.wearable.Wearable;
import com.jsh.kr.alltest.wear.WearC;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;

public class GoogleMessageSender {

   private final String TAG = this.getClass().getSimpleName();
   private final String PATH = "/wear";

   private final Context mContext;

   public GoogleMessageSender(Context context) {
      mContext = context;
   }

   public void sendMessage(final String message) {

      ThreadPoolExecutor threadPool = new ThreadPoolExecutor(10, 100, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10), new ThreadPoolExecutor.DiscardOldestPolicy());
      threadPool.execute(new Runnable() {
         @Override
         public void run() {
            NodeClient nodeClient = Wearable.getNodeClient(mContext);
            nodeClient.getConnectedNodes().addOnSuccessListener(new OnSuccessListener<List<Node>>() {
               @Override
               public void onSuccess(List<Node> nodes) {

                  if (nodes != null && nodes.size() != 0) {
                     Log.i(TAG, nodes.get(0).getDisplayName());

                     for (int i = 0; i < nodes.size(); i++) {
                        if (i == 0) {
                           send(nodes.get(i), message);
                        }
                     }
                  } else {
                     Log.i(TAG, "nothing connected!");
                  }
               }
            });

         }
      });

   }

   private void send(Node node, String message) {
      if (node.isNearby()) {
         Log.i(TAG, "send: " + message);
         MessageClient messageClient = Wearable.getMessageClient(mContext);
         messageClient.sendMessage(node.getId(), WearC.PATH_WEAR, message.getBytes(StandardCharsets.UTF_8))
                 .addOnSuccessListener(new OnSuccessListener<Integer>() {
                    @Override
                    public void onSuccess(Integer integer) {
                       Log.i(TAG, "onResult: Succeed");
                    }
                 }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
               Log.i(TAG, "onResult: Failed");
            }
         });
      } else {
         Log.i(TAG, "sendMessage: node is away from here !");
      }
   }

}

