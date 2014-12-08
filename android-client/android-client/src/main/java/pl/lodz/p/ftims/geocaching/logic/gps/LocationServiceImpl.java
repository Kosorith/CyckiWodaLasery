package pl.lodz.p.ftims.geocaching.logic.gps;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import pl.lodz.p.ftims.geocaching.model.GeoCoords;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michal on 12/2/14.
 */
public class LocationServiceImpl implements LocationService, LocationListener {

    private LocationManager locationManager;
    private List<LocationObserver> locationObservers = new ArrayList<LocationObserver>();

    public LocationServiceImpl(LocationManager locationManager) {
        this.locationManager = locationManager;
    }

    @Override
    public GeoCoords getCurrentLocation() {
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        return GeoCoords.fromLocation(location);
    }

    @Override
    public void subscribe(LocationObserver locationObserver) {
        locationObservers.add(locationObserver);
    }

    @Override
    public void unsubscribe(LocationObserver locationObserver) {
        locationObservers.remove(locationObserver);
    }

    @Override
    public void onLocationChanged(Location location) {
        GeoCoords geoCoords = GeoCoords.fromLocation(location);
        for (LocationObserver locationObserver : locationObservers) {
            locationObserver.onLocationChanged(geoCoords);
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // Ignorowany póki co
    }

    @Override
    public void onProviderEnabled(String provider) {
        // Ignorowany póki co
    }

    @Override
    public void onProviderDisabled(String provider) {
        // Ignorowany póki co
    }
}
