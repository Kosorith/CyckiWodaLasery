package pl.lodz.p.ftims.geocaching.GUI;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import pl.lodz.p.ftims.geocaching.R;
import pl.lodz.p.ftims.geocaching.logic.challenges.ChallengesService;
import pl.lodz.p.ftims.geocaching.logic.inject.InjectPlz;
import pl.lodz.p.ftims.geocaching.model.RankingEntry;

import java.util.Arrays;
import java.util.List;


public class Ranking extends Activity {

    @InjectPlz
    private ChallengesService challengesService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        List<RankingEntry> rankingList =  challengesService.getRanking();

        TableLayout rankingTable = (TableLayout) findViewById(R.id.profileTableLayout);
        for (RankingEntry entry : rankingList) {
            TableRow newRow = (TableRow) LayoutInflater.from(Ranking.this).inflate(R.layout.ranking_row, null);
            ((TextView) newRow.findViewById(R.id.rankingRowPozycja)).setText(String.valueOf(entry.getPosition()));
            ((TextView) newRow.findViewById(R.id.rankingRowNick)).setText(entry.getNick());
            ((TextView) newRow.findViewById(R.id.rankingRowPunkty)).setText(String.valueOf(entry.getPoints()));
            ((TextView) newRow.findViewById(R.id.rankingRowUkonczono)).setText(String.valueOf(entry.getCompletedChallenges()));
            rankingTable.addView(newRow);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ranking, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //menu górne
        super.onContextItemSelected(item);
        //Intent intent;

        switch(item.getItemId()) {


//                Toast.makeText(Profil.this, "Button Clicked :" + item.getTitle(),
//                        Toast.LENGTH_SHORT).show();
//                intent = new Intent(Intent.ACTION_MAIN);
//                intent.addCategory(Intent.CATEGORY_HOME);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);

            case R.id.ekran_Stworz_wyzwanie:
                Intent intent;
                intent = new Intent(Ranking.this, Dodaj_wskazowke_1.class);
                startActivityForResult(intent,0);
                break;

            case R.id.ekran_Profil:
                Intent intent2;
                intent2 = new Intent(Ranking.this, Profil.class);
                startActivityForResult(intent2,0);
                break;

            case R.id.ekran_Mapa:
                Intent intent3;
                intent3 = new Intent(Ranking.this, Mapy.class);
                startActivityForResult(intent3,0);
                break;

            default:
                return false;
        }
        return true;
    }
}
