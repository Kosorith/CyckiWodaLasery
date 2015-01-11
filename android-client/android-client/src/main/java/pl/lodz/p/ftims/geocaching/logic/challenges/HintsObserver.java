package pl.lodz.p.ftims.geocaching.logic.challenges;

import pl.lodz.p.ftims.geocaching.model.Hint;

/**
 * Klasy implementujące ten interfejs potrafią reagować na wydarzenia otrzymania nowej
 * wskazówki oraz zmiany temperatury, zależnie od trybu naprowadzania.
 * @author Michał Sośnicki, Andrzej Kurczewski
 */
public interface HintsObserver {

    /**
     * Reaguje na nową wskazówkę we wskazówkowym trybie naprowadzania.
     * @param hint Nowa wskazówka.
     */
    void onNewHint(Hint hint);

    /**
     * Reaguje na zmianę temperatury w cieplnym trybie naprowadzania.
     * Temperatura to wartość od 0 do 100.
     * Wartość rośnie ze zbliżaniem się do celu. 
     * @param temperature Nowa temperatura.
     */
    void onHeatChange(int temperature);

}
