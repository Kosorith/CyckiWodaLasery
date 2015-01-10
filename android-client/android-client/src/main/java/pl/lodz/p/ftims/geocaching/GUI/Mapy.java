package pl.lodz.p.ftims.geocaching.GUI;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import pl.lodz.p.ftims.geocaching.R;
import pl.lodz.p.ftims.geocaching.logic.challenges.ChallengeSolvingService;
import pl.lodz.p.ftims.geocaching.logic.challenges.HintsObserver;
import pl.lodz.p.ftims.geocaching.logic.gps.LocationObserver;
import pl.lodz.p.ftims.geocaching.logic.gps.LocationService;
import pl.lodz.p.ftims.geocaching.logic.inject.InjectPlz;
import pl.lodz.p.ftims.geocaching.model.GeoCoords;
import pl.lodz.p.ftims.geocaching.model.Hint;

public class Mapy extends FragmentActivity implements HintsObserver, LocationObserver {

    @InjectPlz
    private ChallengeSolvingService challengeSolvingService;
    @InjectPlz
    private LocationService locationService;

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private Button actionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapy);
        setUpMapIfNeeded();

        actionButton = (Button) findViewById(R.id.button);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(Mapy.this, actionButton);
                popupMenu.getMenuInflater().inflate(R.menu.menu_mapa_popup, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new ActionPopupClickListener());
                popupMenu.show();
            }
        });
    }

    @Override
    public void onNewHint(Hint hint) {
//  TODO: dodać nowego hinta do jakiejś listy wyświetlającej hinty, może nawet lepszym rozwiązaniem byłoby
//        zostawienie tej metody pustej i zaimplementowanie interfejsu HintObserver bezpośrednio w tamtym activity
    }

    private static final String[] tempDescs =  {"Arktycznie", "Syberyjsko", "Lodowato",
        "Zimno", "Chłodno", "Letnio", "Ciepło", "Cieplej", "Gorąco", "Piekielnie", "Szatańsko"};
    @Override
    public void onHeatChange(int temperature) {
        Toast.makeText(getApplicationContext(), tempDescs[temperature / 10], Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLocationChanged(GeoCoords geoCoords) {
        double latitude = geoCoords.getLatitude();
        double longitude = geoCoords.getLongitude();

        LatLng latLng = new LatLng(latitude, longitude);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
    }

    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_mapa, menu);

        //drugie menu
        /*
        String title = "Element w kodzie";
        int groupId = Menu.NONE;
        int itemId = Menu.FIRST;
        int order = 103;
        menu.add(groupId, itemId, order, title);
        */

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
        */

        //menu górne
        super.onContextItemSelected(item);
        //Intent intent;

        switch(item.getItemId()) {

            case R.id.infoOWsk:
                Intent intent4;
                intent4 = new Intent(Mapy.this, Ranking.class);
                startActivityForResult(intent4,0);
                break;

            case R.id.action_exit:
                AlertDialog.Builder alert2 = new AlertDialog.Builder(this);
                alert2.setTitle("Wyjście");

                alert2.setMessage("Czy na pewno chcesz Wyjść?");

                alert2.setPositiveButton("Tak",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alert2.setNegativeButton("Nie", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                break;

//                Toast.makeText(Profil.this, "Button Clicked :" + item.getTitle(),
//                        Toast.LENGTH_SHORT).show();
//                intent = new Intent(Intent.ACTION_MAIN);
//                intent.addCategory(Intent.CATEGORY_HOME);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);

            case R.id.ekran_Stworz_wyzwanie:
                Intent intent;
                intent = new Intent(Mapy.this, Dodaj_wskazowke_1.class);
                startActivityForResult(intent,0);
                break;

            case R.id.ekran_Ranking:
                Intent intent2;
                intent2 = new Intent(Mapy.this, Ranking.class);
                startActivityForResult(intent2,0);
                break;

            case R.id.ekran_Profil:
                Intent intent3;
                intent3 = new Intent(Mapy.this, Profil.class);
                startActivityForResult(intent3,0);
                break;

            default:
                return false;
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        challengeSolvingService.subscribe(this);
        // TODO: Trzeba gdzieś wywołać challengeSolvingService.startChallenge() by to cokolwiek robiło
        locationService.subscribe(this);
    }

    private class ActionPopupClickListener implements PopupMenu.OnMenuItemClickListener {
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.infoOWsk:
                    Intent intent7;
                    intent7 = new Intent(Mapy.this, informacje_o_wskazowce.class);
                    startActivityForResult(intent7, 0);
                    break;
            }
            /*
            Toast.makeText(Mapy.this, "Button Clicked :" + item.getTitle(),
                Toast.LENGTH_SHORT).show();
            */
            return true;
        }
    }
}
