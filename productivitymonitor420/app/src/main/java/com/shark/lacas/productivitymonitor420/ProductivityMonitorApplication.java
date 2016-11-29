package com.shark.lacas.productivitymonitor420;

import io.realm.Realm;
import io.realm.RealmConfiguration;

import android.app.Application;


/**
 * Created by macbookpro on 26/11/16.
 */
public class ProductivityMonitorApplication extends Application{


    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(realmConfig);
    }


}
