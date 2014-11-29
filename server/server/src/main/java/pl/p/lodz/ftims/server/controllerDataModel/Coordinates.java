/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.p.lodz.ftims.server.controllerDataModel;

/**
 *
 * @author Wojciech Szałapski
 */
public class Coordinates {

    public static final int EARTH_RADIUS = 6371000;

    private double latitude;

    private double longitude;

    public Coordinates() {
    }

    public Coordinates(String coordinates) {
        String[] parts = coordinates.split(" ");
        latitude = Double.parseDouble(parts[0]);
        longitude = Double.parseDouble(parts[1]);
    }
    
    /**
     * Computes distance in meters between this point and given point. Haversine
     * formula is used to compute the great-circle distance.
     *
     * @param destination Destination point.
     * @return Great-circle distance between this point and the destination.
     */
    public double computeDistance(Coordinates destination) {
        double sourceLatitude = Math.toRadians(latitude);
        double destinationLatitude = Math.toRadians(destination.latitude);
        double sourceLongitude = Math.toRadians(longitude);
        double destinationLongitude = Math.toRadians(destination.longitude);

        double deltaLatitude = sourceLatitude - destinationLatitude;
        double deltaLongitude = sourceLongitude - destinationLongitude;

        double a = Math.sin(deltaLatitude / 2) * Math.sin(deltaLatitude / 2)
                + Math.cos(sourceLatitude) * Math.cos(destinationLatitude)
                * Math.sin(deltaLongitude / 2) * Math.sin(deltaLongitude / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }

    @Override
    public String toString() {
        return latitude + " " + longitude;
    }
    
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}