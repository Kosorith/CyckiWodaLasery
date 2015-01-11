package pl.lodz.p.ftims.geocaching.logic.gps;

import pl.lodz.p.ftims.geocaching.model.GeoCoords;

/**
 * Interfejs klas, które chcą być informowane o zmianach położenia użytkownika.
 * @author Michał Sośnicki, Andrzej Kurczewski
 */
public interface LocationObserver {

    /**
     * Metoda wywoływana, gdy obserwowany uzyskuje aktualne informacje o położeniu użytkownika.
     * Nie są one koniecznie różne od poprzednich koordynatów.
     * @param geoCoords Aktualne koordynaty użytkownika na kuli ziemskiej.
     */
    void onLocationChanged(GeoCoords geoCoords);

}
