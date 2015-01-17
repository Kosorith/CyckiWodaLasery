package pl.lodz.p.ftims.geocaching.logic.gps;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import pl.lodz.p.ftims.geocaching.model.GeoCoords;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class LocationServiceImplTest {

    private LocationServiceImpl locationService;

    @Before
    public void setUp() throws Exception {
        LocationManager locationManager = mock(LocationManager.class);
        locationService = new LocationServiceImpl(locationManager);
    }

    @Test
    public void testOnLocationChanged() throws Exception {
        class TestObserver implements LocationObserver {
            @Override
            public void onLocationChanged(GeoCoords geoCoords) {
                System.out.printf("New location! - %s, %s%n", geoCoords.getLatitude(), geoCoords.getLongitude());
            }
        }
        Location location = mock(Location.class);
        when(location.getLatitude()).thenReturn(0.5, 0.4, 0.3, 0.2, 0.1);
        when(location.getLongitude()).thenReturn(0.1, 0.2, 0.3, 0.4, 0.5);

        TestObserver observer = new TestObserver();

        locationService.subscribe(observer);

        locationService.onLocationChanged(location);
        locationService.onLocationChanged(location);
        locationService.onLocationChanged(location);
        locationService.onLocationChanged(location);
        locationService.onLocationChanged(location);

    }
}