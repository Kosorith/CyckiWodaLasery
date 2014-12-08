package pl.lodz.p.ftims.geocaching.logic.inject;

import android.app.Application;

/**
 * Created by michal on 12/8/14.
 */
public class InjectingApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ServiceRegistryImpl serviceRegistry = new ServiceRegistryImpl();
        serviceRegistry.initialize(this);

        InjectingLifecycleHandler injectingHandler = new InjectingLifecycleHandler(serviceRegistry);
        registerActivityLifecycleCallbacks(injectingHandler);
    }

}
