package com.example.kunalparte.styfitask.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Consts {

    public static final String BASE_URL = "http://lightbuzz.in:1353/";

    public static final String PATH_URL = "test";

    public static final String NAME_KEY = "name";

    public static final String APP_PREF = "appPref";

    public static final String LOGIN_TOKEN = "token";

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
