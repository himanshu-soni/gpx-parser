package com.hs.gpxparser.modal;

/**
 * Created by Himanshu on 7/5/2015.
 */
public class Bounds {
    private double minLat;
    private double minLon;
    private double maxLat;
    private double maxLon;

    public Bounds(double minlat, double maxlat, double minlon, double maxlon) {
        this.minLat = minlat;
        this.maxLat = maxlat;
        this.minLon = minlon;
        this.maxLon = maxlon;
    }

    public double getMinLat() {
        return minLat;
    }

    public void setMinLat(double minLat) {
        this.minLat = minLat;
    }

    public double getMinLon() {
        return minLon;
    }

    public void setMinLon(double minLon) {
        this.minLon = minLon;
    }

    public double getMaxLat() {
        return maxLat;
    }

    public void setMaxLat(double maxLat) {
        this.maxLat = maxLat;
    }

    public double getMaxLon() {
        return maxLon;
    }

    public void setMaxLon(double maxLon) {
        this.maxLon = maxLon;
    }

    // TFE, 20180201: helper method to extend bounds easily
    public void extendBounds(final Bounds bounds) {
        this.minLat = Math.min(minLat, bounds.minLat);
        this.maxLat = Math.max(maxLat, bounds.maxLat);
        this.minLon = Math.min(minLon, bounds.minLon);
        this.maxLon = Math.max(maxLon, bounds.maxLon);
    }
}
