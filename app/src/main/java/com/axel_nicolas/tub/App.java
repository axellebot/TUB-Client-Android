package com.axel_nicolas.tub;

import android.app.Application;

/**
 * Created by axell on 04/11/2016.
 */
public class App extends Application{
    private static App ourInstance;

    public static App getInstance() {
        return ourInstance;
    }

    private App() {
        ourInstance=this;

    }
}
