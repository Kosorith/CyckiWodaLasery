package pl.lodz.p.ftims.geocaching.GUI;

import android.support.v7.app.ActionBarActivity;
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
import pl.lodz.p.ftims.geocaching.logic.challenges.HintsObserver;
import pl.lodz.p.ftims.geocaching.logic.inject.InjectPlz;
import pl.lodz.p.ftims.geocaching.model.Challenge;
import pl.lodz.p.ftims.geocaching.model.ChallengeStub;
import pl.lodz.p.ftims.geocaching.model.Hint;

import java.util.List;

public class Lista_Wskazowek extends ActionBarActivity implements HintsObserver {

	@InjectPlz
	private ChallengeSolvingService challengeSolvingService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista__wskazowek);
		challengeSolvingService.subscribe(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lista__wskazowek, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onNewHint(Hint hint) {
        TableLayout hintTable = (TableLayout) findViewById(R.id.wskazowkiLayout);

/*        while (challengeTable.getChildCount() > 1) {
            challengeTable.removeViewAt(1);
        } Sorki, mój błąd */

		TableRow newRow = (TableRow) LayoutInflater.from(Lista_Wskazowek.this).inflate(R.layout.wskazowka_row, null);
		((TextView) newRow.findViewById(R.id.wskazowkaRowLP)).setText(hintTable.getChildCount());
		((TextView) newRow.findViewById(R.id.wskazowkaRowNazwa)).setText(hint.getDescription());

        final Hint finalHint = hint;
        newRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Wybierz_wskazowke(finalHint);
            }
        });
        hintTable.addView(newRow);
	}

	@Override
	public void onHeatChange(int temperature) {
		// ignore
	}

	private void Wybierz_wskazowke(Hint hint) {
		// tutaj przydałoby sie przejść do widoku ze szczegółami hinta, nie zrobię już tego
	}

}
