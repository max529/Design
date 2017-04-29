package ch.hevs.design;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.roughike.bottombar.BottomBar;

import java.util.ArrayList;
import java.util.List;

import ch.hevs.design.components.SerializeList;
import ch.hevs.design.data.Command;
import ch.hevs.design.data.Couleur;
import ch.hevs.design.data.DB.dbHelper;
import ch.hevs.design.data.Mouvement;
import ch.hevs.design.data.Vin;

public class HomeActivity extends AppCompatActivity {
    public static dbHelper db = null;
    public static List<Couleur> colors = new ArrayList<Couleur>();
    public BottomNavBar bottomNavBar = null;
    public SerializeList<Vin> vins = new SerializeList<Vin>();
    public List<Command> commands = new ArrayList<Command>();
    public List<Mouvement> mvts = new ArrayList<Mouvement>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // create the DB
        db = new dbHelper(HomeActivity.this);

        //Ajout de couleur
        colors.add(new Couleur(getString(R.string.red)));
        colors.add(new Couleur(getString(R.string.white)));
        colors.add(new Couleur(getString(R.string.pink)));

        // recupère les infos de la DB
        vins = db.getWines();
        commands = db.getCommands();
        mvts = db.getMovements();
        for(Object w : vins){
            Log.e("debugImg",((Vin)w).toStringInfo());
        }

        //creation de la nvaigation
        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomNavBar = new BottomNavBar(bottomBar);
        bottomNavBar.setActivity(HomeActivity.this);

        bottomNavBar.addPageViewer();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        Log.e("debug","save");
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putSerializable("listVins",vins);
    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        Log.e("debug","restore");
        super.onRestoreInstanceState(savedInstanceState);
        vins = (SerializeList<Vin>)savedInstanceState.getSerializable("listVins");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        bottomNavBar.setMenu(menu);
        getMenuInflater().inflate(R.menu.home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.navBtnAddWine:
                Intent intent = new Intent(this, AddWineActivity.class);
                this.startActivityForResult(intent,1);
                break;
            case R.id.navBtnOrderWine:
                Intent intent1 = new Intent(this, AddOrderActivity.class);
                this.startActivityForResult(intent1,2);
                break;
            case R.id.navBtnClearMvt:
                db.clearMovements();
                this.mvts = db.getMovements();
                bottomNavBar.updateFragment(2);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    // retour des activités enfants
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK){
            if(requestCode==1){
                vins = db.getWines();
                bottomNavBar.updateFragment(0);
            }else if(requestCode==2){
                commands = db.getCommands();
                bottomNavBar.updateFragment(1);
            }
        }
    }
}
