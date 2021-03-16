package com.example.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MessengerService extends Service {

    private static final String TAG = "MessengerService";


    private final Messenger mMessenger = new Messenger(new MessengerHandler());

    private static class MessengerHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch(msg.what){
                case MyConstants.MSG_FROM_CLIENT:
                    Log.i(TAG,"receive msg from Client:"+msg.getData().getString("msg"));
                    break;

                default:
                    super.handleMessage(msg);

            }
        }
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }
}










