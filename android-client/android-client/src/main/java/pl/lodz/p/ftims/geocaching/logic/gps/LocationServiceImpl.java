package pl.lodz.p.ftims.geocaching.logic.gps;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import pl.lodz.p.ftims.geocaching.logic.patterns.ListSubject;
import pl.lodz.p.ftims.geocaching.model.GeoCoords;

/**
 * Created by michal on 12/2/14.
 */
public class LocationServiceImpl extends ListSubject<LocationObserver>
                                 implements LocationService, LocationListener {

    private static final int UPDATE_DELAY = 5000;
    private static final int UPDATE_DIST = 0;

    private LocationManager locationManager;

    private String bestProvider;

    public LocationServiceImpl(LocationManager locationManager) {
        this.locationManager = locationManager;
        bestProvider = locationManager.getBestProvider(new Criteria(), true);
        locationManager.requestLocationUpdates(UPDATE_DELAY, UPDATE_DIST, new Criteria(), this, null);
    }

    @Override
    public GeoCoords getCurrentLocation() {
        Location location = locationManager.getLastKnownLocation(bestProvider);
        return location == null ? null : GeoCoords.fromLocation(location);
    }


    @Override
    public void onLocationChanged(Location location) {
        GeoCoords geoCoords = GeoCoords.fromLocation(location);
        for (LocationObserver locationObserver : observers) {
            locationObserver.onLocationChanged(geoCoords);
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // Ignorowany póki co
    }

    @Override
    public void onProviderEnabled(String provider) {
        bestProvider = locationManager.getBestProvider(new Criteria(), true);
    }

    @Override
    public void onProviderDisabled(String provider) {
        bestProvider = locationManager.getBestProvider(new Criteria(), true);
    }
}
