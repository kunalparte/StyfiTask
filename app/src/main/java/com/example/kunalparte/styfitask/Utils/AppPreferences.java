package com.example.kunalparte.styfitask.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreferences {

    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private static AppPreferences appPreferences;

    public static AppPreferences getInstance(Context context){
        if (sharedPreferences == null){
            sharedPreferences = context.getSharedPreferences(Consts.APP_PREF, Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
        }
        if (appPreferences == null)
            appPreferences = new AppPreferences();

        return appPreferences;
    }

    public void setLoginToken(String token){
        editor.putString(Consts.LOGIN_TOKEN,token).commit();
    }

    public String getLoginToken(){
        return sharedPreferences.getString(Consts.LOGIN_TOKEN,"");
    }

}
