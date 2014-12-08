package pl.lodz.p.ftims.geocaching.logic.inject;

import android.app.Activity;
import android.app.Application;

/**
 * Created by michal on 12/8/14.
 */
public interface ServiceRegistry {

    public <A> A getService(Class<A> cls);
    public <A, B> void registerService(Class<A> key, B service);

}
