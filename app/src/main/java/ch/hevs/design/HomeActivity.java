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
import ch.hevs.design.data.Couleur;
import ch.hevs.design.data.DB.dbHelper;
import ch.hevs.design.data.Vin;

public class HomeActivity extends AppCompatActivity {
    public static dbHelper db = null;
    public static List<Couleur> colors = new ArrayList<Couleur>();
    public BottomNavBar bottomNavBar = null;
    public SerializeList<Vin> vins = new SerializeList<Vin>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        db = new dbHelper(HomeActivity.this);

        colors.add(new Couleur(getString(R.string.red)));
        colors.add(new Couleur(getString(R.string.white)));
        colors.add(new Couleur(getString(R.string.pink)));

        if (savedInstanceState == null) {
            vins = db.getWines();
        }else{
            vins = (SerializeList<Vin>)savedInstanceState.getSerializable("listVins");
        }

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomNavBar = new BottomNavBar(bottomBar);
        bottomNavBar.setActivity(HomeActivity.this);

        bottomNavBar.addPageViewer();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putSerializable("listVins",vins);
    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
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
            }
        }
    }
}
