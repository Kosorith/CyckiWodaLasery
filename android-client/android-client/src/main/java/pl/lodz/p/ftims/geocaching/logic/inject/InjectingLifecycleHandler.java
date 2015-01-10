package pl.lodz.p.ftims.geocaching.logic.inject;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.lang.reflect.Field;

/**
 * Created by michal on 12/8/14.
 */
public class InjectingLifecycleHandler implements Application.ActivityLifecycleCallbacks {

    private ServiceRegistry serviceRegistry;

    public InjectingLifecycleHandler(ServiceRegistry serviceRegistry) {
        this.serviceRegistry = serviceRegistry;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        injectServices(activity);
    }

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
