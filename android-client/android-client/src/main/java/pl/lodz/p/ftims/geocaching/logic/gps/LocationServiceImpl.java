package pl.lodz.p.ftims.geocaching.logic.gps;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import pl.lodz.p.ftims.geocaching.logic.patterns.ListSubject;
import pl.lodz.p.ftims.geocaching.model.GeoCoords;

/**
 * Implementacja usługi lokalizacyjnej, korzystająca z Androidowego LocationManagera
 * dowolnych Providerow i aktualizująca stan 5 sekund.
 * @author Michał Sośnicki, Andrzej Kurczewski
 */
public class LocationServiceImpl extends ListSubject<LocationObserver>
                                 implements LocationService, LocationListener {

    private static final int UPDATE_DELAY = 5000;
    private static final int UPDATE_DIST = 0;

    private LocationManager locationManager;

    private String bestProvider;

    /**
     * Tworzy nową usługę lokalizacyjną, wykorzystującą przekazanego Androidowego LocationManagera
     * do pobierania informacji o położeniu. Rejestruje się u tego Managera, by uzyskiwać
     * je co 5 sekund, z aktualizacjami w innym wątku.
     * @param locationManager Androidowy LocationManager, w którym ta usługa się zarejestruje, by uzyskiwać
     *                        informacje o aktualnym położeniu.
     */
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
        // ignorowany
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
