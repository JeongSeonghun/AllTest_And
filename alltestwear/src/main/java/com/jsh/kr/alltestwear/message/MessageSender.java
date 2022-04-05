package com.jsh.kr.alltestwear.message;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.android.gms.wearable.CapabilityClient;
import com.google.android.gms.wearable.CapabilityInfo;
import com.google.android.gms.wearable.MessageClient;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.Wearable;
import com.jsh.kr.alltestwear.C;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import androidx.annotation.NonNull;

public class MessageSender {
   private final static String TAG = MessageSender.class.getSimpleName();

   private MessageClient messageClient;

   public MessageSender(Context context) {
//        messageClient = Wearable.getMessageClient(context);
//        setupVoiceTranscription(context);
   }

   public void sendMessage(Context context, String message) {
      getAllNodes(context, message);
   }

   public void sendMessageNext(Context context, String message, List<Node> nodes) {
      String nodeId = pickBestNodeId(nodes);
      byte[] data = message.getBytes();

      Task<Integer> sendTask = Wearable.getMessageClient(context).sendMessage(nodeId, C.PATH_MOBILE, data);
      sendTask.addOnSuccessListener(new OnSuccessListener<Integer>() {
         @Override
         public void onSuccess(Integer integer) {
            Log.d(TAG, "send message success : "+integer);
         }
      });
      sendTask.addOnFailureListener(new OnFailureListener() {
         @Override
         public void onFailure(@NonNull Exception e) {
            Log.d(TAG, "send message fail : "+e);
         }
      });
   }

   private void getAllNodes(final Context context, final String message) {
      Wearable.getNodeClient(context).getConnectedNodes().addOnSuccessListener(new OnSuccessListener<List<Node>>() {
         @Override
         public void onSuccess(List<Node> nodes) {
            sendMessageNext(context, message, nodes);
         }
      });
   }

   private String pickBestNodeId(List<Node> nodes) {
      String bestNodeId = null;
      // Find a nearby node or pick one arbitrarily
      for (Node node : nodes) {
         if (node.isNearby()) {
            return node.getId();
         }
         bestNodeId = node.getId();
      }
      return bestNodeId;
   }

   private static final String
           VOICE_TRANSCRIPTION_CAPABILITY_NAME = "voice_transcription";

   private void setupVoiceTranscription(Context context) {
      CapabilityInfo capabilityInfo = null;
      try {
         capabilityInfo = Tasks.await(
                 Wearable.getCapabilityClient(context).getCapability(
                         VOICE_TRANSCRIPTION_CAPABILITY_NAME, CapabilityClient.FILTER_REACHABLE));

         // capabilityInfo has the reachable nodes with the transcription capability
         updateTranscriptionCapability(capabilityInfo);

      } catch (ExecutionException e) {
         e.printStackTrace();
      } catch (InterruptedException e) {
         e.printStackTrace();
      }

      // This example uses a Java 8 Lambda. Named or anonymous classes can also be used.
      CapabilityClient.OnCapabilityChangedListener capabilityListener = new CapabilityClient.OnCapabilityChangedListener() {
         @Override
         public void onCapabilityChanged(@NonNull CapabilityInfo capabilityInfo) {
            updateTranscriptionCapability(capabilityInfo);
         }
      };
      Wearable.getCapabilityClient(context).addListener(
              capabilityListener,
              VOICE_TRANSCRIPTION_CAPABILITY_NAME);

   }

   private String transcriptionNodeId = null;

   private void updateTranscriptionCapability(CapabilityInfo capabilityInfo) {
      Set<Node> connectedNodes = capabilityInfo.getNodes();

      transcriptionNodeId = pickBestNodeId(connectedNodes);
   }

   private String pickBestNodeId(Set<Node> nodes) {
      String bestNodeId = null;
      // Find a nearby node or pick one arbitrarily
      for (Node node : nodes) {
         if (node.isNearby()) {
            return node.getId();
         }
         bestNodeId = node.getId();
      }
      return bestNodeId;
   }

   public static final String VOICE_TRANSCRIPTION_MESSAGE_PATH = "/voice_transcription";
   private void requestTranscription(Context context, byte[] voiceData) {
      if (transcriptionNodeId != null) {
         Task<Integer> sendTask =
                 Wearable.getMessageClient(context).sendMessage(
                         transcriptionNodeId, VOICE_TRANSCRIPTION_MESSAGE_PATH, voiceData);
         // You can add success and/or failure listeners,
         // Or you can call Tasks.await() and catch ExecutionException
//            sendTask.addOnSuccessListener(...);
//            sendTask.addOnFailureListener(...);
      } else {
         // Unable to retrieve node with transcription capability
      }
   }
}
