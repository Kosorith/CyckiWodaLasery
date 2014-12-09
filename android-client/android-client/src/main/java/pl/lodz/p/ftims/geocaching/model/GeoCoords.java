package pl.lodz.p.ftims.geocaching.model;

import android.location.Location;

import java.util.Date;

/**
 * Created by michal on 11/19/14.
 */
public class GeoCoords {

    private double latitude;  // TODO: Jak to zamodelować? Czy GPS API nie dostarcza własnych klas?
    private double longitude;

    public GeoCoords() {
    }

    public GeoCoords(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Metoda tworząca obiekt GeoCoords z android.location.Location.
     * @param location Obiekt klasy Location z biblioteki androidowej.
     * @return GeoCoords z szerokością i długością geograficzną wziętą z argumentu.
     */
    public static GeoCoords fromLocation(Location location) {
        return new GeoCoords(location.getLatitude(), location.getLongitude());
    }

    /**
     * Metoda tworząca android.location.Location z obiektu GeoCoords.
     * Powstały obiekt jest ogołocony z wielu danych zwykle dostępnych w Location, lecz posiada niezbędną
     * szerokość i długość geograficzną.
     * @return Obiekt klasy Location ze biblioteki androidowej odpowiadający tym GeoCoordsom.
     */
    public Location toLocation() {
        Location location = new Location("");

        location.setLatitude(latitude);
        location.setLongitude(longitude);

        return location;
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
