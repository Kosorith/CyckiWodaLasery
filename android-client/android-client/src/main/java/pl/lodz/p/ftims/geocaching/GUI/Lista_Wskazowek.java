package pl.lodz.p.ftims.geocaching.GUI;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableRow;

public class Lista_Wskazowek extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista__wskazowek);
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
	
	// pozwala na wybranie wskazowki poprzez klikniecie w rz¹d z danymi w widoku
	// row musi zawierac android:onClick="Wybierz_wskazowke"
	public void Wybierz_wskazowke(View v){

	    TableRow Wybierz = (TableRow) findViewById(v.getId());
	    Wybierz.setOnClickListener(new View.OnClickListener() {

	        public void onClick(View v) {
	            // tu cos powinien zrobic
	        }
	    });
	}
}
