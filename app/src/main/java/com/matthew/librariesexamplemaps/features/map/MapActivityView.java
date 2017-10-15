package com.matthew.librariesexamplemaps.features.map;

import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView;
import com.matthew.librariesexamplemaps.model.MapPoint;

import io.realm.RealmResults;

/**
 * Created by Matthew on 15/10/2017.
 */

public interface MapActivityView extends MvpLceView {
    public String getBaseUrl();

    void setMaps(RealmResults<MapPoint> points);
}
