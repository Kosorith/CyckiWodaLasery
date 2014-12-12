package pl.lodz.p.ftims.geocaching.logic.challenges;

import pl.lodz.p.ftims.geocaching.model.Hint;

/**
 * Created by michal on 11/19/14.
 */
public interface HintsObserver {

    void onNewHint(Hint hint);

    /**
     * Temperatura to wartość od 0 do 100. 
     * Wartość rośnie ze zbliżaniem się do celu. 
     */
    void onHeatChange(int temperature);

}
