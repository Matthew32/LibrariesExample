package com.matthew.librariesexamplemaps.features.map;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.matthew.librariesexamplemaps.R;
import com.matthew.librariesexamplemaps.features.main.MainActivityPresenter;
import com.matthew.librariesexamplemaps.features.main.MainActivityView;
import com.matthew.librariesexamplemaps.model.MapPoint;

import io.realm.RealmResults;

/**
 * Created by Matthew on 15/10/2017.
 */

public class MapActivity extends MvpActivity<MapActivityView, MapActivityPresenter> implements MapActivityView, OnMapReadyCallback {
    private GoogleMap map;

    @NonNull
    @Override
    public MapActivityPresenter createPresenter() {
        return new MapActivityPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void showLoading(boolean pullToRefresh) {

    }

    @Override
    public void showContent() {

    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {

    }

    @Override
    public void setData(Object data) {

    }

    @Override
    public void loadData(boolean pullToRefresh) {

    }

    @Override
    public String getBaseUrl() {
        return getString(R.string.web_resources);
    }

    @Override
    public void setMaps(RealmResults<MapPoint> points) {
        // do something
        for (MapPoint point : points) {
            LatLng pointx = new LatLng(point.getxPoint(), point.getyPoint());
            map.addMarker(new MarkerOptions().position(pointx)
                    .title(point.getName()));

        }
        LatLng barna = new LatLng(41.3850639, 2.1734034999999494);
        map.moveCamera(CameraUpdateFactory.newLatLng(barna));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        getPresenter().getServerInfo();
    }
}
