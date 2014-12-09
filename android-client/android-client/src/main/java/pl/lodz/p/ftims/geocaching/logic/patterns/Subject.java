package pl.lodz.p.ftims.geocaching.logic.patterns;

/**
 * Created by michal on 12/9/14.
 */
public interface Subject<O> {
    void subscribe(O observer);
    void unsubscribe(O observer);
}
