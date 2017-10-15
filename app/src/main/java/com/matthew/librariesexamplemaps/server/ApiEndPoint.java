package com.matthew.librariesexamplemaps.server;

import com.matthew.librariesexamplemaps.server.utils.JsonResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Matthew on 15/10/2017.
 */

public interface ApiEndPoint {
    @GET("equipaments/search?subambit=parcs")
    Call<HashMap<String, Object>> getParks();

}
