package pl.lodz.p.ftims.geocaching.logic.inject;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.lang.reflect.Field;

/**
 * Implementacja zbioru callbacków do cyklu życia Androidowych Activity, który na zdarzenie
 * utworzenia Activity reaguje wstrzykując w nią zależności z rejestru usług.
 * Pozostałe wydarzenia z cyklu życia Activity są ignorowane.
 * @author Michał Sośnicki, Andrzej Kurczewski
 */
public class InjectingLifecycleHandler implements Application.ActivityLifecycleCallbacks {

    private ServiceRegistry serviceRegistry;

    /**
     * Tworzy nowy InjectingLifecycleHandler, wstrzykujący zależności do Activity
     * z przekazanego jako argument rejestru usług.
     * @param serviceRegistry Rejestr usług, w ktorym ten obiekt będzie wyszukiwał zależności.
     */
    public InjectingLifecycleHandler(ServiceRegistry serviceRegistry) {
        this.serviceRegistry = serviceRegistry;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        injectServices(activity);
    }

    /**
     * Metoda sprawdza po kolei wszystkie pola w przekazanym obiekcie, szukając tych
     * z adnotacją @InjectPlz i ustawia je na obiekty wyszukane w ServiceRegistry.
     * Wyszukiwanie w ServiceRegistry zwraca uwagę wyłącznie na zgodność typów
     * wskazanego pola i typu, pod jakim obiekt jest zarejestrowany.
     *
     * @param object Obiekt, w którego pola zostaną wstrzyknięte usługi.
     */
    public void injectServices(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(InjectPlz.class)) {
                boolean isAccessible = field.isAccessible();
                field.setAccessible(true);

                Object serviceToInject = serviceRegistry.getService(field.getType());
                try {
                    field.set(object, serviceToInject);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                field.setAccessible(isAccessible);
            }
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {
    }

    @Override
    public void onActivityResumed(Activity activity) {
    }

    @Override
    public void onActivityPaused(Activity activity) {
    }

    @Override
    public void onActivityStopped(Activity activity) {
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
    }
}
