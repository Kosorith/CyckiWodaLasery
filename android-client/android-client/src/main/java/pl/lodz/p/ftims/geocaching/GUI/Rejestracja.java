package pl.lodz.p.ftims.geocaching.GUI;

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
import pl.lodz.p.ftims.geocaching.GUI.Logowanie;
import pl.lodz.p.ftims.geocaching.R;
import pl.lodz.p.ftims.geocaching.logic.inject.InjectPlz;
import pl.lodz.p.ftims.geocaching.logic.user.LoginService;
import pl.lodz.p.ftims.geocaching.model.Credentials;
import pl.lodz.p.ftims.geocaching.model.Profile;

public class Rejestracja extends ActionBarActivity {

    @InjectPlz
    private LoginService loginService;

	private EditText loginEdit;
	private EditText passwordEdit;
    private EditText nickEdit;
    private EditText emailEdit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rejestracja);

        loginEdit = (EditText) findViewById(R.id.Pole_Login);
        passwordEdit = (EditText) findViewById(R.id.Pole_Haslo);
        nickEdit = (EditText) findViewById(R.id.Pole_Nick);
        emailEdit = (EditText) findViewById(R.id.Pole_Email);

    // Guzik Wroc
        Button Dalej = (Button) findViewById(R.id.Wroc);
        Dalej.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Logowanie.class);
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
	
    public void doRegister(View v) {
        String login = loginEdit.getText().toString();
        String password = passwordEdit.getText().toString();
        String email = emailEdit.getText().toString();
        String nick = nickEdit.getText().toString();

        Credentials credentials = new Credentials(login, password);

        if (!loginService.preverifyCredentials(credentials)) {
            Toast.makeText(getApplicationContext(), "Wpisałeś jakieś bzdury, użytkowniku.", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean ok = loginService.register(credentials, new Profile(nick, email, 0, 0));

        if (ok) {
            Intent intent = new Intent(v.getContext(), Profil.class);
            startActivityForResult(intent, 0);
        } else {
            Toast.makeText(getApplicationContext(), "Rejestracja nie powiodła się!", Toast.LENGTH_SHORT).show();
        }
    }
}
