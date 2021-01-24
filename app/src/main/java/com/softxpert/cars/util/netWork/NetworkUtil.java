package com.softxpert.cars.util.netWork;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 *Check and manipulate network
 * status.
 */
public class NetworkUtil {

    private static ConnectivityManager connectivityManager;
    private static NetworkInfo activeNetwork;

    public static NetworkStatus getConnectivityStatus(Context context) {
        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        activeNetwork = connectivityManager.getActiveNetworkInfo();

        if (activeNetwork != null && activeNetwork.isConnected()) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
               // return isOnline();
                return NetworkStatus.WIFI_CONNECTED_WITH_INTERNET;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                return NetworkStatus.MOBILE_DATA_CONNECTED;
            }
        }
        return NetworkStatus.OFFLINE;
    }


 }
