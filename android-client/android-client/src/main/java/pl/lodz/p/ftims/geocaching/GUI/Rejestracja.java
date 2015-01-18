package pl.lodz.p.ftims.geocaching;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Rejestracja extends ActionBarActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rejestracja);	

		/*
// Guzik OK		
	  Button Dalej = (Button) findViewById(R.id.OK);
        Dalej.setOnClickListener(new View.OnClickListener(){
    // Rejestracja wzieta z Logowania
            public void onClick(View v) {
                String login = loginEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                Credentials credentials = new Credentials(login, password);

                if (!loginService.preverifyCredentials(credentials)) {
                    Toast.makeText(getApplicationContext(), "Wpisałeś jakieś bzdury, użytkowniku.", Toast.LENGTH_SHORT).show();
                    return;
                }
        });
        */
// Guzik Wroc		
        Button Dalej = (Button) findViewById(R.id.Wroc);
        Dalej.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),Logowanie.class);
                startActivityForResult(intent,0);
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.rejestracja, menu);
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
	

}
