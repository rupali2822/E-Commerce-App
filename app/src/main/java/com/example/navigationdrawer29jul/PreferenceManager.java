package com.example.navigationdrawer29jul;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {
    private static PreferenceManager INSTANCE;
    private  static SharedPreferences preferences;
    Context context;

    public static PreferenceManager getINSTANCE(Context context) {
        if (INSTANCE==null){
            INSTANCE=new PreferenceManager();
            preferences=context.getSharedPreferences("ImgInfo",context.MODE_PRIVATE);
        }
        return INSTANCE;
    }

    public  void setString(String key,String value){
        preferences.edit().putString(key,value).apply();
    }

    public  String getString(String key){
        return preferences.getString(key,"");
    }

//    SharedPreferences pref = context.getSharedPreferences(PREFS_STORE_VEHICLE, context.MODE_PRIVATE);
//    SharedPreferences.Editor editor = pref.edit();
//        editor.putString(KEY_VEHICLE_NO, vehicleNo.trim());
//        editor.commit();
}
