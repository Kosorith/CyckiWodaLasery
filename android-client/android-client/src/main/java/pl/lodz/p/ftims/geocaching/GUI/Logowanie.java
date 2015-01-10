package pl.lodz.p.ftims.geocaching.GUI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import pl.lodz.p.ftims.geocaching.R;
import pl.lodz.p.ftims.geocaching.logic.inject.InjectPlz;
import pl.lodz.p.ftims.geocaching.logic.user.LoginService;
import pl.lodz.p.ftims.geocaching.model.Credentials;


public class Logowanie extends Activity {

    @InjectPlz
    private LoginService loginService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logowanie);

        final EditText loginEdit = (EditText) findViewById(R.id.Pole_Login);
        final EditText passwordEdit = (EditText) findViewById(R.id.Pole_Haslo);
        final CheckBox rememberBox = (CheckBox) findViewById(R.id.Zapamietac);

        Button OK = (Button) findViewById(R.id.OK);
        OK.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String login = loginEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                Credentials credentials = new Credentials(login, password);
                boolean remember = rememberBox.isChecked();
                boolean ok = loginService.login(credentials, remember);
                if (ok) {
                    Intent intent = new Intent(v.getContext(), Profil.class);
                    startActivityForResult(intent, 0);
                } else {
                    Toast.makeText(getApplicationContext(), "Nie udało się zalogować!", Toast.LENGTH_SHORT).show();

                    // TODO: ZOSTAWIŁEM TO PONIŻEJ BY DAŁO SIĘ PRZEJŚĆ DALEJ MIMO BRAKU SERWERA
                    Intent intent = new Intent(v.getContext(), Profil.class);
                    startActivityForResult(intent, 0);
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_logowanie, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



// PROFIL
 // Tworzenie wiersza Tabeli
    public void profileFillTable(){
        // szukanie tableLayout
        TableLayout tl = (TableLayout)findViewById(R.id.profileTableLayout);
        // tworzenie tableRow
        TableRow tr = new TableRow(this);
        tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,
                                TableRow.LayoutParams.WRAP_CONTENT));
        // tworzenie textView
        TextView tv = new TextView(this);
        tv.setText("TUTAJ PODAJ TEKST");
        // dodanie textView do tableRow
        tr.addView(tv);
        // dodanie tableRow do tableLayout
        tl.addView(tr, new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,
                            TableRow.LayoutParams.WRAP_CONTENT));

    }
}
