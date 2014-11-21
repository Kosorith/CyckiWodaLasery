package pl.lodz.p.ftims.geocaching.model;

/**
 * Created by michal on 11/19/14.
 */
public class GeoCoords {

    private double latitude;  // TODO: Jak to zamodelować? Czy GPS API nie dostarcza własnych klas?
    private double longitude; // Jest klasa Location: http://developer.android.com/reference/android/location/Location.html
                              // udostępnia więcej informacji niż nam trzeba, w tym koordynaty jako double, więc chyba tak

    public GeoCoords() {
    }

    public GeoCoords(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
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
