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
import android.widget.TableRow;

import pl.lodz.p.ftims.geocaching.R;


public class Dodaj_wskazowke_2 extends Activity {

	public boolean tak = false;

    /**
     * Powiązanie z layoutem
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_wskazowke_2);

    }

    /**
     * Przejście do poprzedniego layoutu
     * @param v
     */
    public void Cofnij_W2(View v){
        Button Wstecz = (Button) findViewById(R.id.Wstecz);
        Wstecz.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Dodaj_wskazowke_1.class);
                startActivityForResult(intent,0);
            }
        });
    }

    /**
     * Przejście do następnego layoutu jeśli użytkownik bedzie chciał
     * dodać wskazówke
     * @param v
     */
    public void Dodaj_wskazowke(View v){
        Button Dodaj_nowa = (Button) findViewById(R.id.Dodaj_wskazowke_text);
        Dodaj_nowa.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), Edycja_wskazowki.class);
            startActivityForResult(intent, 0);
            }
        });
    }

    /**
     * Możliwość edycji wskazówki po kliknieciu na nią w tabeli
     * @param v
     */
    public void Edytuj_wskazowke(View v){

        TableRow Edytuj = (TableRow) findViewById(v.getId());
        Edytuj.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Edycja_wskazowki.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    /**
     * Zatwierdzenie wskazówki, przeniesienie do layoutu profil
     * @param v
     */
    public void CzyChceszZatwierdzic(View v) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Zapisać?");

        alert.setMessage("Czy na pewno chcesz zatwierdzic?");

        alert.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tak = true;
            }
        });
        alert.setNegativeButton("Nie", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tak = false;
            }
        });

        alert.show();
        if (tak){
            Intent intent = new Intent(v.getContext(), Profil.class);
            startActivityForResult(intent,0);
        }

//        Button Zatw = (Button) findViewById(R.id.Zatwierd�_wyzwanie);
//        Zatw.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View v) {
//                Intent intent = new Intent(v.getContext(), Profil.class);
//                startActivityForResult(intent,0);
//            }
//        });
//
    }

    /**
     * dodanie rozwijanego menu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dodaj_wskazowke_2, menu);
        return true;
    }

    /**
     * tworzenie rozwijanego menu
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //menu górne
        super.onContextItemSelected(item);
        Intent intent;

        switch(item.getItemId()) {

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

//                Toast.makeText(Profil.this, "Button Clicked :" + item.getTitle(),
//                        Toast.LENGTH_SHORT).show();
//                intent = new Intent(Intent.ACTION_MAIN);
//                intent.addCategory(Intent.CATEGORY_HOME);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
                break;

            case R.id.ekran_Profil:
                intent = new Intent(Dodaj_wskazowke_2.this, pl.lodz.p.ftims.geocaching.GUI.Profil.class);
                startActivityForResult(intent,0);
                break;

            case R.id.ekran_Ranking:
                intent = new Intent(Dodaj_wskazowke_2.this, pl.lodz.p.ftims.geocaching.GUI.Ranking.class);
                startActivityForResult(intent,0);
                break;

            case R.id.ekran_Mapa:
                Intent intent3;
                intent3 = new Intent(Dodaj_wskazowke_2.this, pl.lodz.p.ftims.geocaching.GUI.Mapy.class);
                startActivityForResult(intent3,0);
                break;

            default:
                return false;
        }
        return true;
    }
}
