package com.robosoft.archanakumari.androideventmanager;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by archanakumari on 27/12/15.
 */
public class Message {

    public static String TAG = "Hello";
    public static void message(Context context ,String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        Log.i(TAG, message);
        Log.d(TAG, message);
        Log.v(TAG, message);
        Log.w(TAG, message);
    }
}
