package com.matthew.librariesexamplemaps.App;

import android.app.Application;
import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Matthew on 15/10/2017.
 */

public class app extends Application {
    private Realm realm;
    private RealmConfiguration myAppRealmConfig;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        Realm.deleteRealm(realmConfiguration); // Delete Realm between app restarts.
        Realm.setDefaultConfiguration(realmConfiguration);
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    @Override
    protected void attachBaseContext(Context base) {

        super.attachBaseContext(base);
        //   MultiDex.install(this);
    }
}

