package hu.ait.android.timehelloworld;

import android.app.Application;
import android.util.Log;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("TAG_MAIN","APP STARTED");
    }


}
