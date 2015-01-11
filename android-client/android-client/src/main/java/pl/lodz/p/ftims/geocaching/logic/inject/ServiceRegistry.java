package pl.lodz.p.ftims.geocaching.logic.inject;

import android.app.Activity;
import android.app.Application;

/**
 * Obiekty ServiceRegistry to mapy klas na obiekty tych klas, używane
 * w InjectingLifecycleHandler jako kolekcje zależności do wstrzyknięcia.
 * @author Michał Sośnicki, Andrzej Kurczewski
 */
public interface ServiceRegistry {
    /**
     * Pobiera obiekt danej klasy zarejestrowany w tym obiekcie, bądź null jak go nie ma.
     *
     * @param cls Klasa, której instancja zostanie wyszukana w rejestrze.
     * @param <A> Parametr typu, który informuje typechecker,
     *           iż ta metoda zwraca obiekt klasy przekazanej jako argument.
     * @return Obiekt klasy jeżeli taki jest tu zarejestrowany lub null.
     */
    public <A> A getService(Class<A> cls);

    /**
     * Rejestruje dany obiekt jako instancję danej klasy w tym rejestrze.
     * Istotne jest, iż obiekt zostanie odnaleziony wyłącznie pod tą klasą. Rejestr nie
     * znajdzie go, jeżeli zostanie następnie przeprowadzone wyszukiwanie z argumentem będącym
     * klasą bazową dla klucza, choć nie byłoby to błędne.
     *
     * @param key Klasa, która zostanie kluczem pod którym będzie możliwe znalezienie obiektu.
     * @param service Obiekt, który zostanie zapisany w rejestrze.
     * @param <A> Parametr typu, który pozwala typecheckerowi sprawdzić, iż przekazany
     *           obiekt rzeczywiście jest danej klasy.
     */
    public <A> void registerService(Class<A> key, A service);
}
