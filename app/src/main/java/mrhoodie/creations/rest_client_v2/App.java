package mrhoodie.creations.rest_client_v2;

import android.app.Application;

/**
 * Created by Corentin on 4/3/2018.
 */

public class App extends Application {

    private static App mApp;
    public String BASE_URL;

    public static App getInstance(){
        if(mApp == null){
            mApp = new App();
        }
        return mApp;
    }



    public App() {
    }

    public String getBASE_URL() {
        return BASE_URL;
    }

    public void setBASE_URL(String BASE_URL) {
        this.BASE_URL = BASE_URL;
    }
}
