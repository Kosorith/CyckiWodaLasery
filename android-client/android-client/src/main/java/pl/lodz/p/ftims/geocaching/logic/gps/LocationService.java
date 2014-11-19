package pl.lodz.p.ftims.geocaching.logic.gps;

import pl.lodz.p.ftims.geocaching.model.GeoCoords;

/**
 * Created by michal on 11/19/14.
 */
public interface LocationService {

    GeoCoords getCurrentLocation();

    void subscribe(LocationObserver locationObserver);

    void unsubscribe(LocationObserver locationObserver);

}
