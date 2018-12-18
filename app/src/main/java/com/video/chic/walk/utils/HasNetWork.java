package com.video.chic.walk.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

public class HasNetWork {

    public static boolean isNetworkAvailable(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager == null){
            return false;
        }else{
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();
            if(networkInfo != null &&networkInfo.length > 0 ){
                for(int i=0; i< networkInfo.length; i++){
                    if(networkInfo[i].getState() == NetworkInfo.State.CONNECTED){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    /**
     * 没有网络dialog
     */

    public static void showNoWifiDialog(final  Context context){
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("当前没有网络，请检查你的网络");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent("android.settings.WIRELESS_SETTINGS");
                context.startActivity(intent);
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
