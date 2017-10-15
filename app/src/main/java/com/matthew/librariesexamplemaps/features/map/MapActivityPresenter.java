package com.matthew.librariesexamplemaps.features.map;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.matthew.librariesexamplemaps.model.MapPoint;
import com.matthew.librariesexamplemaps.server.ApiEndPoint;
import com.matthew.librariesexamplemaps.server.utils.JsonResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Matthew on 15/10/2017.
 */

public class MapActivityPresenter extends MvpBasePresenter<MapActivityView> {
    private Retrofit retrofit;
    private Realm realm;

    public MapActivityPresenter() {
        realm = Realm.getDefaultInstance();

    }

    public void getServerInfo() {
        final List<MapPoint> mapPoints = new ArrayList<>();
        String url = getView().getBaseUrl();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        ApiEndPoint apiService =
                retrofit.create(ApiEndPoint.class);
        Call<HashMap<String, Object>> call = apiService.getParks();

        call.enqueue(new Callback<HashMap<String, Object>>() {
            @Override
            public void onResponse(Call<HashMap<String, Object>> call, Response<HashMap<String, Object>> response) {
                int statusCode = response.code();
                // User user = response.body();
                //save Mappoints
                final List<MapPoint> points = new ArrayList<MapPoint>();
                if (statusCode == 200) {
                    ArrayList<LinkedTreeMap> items = (ArrayList<LinkedTreeMap>) response.body().get("items");
                    LinkedTreeMap previewLocal = null;
                    ArrayList<ArrayList> loca = null;


                    if (items != null) {
                        for (LinkedTreeMap child : items) {

                            if (child != null) {
                                MapPoint map = new MapPoint();
                                map.setName((String) child.get("titol"));
                                previewLocal = (LinkedTreeMap) child.get("localitzacio");
                                if (previewLocal != null) {
                                    loca = (ArrayList) previewLocal.get("localitzacio_geolocalitzacio");
                                    if (loca != null) {
                                        map.setxPoint((Double) loca.get(0).get(1));
                                        map.setyPoint((Double) loca.get(0).get(0));
                                    }
                                }
                                points.add(map);

                            }

                        }
                        //save points to local
                        // Asynchronously update objects on a background thread
                        realm.executeTransactionAsync(new Realm.Transaction() {
                            @Override
                            public void execute(Realm bgRealm) {

                                bgRealm.copyToRealmOrUpdate(points);

                            }
                        }, new Realm.Transaction.OnSuccess() {
                            @Override
                            public void onSuccess() {
                                // Original queries and Realm objects are automatically updated.
                                setMapPoints();
                            }
                        });

                    }

                }
            }

            @Override
            public void onFailure(Call<HashMap<String, Object>> call, Throwable t) {
                // Log error here since request failed
                Log.i("Error001Retrofirt:", "Error 001:" + t.getMessage());
            }
        });


    }

    public void setMapPoints() {

        RealmResults<MapPoint> points = realm.where(MapPoint.class).findAll();

        getView().setMaps(points);
    }
}



