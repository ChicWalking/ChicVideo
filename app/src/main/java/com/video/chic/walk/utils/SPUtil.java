package com.video.chic.walk.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SPUtil {
    public static final String SP_ISFIRST_TIME="sp_isfirst_time";

    public static boolean getPrefBoolean(Context context, final String key,
                                         final boolean defaultValue) {
        final SharedPreferences settings=PreferenceManager.getDefaultSharedPreferences(context);
        return settings.getBoolean(key,defaultValue);
    }

    public static void setPrefBoolean(Context applicationContext, String key, boolean value) {
        final SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(applicationContext);
        settings.edit().putBoolean(key, value).commit();

    }
}
