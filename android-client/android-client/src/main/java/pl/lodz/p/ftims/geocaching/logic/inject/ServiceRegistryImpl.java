package pl.lodz.p.ftims.geocaching.logic.inject;

import android.app.Application;
import android.content.Context;
import android.location.LocationManager;
import pl.lodz.p.ftims.geocaching.dao.ChallengeAccessDao;
import pl.lodz.p.ftims.geocaching.dao.IChallengeAccess;
import pl.lodz.p.ftims.geocaching.dao.IProfilesAccess;
import pl.lodz.p.ftims.geocaching.dao.ProfilesAccessDao;
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
        IChallengeAccess challengeAccess = new ChallengeAccessDao(application.getApplicationContext());
        IProfilesAccess profilesAccess = new ProfilesAccessDao(application.getApplicationContext());

        LocationManager locationManager = (LocationManager) application.getSystemService(Context.LOCATION_SERVICE);
        LocationService locationService = new LocationServiceImpl(locationManager);
        registerService(LocationService.class, locationService);

        LoginService loginService = new LoginServiceImpl(application, profilesAccess);
        registerService(LoginService.class, loginService);

        registerService(ChallengeCreationService.class, new ChallengeCreationServiceImpl(challengeAccess));

        registerService(ChallengesService.class, new ChallengesServiceImpl(locationService, challengeAccess));

        registerService(ChallengeSolvingService.class,
            new ChallengeSolvingServiceImpl(locationService, loginService, challengeAccess));

        registerService(ProfilesService.class, new ProfilesServiceImpl(loginService, profilesAccess));
    }

    @Override
    public <A> A getService(Class<A> cls) {
        return (A) services.get(cls);
    }

    @Override
    public <A> void registerService(Class<A> key, A service) {
        services.put(key, service);
    }

}
