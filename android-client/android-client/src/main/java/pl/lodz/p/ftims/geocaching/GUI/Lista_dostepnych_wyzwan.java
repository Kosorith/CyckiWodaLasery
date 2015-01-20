package pl.lodz.p.ftims.geocaching.GUI;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;

import android.widget.TextView;
import pl.lodz.p.ftims.geocaching.R;
import pl.lodz.p.ftims.geocaching.logic.challenges.ChallengeSolvingService;
import pl.lodz.p.ftims.geocaching.logic.challenges.ChallengesService;
import pl.lodz.p.ftims.geocaching.logic.gps.LocationService;
import pl.lodz.p.ftims.geocaching.logic.inject.InjectPlz;
import pl.lodz.p.ftims.geocaching.model.Challenge;
import pl.lodz.p.ftims.geocaching.model.ChallengeStub;
import pl.lodz.p.ftims.geocaching.model.GeoCoords;

import java.util.Iterator;
import java.util.List;


/**
 *
 */
public class Lista_dostepnych_wyzwan extends ActionBarActivity {

    @InjectPlz
    private ChallengesService challengesService;
    @InjectPlz
    private ChallengeSolvingService challengeSolvingService;
    @InjectPlz
    private LocationService locationService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_dostepnych_wyzwan);

        List<ChallengeStub> challenges = challengesService.getChallengeList();
        wyswieltChallenge(challenges);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lista_dostepnych_wyzwan, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
        //menu górne
        super.onContextItemSelected(item);
        Intent intent;

        switch(item.getItemId()) {

            case R.id.ekran_Stworz_wyzwanie:
                intent = new Intent(Lista_dostepnych_wyzwan.this, Dodaj_wskazowke_1.class);
                startActivityForResult(intent,0);
                break;

            case R.id.ekran_Ranking:
                intent = new Intent(Lista_dostepnych_wyzwan.this, Ranking.class);
                startActivityForResult(intent,0);
                break;

            case R.id.ekran_Profil:
                intent = new Intent(Lista_dostepnych_wyzwan.this, Profil.class);
                startActivityForResult(intent,0);
                break;
                
            case R.id.ekran_Mapa:
                intent = new Intent(Lista_dostepnych_wyzwan.this, Mapy.class);
                startActivityForResult(intent,0);
                break;

            default:
                return false;
        }
        return true;
    }

    public void filter1km(View v) {
        List<ChallengeStub> challenges = challengesService.getChallengeList();
        filtrujOdleglosc(challenges, 0.0f, 1000.0f);
        wyswieltChallenge(challenges);
    }

    public void filter14km(View v) {
        List<ChallengeStub> challenges = challengesService.getChallengeList();
        filtrujOdleglosc(challenges, 1000.0f, 4000.0f);
        wyswieltChallenge(challenges);
    }

    public void filter410km(View v) {
        List<ChallengeStub> challenges = challengesService.getChallengeList();
        filtrujOdleglosc(challenges, 4000.0f, 10000.0f);
        wyswieltChallenge(challenges);
    }

    public void filterPublic(View v) {
        List<ChallengeStub> challenges = challengesService.getChallengeList();
        filtrujPubliczne(challenges, false);
        wyswieltChallenge(challenges);
    }

    public void filterPrivate(View v) {
        List<ChallengeStub> challenges = challengesService.getChallengeList();
        filtrujPubliczne(challenges, true);
        wyswieltChallenge(challenges);
    }

	private void Wybierz_wyzwanie(ChallengeStub stub) {
        challengeSolvingService.startChallenge(stub);
	}

	private void wyswieltChallenge(List<ChallengeStub> challenges) {
        TableLayout challengeTable = (TableLayout) findViewById(R.id.wyzwaniaLayout);

        while (challengeTable.getChildCount() > 1) {
            challengeTable.removeViewAt(1);
        }

        GeoCoords currentLocation = locationService.getCurrentLocation();
        for (ChallengeStub stub : challenges) {
            float distance = currentLocation == null ? 0.0f : stub.getLocation().distanceTo(currentLocation);
            TableRow newRow = (TableRow) LayoutInflater.from(Lista_dostepnych_wyzwan.this).inflate(R.layout.wyzwanie_row, null);
            ((TextView) newRow.findViewById(R.id.wyzwanieRowNazwa)).setText(stub.getName());
            ((TextView) newRow.findViewById(R.id.wyzwanieRowOdleglosc)).setText(currentLocation == null ? "nieznana" : String.valueOf(distance));
            ((TextView) newRow.findViewById(R.id.wyzwanieRowDostep)).setText(stub.isPublicAccess() ? "publiczny" : "prywatny");

            final ChallengeStub currentStub = stub;
            newRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Wybierz_wyzwanie(currentStub);
                }
            });
            challengeTable.addView(newRow);
        }
	}

    private void filtrujPubliczne(List<ChallengeStub> challenges, boolean usunZTymAccessem) {
        Iterator<ChallengeStub> it = challenges.iterator();
        while (it.hasNext()) {
            ChallengeStub stub = it.next();
            if (stub.isPublicAccess() == usunZTymAccessem) {
                it.remove();
            }
        }
    }

    private void filtrujOdleglosc(List<ChallengeStub> challenges, float min, float max) {
        GeoCoords currentLocation = locationService.getCurrentLocation();
        if (currentLocation == null) {
            return;
        }

        Iterator<ChallengeStub> it = challenges.iterator();
        while (it.hasNext()) {
            ChallengeStub stub = it.next();
            float distance = stub.getLocation().distanceTo(currentLocation);
            if (distance > max || distance < min) {
                it.remove();
            }
        }
    }

}


