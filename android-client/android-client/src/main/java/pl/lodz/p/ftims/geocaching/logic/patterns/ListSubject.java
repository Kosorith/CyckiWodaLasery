package pl.lodz.p.ftims.geocaching.logic.patterns;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michal on 12/9/14.
 */
public class ListSubject<O> implements Subject<O> {

    protected List<O> observers = new ArrayList<O>();

    @Override
    public void subscribe(O observer) {
        observers.add(observer);
    }

    @Override
    public void unsubscribe(O observer) {
        observers.remove(observer);
    }

}
