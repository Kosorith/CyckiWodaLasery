package pl.lodz.p.ftims.geocaching.GUI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import pl.lodz.p.ftims.geocaching.R;


public class Logowanie extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logowanie);

        Button OK = (Button) findViewById(R.id.OK);
        OK.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),Profil.class);
                startActivityForResult(intent,0);
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
