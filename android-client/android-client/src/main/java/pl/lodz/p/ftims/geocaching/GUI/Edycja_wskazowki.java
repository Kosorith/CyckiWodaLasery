package pl.lodz.p.ftims.geocaching.GUI;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.io.File;
import java.io.IOException;

import pl.lodz.p.ftims.geocaching.R;


public class Edycja_wskazowki extends Activity {

    ImageView Widok;
    Bitmap bmp;
    private Uri imageUri;

    /**
     * Powiązanie z layoutem
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edycja_wskazowki);

    }

    /**
     * Dodawanie zdjęcia z kamery
     * @param v
     */
    public void ZdjecieKamera2(View v) {
        Widok = (ImageView) findViewById(R.id.Zdjecie2);
        ImageButton Przycisk = (ImageButton) findViewById(R.id.ZdjecieKamera);
        Przycisk.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                    Intent i = new Intent("android.media.action.IMAGE_CAPTURE");
                    File photo = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "zdjecie.jpg");
                    imageUri = Uri.fromFile(photo);
                    i.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(i,0);
            }
        });
    }

    /**
     * dodawanie zdjęcia z pliku
     * @param v
     */
    public void ZdjeciePamiec2(View v){

        Widok = (ImageView) findViewById(R.id.Zdjecie2);
        ImageButton Przycisk2 = (ImageButton) findViewById(R.id.ZdjeciePamiec);
        Przycisk2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Wybierz Zdjecie"), 1);
            }
        });
    }

    /**
     * Wyświetlanie zdjęcia w layoucie
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode == RESULT_OK){
            if(requestCode == 0){
                Uri selected = imageUri;
                getContentResolver().notifyChange(selected, null );

                ContentResolver cr = getContentResolver();

                try {
                    bmp = MediaStore.Images.Media.getBitmap(cr, selected);
                    Widok.setImageBitmap(bmp);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if(requestCode == 1){
                Widok.setImageURI(data.getData());
            }
        }
    }

    /**
     * przejscie do layoutu koordynaty_mapa
     * @param v
     */
    public void Mapa_kordy(View v){
        ImageButton Wstecz = (ImageButton) findViewById(R.id.koordynaty);
        Wstecz.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Koordynaty_mapa.class);
                startActivityForResult(intent,0);
            }
        });
    }

    /**
     * Powrót do layoutu dodaj_wskazówke_2
     * @param v
     */
    public void Cofnij_D3(View v){
        Button Cofnij = (Button) findViewById(R.id.Cofnij);
        Cofnij.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),Dodaj_wskazowke_2.class);
                startActivityForResult(intent,0);
            }
        });
    }

    /**
     * Zatwierdzanie wskazówki
     * @param v
     */
    public void Zatwierdź_D3(View v){
        Button Zatwierdź = (Button) findViewById(R.id.Dodaj);
        Zatwierdź.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),Dodaj_wskazowke_2.class);
                startActivityForResult(intent,0);
            }
        });
    }

    /**
     * dodawanie menu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edycja_wskazowki, menu);
        return true;
    }

//    public void Koordynaty_XY (View v){
//        final ToggleButton Czy_Prywatne = (ToggleButton) findViewById(R.id.XY);
//        Czy_Prywatne.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v){
//                LinearLayout kordy = (LinearLayout) findViewById(R.id.Kordy);
//
//                if(Czy_Prywatne.isChecked()==true){
//                    kordy.setVisibility(kordy.VISIBLE);
//                }
//                else
//                {
//                    kordy.setVisibility(kordy.INVISIBLE);
//                }
//            }
//        });
//    }

    /**
     * Dodawanie lokalizacji z GPS
     * @param v
     */
    public void GPS (View v){
        ImageButton Czy_Prywatne = (ImageButton) findViewById(R.id.GPS);
//        final ToggleButton Czy_Prywatne = (ToggleButton) findViewById(R.id.GPS);
        Czy_Prywatne.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                LinearLayout kordy = (LinearLayout) findViewById(R.id.Kordy);


            }
        });
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


//                Toast.makeText(Profil.this, "Button Clicked :" + item.getTitle(),
//                        Toast.LENGTH_SHORT).show();
//                intent = new Intent(Intent.ACTION_MAIN);
//                intent.addCategory(Intent.CATEGORY_HOME);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);


            case R.id.ekran_Profil:
                intent = new Intent(Edycja_wskazowki.this, Profil.class);
                startActivityForResult(intent,0);
                break;

            case R.id.ekran_Ranking:
                intent = new Intent(Edycja_wskazowki.this, Ranking.class);
                startActivityForResult(intent,0);
                break;

            case R.id.ekran_Mapa:
                Intent intent3;
                intent3 = new Intent(Edycja_wskazowki.this, Mapy.class);
                startActivityForResult(intent3,0);
                break;

            default:
                return false;
        }
        return true;
    }
}
