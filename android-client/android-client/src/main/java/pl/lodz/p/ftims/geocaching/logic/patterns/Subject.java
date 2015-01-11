package pl.lodz.p.ftims.geocaching.logic.patterns;

/**
 * Interfejs klas, które uczestniczą w realizacji wzorca obserwator
 * jako klasy obserwowane, przyjmujące obserwatorów.
 * @author Michał Sośnicki, Andrzej Kurczewski
 */
public interface Subject<O> {

    /**
     * Rejestruje obserwatora do powiadamiania.
     * @param observer Obiekt, który będzie zarejestrowany jako obserwator.
     */
    void subscribe(O observer);

    /**
     * Usuwa obserwatora z rejestru w tym obiekcie.
     * @param observer Obserwator, który chce przestać otrzymywać powiadomienia.
     */
    void unsubscribe(O observer);

}
