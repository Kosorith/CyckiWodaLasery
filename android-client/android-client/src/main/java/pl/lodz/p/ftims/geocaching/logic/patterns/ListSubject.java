package pl.lodz.p.ftims.geocaching.logic.patterns;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementacja klasy obserwowanej w realizowanym wzorcu obserwatora, wykorzystującej
 * listę do przechowywania powiadamianych obiektów.
 * @author Michał Sośnicki, Andrzej Kurczewski
 */
public abstract class ListSubject<O> implements Subject<O> {

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
