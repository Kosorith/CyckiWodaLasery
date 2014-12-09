package pl.lodz.p.ftims.geocaching.logic.inject;

import android.app.Application;
import android.content.Context;
import android.location.LocationManager;
import pl.lodz.p.ftims.geocaching.logic.challenges.*;
import pl.lodz.p.ftims.geocaching.logic.gps.LocationService;
import pl.lodz.p.ftims.geocaching.logic.gps.LocationServiceImpl;
import pl.lodz.p.ftims.geocaching.logic.user.LoginService;
import pl.lodz.p.ftims.geocaching.logic.user.LoginServiceImpl;
import pl.lodz.p.ftims.geocaching.logic.user.ProfilesService;
import pl.lodz.p.ftims.geocaching.logic.user.ProfilesServiceImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by michal on 12/8/14.
 */
public class ServiceRegistryImpl implements ServiceRegistry {

    private Map<Class, Object> services = new HashMap<Class, Object>();

    public ServiceRegistryImpl() {
    }

    public void initialize(Application application) {
        registerService(ChallengeCreationService.class, new ChallengeCreationServiceImpl());

        registerService(ChallengesService.class, new ChallengesServiceImpl());

        registerService(ChallengeSolvingService.class, new ChallengeSolvingServiceImpl());

        LocationManager locationManager = (LocationManager) application.getSystemService(Context.LOCATION_SERVICE);
        registerService(LocationService.class, new LocationServiceImpl(locationManager));

        registerService(LoginService.class, new LoginServiceImpl(application));

        registerService(ProfilesService.class, new ProfilesServiceImpl());
    }

    @Override
    public <A> A getService(Class<A> cls) {
        return (A) services.get(cls);
    }

    @Override
    public <A, B> void registerService(Class<A> key, B service) {
        services.put(key, service);
    }

}
