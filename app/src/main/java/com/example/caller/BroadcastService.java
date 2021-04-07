package com.example.caller;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class BroadcastService extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent)
    {
        try
        {
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            String number = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            Boolean isPicked = false;
            if(state.equals(TelephonyManager.EXTRA_STATE_RINGING))
            {
                //handle incoming call here
//                Toast.makeText(context, "Incoming Call "+number, Toast.LENGTH_SHORT).show();
            }
            if(state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK))
            {
                //handle when call is connected
//                Toast.makeText(context, "Picked up", Toast.LENGTH_SHORT).show();
                isPicked = true;
            }
            if (state.equals(TelephonyManager.EXTRA_STATE_IDLE))
            {
                //handle when call is disconnected
//                Toast.makeText(context, "Disconnected", Toast.LENGTH_SHORT).show();
                if(!isPicked)
                {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage("YOUR_OWN_NUMBER", null, number+ " tried to call", null, null);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

}
