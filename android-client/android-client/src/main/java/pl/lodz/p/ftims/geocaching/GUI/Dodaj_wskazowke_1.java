package pl.lodz.p.ftims.geocaching.GUI;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import java.io.File;
import java.io.IOException;

import pl.lodz.p.ftims.geocaching.R;
import pl.lodz.p.ftims.geocaching.logic.challenges.ChallengeCreationService;
import pl.lodz.p.ftims.geocaching.logic.gps.LocationService;
import pl.lodz.p.ftims.geocaching.logic.inject.InjectPlz;
import pl.lodz.p.ftims.geocaching.model.Challenge;
import pl.lodz.p.ftims.geocaching.model.ChallengeStub;
import pl.lodz.p.ftims.geocaching.model.GeoCoords;

/**
 * Klasa odpowiadająca za dodawanie wskazówki (cz.1)
 */
public class Dodaj_wskazowke_1 extends Activity {

    @InjectPlz
    private LocationService locationService;
    @InjectPlz
    private ChallengeCreationService challengeCreationService;

    private ImageView Widok;
    private Bitmap bmp;
    private Uri imageUri;

    /**
     * Powiązanie z layoutem
     * Przejście do następnej część dodawania wskazówki
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_wskazowke_1);
        Widok = (ImageView) findViewById(R.id.Zdjecie);
    }

    public void przejdzDalej(View v) {
        Intent intent = new Intent(v.getContext(),Dodaj_wskazowke_2.class);
        startActivityForResult(intent,0);

        CheckBox publicBox = (CheckBox) findViewById(R.id.Rodzaj_wyzwania);
        EditText nameEdit = (EditText) findViewById(R.id.Nazwa);
        EditText passEdit = (EditText) findViewById(R.id.Hasło);
        EditText descEdit = (EditText) findViewById(R.id.Opis);

        boolean publicAccess = publicBox.isChecked();
        String name = nameEdit.getText().toString();
        String password = publicAccess ? null : passEdit.getText().toString();
        String description = descEdit.getText().toString();
        GeoCoords location = locationService.getCurrentLocation();

        challengeCreationService.initCreation();
        Challenge challenge = challengeCreationService.getEditedChallenge();
        challenge.setStub(new ChallengeStub(name, description, location, publicAccess));
        challenge.setPhoto(bmp);
        challenge.setPoints(123);
        // coś jest nie tak w sumie, ale i tak dao nie obsługuje tworzenia challenge
    }

    /**
     * metoda służąca do wczytywania zdjęcia wprost z aparatu
     * @param v
     */
    public void ZdjecieKamera(View v) {
        Intent i = new Intent("android.media.action.IMAGE_CAPTURE");
        File photo = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "zdjecie.jpg");
        imageUri = Uri.fromFile(photo);
        i.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(i,0);
    }

    /**
     * metoda służąca do dodawania zdjecia z pamieci telefonu
     * @param v
     */
    public void ZdjeciePamiec(View v){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Wybierz Zdjecie"), 1);
    }

    /**
     * Wyśeirtlanie zdjecia w odpowiednim miejscu w layoucie
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode == RESULT_OK){
            if (requestCode == 0){
                Uri selected = imageUri;
                ContentResolver cr = getContentResolver();
                cr.notifyChange(selected, null);

                try {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 4;

                    AssetFileDescriptor fileDescriptor;
                    fileDescriptor = cr.openAssetFileDescriptor(selected, "r");

                    bmp = BitmapFactory.decodeFileDescriptor(fileDescriptor.getFileDescriptor(), null, options);

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
     * metoda dotycząca wyboru rodzaju zagadki: publiczna czy prywatna
     * @param v
     */
    public void Czy_Pryw (View v){
        final CheckBox Czy_Prywatne = (CheckBox) findViewById(R.id.Rodzaj_wyzwania);
        TextView Haslo_text = (TextView) findViewById(R.id.Hasło_text);
        EditText Haslo = (EditText) findViewById(R.id.Hasło);
        if(Czy_Prywatne.isChecked()) {
            Haslo_text.setVisibility(TextView.VISIBLE);
            Haslo.setVisibility(EditText.VISIBLE);
        }
        else {
            Haslo_text.setVisibility(TextView.INVISIBLE);
            Haslo.setVisibility(EditText.INVISIBLE);
        }
    }

    /**
     * dodawanie rozwijanego menu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dodaj_wskazowke_1, menu);
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

                alert2.setMessage("Czy na pewno chcesz wyjść?");

                alert2.setPositiveButton("Tak",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
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
                intent = new Intent(Dodaj_wskazowke_1.this, Profil.class);
                startActivityForResult(intent,0);
                break;

            case R.id.ekran_Ranking:
                intent = new Intent(Dodaj_wskazowke_1.this, Ranking.class);
                startActivityForResult(intent,0);
                break;

            case R.id.ekran_Mapa:
                Intent intent3;
                intent3 = new Intent(Dodaj_wskazowke_1.this, Mapy.class);
                startActivityForResult(intent3,0);
                break;

            default:
                return false;
        }
        return true;
    }
}
