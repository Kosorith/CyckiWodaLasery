package pl.lodz.p.ftims.geocaching.GUI;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import pl.lodz.p.ftims.geocaching.R;
import pl.lodz.p.ftims.geocaching.logic.inject.InjectPlz;
import pl.lodz.p.ftims.geocaching.logic.user.ProfilesService;
import pl.lodz.p.ftims.geocaching.model.Profile;

public class Profil extends Activity {

    @InjectPlz
    private ProfilesService profilesService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_profil2);

        Profile profile = profilesService.getCurrentProfile();
        if (profile != null) {
            TextView nickText = (TextView) findViewById(R.id.nickText);
            TextView emailText = (TextView) findViewById(R.id.emailText);
            TextView pointsText = (TextView) findViewById(R.id.zdobPktText);
            TextView completedText = (TextView) findViewById(R.id.ukonczylText);
            nickText.setText(profile.getNick());
            emailText.setText(profile.getEmail());
            pointsText.setText(String.valueOf(profile.getPoints()));
            completedText.setText(String.valueOf(profile.getCompletedChallenges()));
        }

        // pierwsze menu
        /*
        button = (Button) findViewById(R.id.ResetButton);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(Profil.this, button);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
                    public boolean onMenuItemClick(MenuItem item){
                        Toast.makeText(Profil.this, "Button Clicked :" + item.getTitle(),
                                Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                popupMenu.show();

            }
        });
        */
    }

    public void zmienHaslo(View v){
        Intent intent = new Intent(v.getContext(), ZmianaHasla.class);
        startActivityForResult(intent, 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_profil, menu);

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

            case R.id.action_exit:

                AlertDialog.Builder alert2 = new AlertDialog.Builder(this);
                alert2.setTitle("Wyjście");

                alert2.setMessage("Czy na pewno chcesz Wyjść?");

                alert2.setPositiveButton("Tak",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       // TODO: Wygląda na niedokończone. Na pewno chcesz dodawać listener, a nie coś po prostu wykonać?
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
                intent = new Intent(Profil.this, Dodaj_wskazowke_1.class);
                startActivityForResult(intent,0);
                break;

            case R.id.ekran_Ranking:
                Intent intent2;
                intent2 = new Intent(Profil.this, Ranking.class);
                startActivityForResult(intent2,0);
                break;

            case R.id.ekran_Mapa:
                Intent intent3;
                intent3 = new Intent(Profil.this, Mapy.class);
                startActivityForResult(intent3,0);
                break;

            default:
                return false;
        }
        return true;
    }
}
