package hu.ait.android.aitsummerchat;

import android.app.Application;

import com.backendless.Backendless;

public class ChatApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Backendless.initApp(this,
                "63550021-A439-4F47-FFF3-B50C14DA4C00",
                "83B33274-5722-AC2E-FFD5-1A2D2E365C00",
                "v1");
    }
}
