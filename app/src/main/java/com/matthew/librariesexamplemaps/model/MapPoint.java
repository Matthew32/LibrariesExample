package com.matthew.librariesexamplemaps.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Matthew on 15/10/2017.
 */

public class MapPoint extends RealmObject {


    @PrimaryKey
    private String name;
    private double xPoint;
    private double yPoint;

    public MapPoint(String name, double xPoint, double yPoint) {
        this.name = name;
        this.xPoint = xPoint;
        this.yPoint = yPoint;
    }

    public MapPoint() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getxPoint() {
        return xPoint;
    }

    public void setxPoint(double xPoint) {
        this.xPoint = xPoint;
    }

    public double getyPoint() {
        return yPoint;
    }

    public void setyPoint(double yPoint) {
        this.yPoint = yPoint;
    }
}
