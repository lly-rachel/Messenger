package com.example.messenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;


public class MessengerActivity extends AppCompatActivity {


    private static final String TAG = "MessengerActivity";

    private Messenger mService;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mService = new Messenger(iBinder);
            Message msg = Message.obtain(null,MyConstants.MSG_FROM_CLIENT);
            Bundle data = new Bundle();
            data.putString("msg","hi,this is client");
            msg.setData(data);
            try {
                mService.send(msg);
            }catch (RemoteException e){
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        Intent intent = new Intent(this,MessengerService.class);
        bindService(intent,mConnection, Context.BIND_AUTO_CREATE);

        setContentView(R.layout.activity_messenger);
    }

    @Override
    protected void onDestroy() {
        unbindService(mConnection);
        super.onDestroy();
    }
}