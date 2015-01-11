package pl.lodz.p.ftims.geocaching.logic.patterns;

/**
 * Zawiera metody pomocne w operowaniu na stringach.
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
public class StringUtils {
    /**
     * Sprawdza czy argument nie jest nullem i nie jest pusty.
     * @param string String do sprawdzenia.
     * @return Rezultat walidacji.
     */
    public static boolean notEmpty(String string) {
        return string != null && !string.isEmpty();
    }
}
