package com.matthew.librariesexamplemaps.server.utils;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

/**
 * Created by Matthew on 15/10/2017.
 */

public class JsonResponse {
    @SerializedName("num")
    private int num;
    @SerializedName("items")
    private HashMap<String, Object> items;

    public JsonResponse(int num, HashMap<String, Object> items) {
        this.num = num;
        this.items = items;
    }

    public JsonResponse() {
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public HashMap<String, Object> getItems() {
        return items;
    }

    public void setItems(HashMap<String, Object> items) {
        this.items = items;
    }
}
