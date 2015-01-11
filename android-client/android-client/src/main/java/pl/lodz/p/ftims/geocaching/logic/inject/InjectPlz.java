package pl.lodz.p.ftims.geocaching.logic.inject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Adnotacja na pola klas, której szuka InjectingLifecycleHandler, decydując,
 * gdzie wstrzyknąć zależności ze swojego rejestru.
 *
 * @author Michał Sośnicki, Andrzej Kurczewski
 * @see pl.lodz.p.ftims.geocaching.logic.inject.InjectingLifecycleHandler
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface InjectPlz {
}
