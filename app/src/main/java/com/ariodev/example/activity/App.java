package com.ariodev.example.activity;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;


public class App extends Application
{



    public static Context context;

    public static Activity currentActivity;
    public static LayoutInflater inflater;
    public static String PACKAGE_NAME;
    public static Handler Handler;



    @Override

    public void onCreate()
    {
        super.onCreate();
        context = getApplicationContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        PACKAGE_NAME = getApplicationContext().getPackageName();
        Handler = new Handler();

    }


}

