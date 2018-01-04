package app.mytweetapp.main;

/**
 * Created by gary on 04/01/18.
 */
import android.app.Application;


import static app.mytweetapp.helpers.LogHelpers.info;

public class MyTweetApp extends Application {

    protected static MyTweetApp app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        info(this, "MyTweetApp is launched");
    }

    public static MyTweetApp getApp() {
        return app;
    }
}