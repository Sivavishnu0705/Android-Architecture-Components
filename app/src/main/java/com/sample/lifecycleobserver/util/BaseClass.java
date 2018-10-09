package com.sample.lifecycleobserver.util;

import android.arch.lifecycle.LifecycleObserver;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class BaseClass implements LifecycleObserver {
    private static final String TAG = "BaseClass";

    /**
     * Method to check network connectivity status
     *
     * @return Whether connected or not
     */
    protected static boolean checkNetworkConnection(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager != null) {
                NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
                if (activeNetwork != null) {
                    if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI || activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                        return true;
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "checkNetworkConnection: Exception: " + e.getLocalizedMessage());
        }
        return false;
    }
}