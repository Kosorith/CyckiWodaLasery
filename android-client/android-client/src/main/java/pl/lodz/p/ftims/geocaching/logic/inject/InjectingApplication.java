package pl.lodz.p.ftims.geocaching.logic.inject;

import android.app.Application;

/**
 * Rozszerzenie Androidowej klasy Application, rejestrujące klasę wstrzykującą zależności
 * do Androidowych Activity, gdy te są tworzone.
 * @author Michał Sośnicki, Andrzej Kurczewski
 * @see pl.lodz.p.ftims.geocaching.logic.inject.InjectingLifecycleHandler
 */
public class InjectingApplication extends Application {

    /**
     * Wykonuje wszystkie czynności, które zwykle wykonuje Androidowe Application przy tworzeniu
     * oraz rejestruje InjectingLifecycleHandler.
     */
    @Override
    public void onCreate() {
        super.onCreate();

        ServiceRegistryImpl serviceRegistry = new ServiceRegistryImpl();
        serviceRegistry.initialize(this);

        InjectingLifecycleHandler injectingHandler = new InjectingLifecycleHandler(serviceRegistry);
        registerActivityLifecycleCallbacks(injectingHandler);
    }

}
