package pl.lodz.p.ftims.geocaching.GUI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import pl.lodz.p.ftims.geocaching.R;
import pl.lodz.p.ftims.geocaching.logic.inject.InjectPlz;
import pl.lodz.p.ftims.geocaching.logic.user.LoginService;


public class ZmianaHasla extends Activity {

    @InjectPlz
    private LoginService loginService;

    private EditText oldPassEdit;
    private EditText newPass1Edit;
    private EditText newPass2Edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zmiana_hasla);

        oldPassEdit = (EditText) findViewById(R.id.StareHaslo);
        newPass1Edit = (EditText) findViewById(R.id.NoweHaslo);
        newPass2Edit = (EditText) findViewById(R.id.NoweHasloPowtorz);
    }

    public void CofnijZmianeHasla(View v){
        Intent intent = new Intent(v.getContext(),Profil.class);
        startActivityForResult(intent,0);
    }

    public void ZatwierdźZmianaHasla(View v) {
        String oldPass = oldPassEdit.getText().toString();
        String newPass1 = newPass1Edit.getText().toString();
        String newPass2 = newPass2Edit.getText().toString();

        if (!newPass1.equals(newPass2)) {
            Toast.makeText(getApplicationContext(), "Wpisane hasła różnią się.", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean ok = loginService.changePassword(oldPass, newPass1);

        if (ok) {
            Intent intent = new Intent(v.getContext(), Profil.class);
            startActivityForResult(intent, 0);
        }
        else {
            Toast.makeText(getApplicationContext(), "Zmiana hasła nie powiodła się.", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_zmiana_hasla, menu);
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
}
