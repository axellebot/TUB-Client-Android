package com.axel_nicolas.tub;

/**
 * Created by axell on 04/11/2016.
 */
public class App {
    private static App ourInstance = new App();

    public static App getInstance() {
        return ourInstance;
    }

    private App() {
    }
}
