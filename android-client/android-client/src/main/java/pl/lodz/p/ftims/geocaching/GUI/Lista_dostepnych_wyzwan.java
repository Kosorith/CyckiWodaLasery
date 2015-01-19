package pl.lodz.p.ftims.geocaching.GUI;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableRow;

import pl.lodz.p.ftims.geocaching.R;


/**
 *
 */
public class Lista_dostepnych_wyzwan extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_dostepnych_wyzwan);
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
        //Intent intent;

        switch(item.getItemId()) {

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
                
            case R.id.ekran_Mapa:
                Intent intent4;
                intent3 = new Intent(Profil.this, Mapy.class);
                startActivityForResult(intent3,0);
                break;

            default:
                return false;
        }
        return true;
    }
	
	// pozwala na wybranie wyzwania poprzez klikniecie w rz�d z danymi w widoku
	// row musi zawierac android:onClick="Wybierz_wyzwanie"
	public void Wybierz_wyzwanie(View v){

	    TableRow Wybierz = (TableRow) findViewById(v.getId());
	    Wybierz.setOnClickListener(new View.OnClickListener() {

	        public void onClick(View v) {
	            // tu cos powinien zrobic
	        }
	    });
	}	
}


