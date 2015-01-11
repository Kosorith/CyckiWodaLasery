package pl.lodz.p.ftims.geocaching.logic.gps;

import pl.lodz.p.ftims.geocaching.logic.patterns.Subject;
import pl.lodz.p.ftims.geocaching.model.GeoCoords;

/**
 * Usługa przekazująca informacje o aktualnym położeniu użytkownika, zarówno przez
 * synchronicznie jak i przez zarejestrowanie się jako obserwator położenia.
 * @author Michał Sośnicki, Andrzej Kurczewski
 */
public interface LocationService extends Subject<LocationObserver> {

    /**
     * Metoda zwraca obecne położenie użytkownika, bądź null jeżeli nie da się tego określić.
     * @return Aktualne koordynaty użytkownika na kuli ziemskiej, bądź null.
     */
    GeoCoords getCurrentLocation();

}
