package com.afollestad.aidlexample;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private IMainService mService;
    private TextView mLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLog = (TextView) findViewById(R.id.log);

        Intent serviceIntent = new Intent()
                .setComponent(new ComponentName(
                        "com.afollestad.aidlexamplereceiver",
                        "com.afollestad.aidlexamplereceiver.MainService"));
        mLog.setText("Starting service…\n");
        startService(serviceIntent);
        mLog.append("Binding service…\n");
        bindService(serviceIntent, mConnection, BIND_AUTO_CREATE);
    }

    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            mLog.append("Service binded!\n");
            mService = IMainService.Stub.asInterface(service);

            performListing();
        }

        @Override
        public void onServiceDisconnected(ComponentName className) {
            mService = null;
            // This method is only invoked when the service quits from the other end or gets killed
            // Invoking exit() from the AIDL interface makes the Service kill itself, thus invoking this.
            mLog.append("Service disconnected.\n");
        }
    };

    private void performListing() {
        mLog.append("Requesting file listing…\n");
        long start = System.currentTimeMillis();
        long end = 0;
        try {
            MainObject[] results = mService.listFiles("/sdcard/testing");
            end = System.currentTimeMillis();
            int index = 0;
            mLog.append("Received " + results.length + " results:\n");
            for (MainObject o : results) {
                if (index > 20) {
                    mLog.append("\t -> Response truncated!\n");
                    break;
                }
                mLog.append("\t -> " + o.getPath() + "\n");
                index++;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        mLog.append("File listing took " + (((double) end - (double) start) / 1000d) + " seconds, or " + (end - start) + " milliseconds.\n");
        try {
            mService.exit();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
