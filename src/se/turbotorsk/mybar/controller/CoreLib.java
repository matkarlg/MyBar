package se.turbotorsk.mybar.controller;
import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;

public class CoreLib extends Application {
    private static CoreLib me;

    public CoreLib() {
        me = this;
    }

    public static Context Context() {
        return me;
    }

    public static ContentResolver ContentResolver() {
        return me.getContentResolver();
    }
}