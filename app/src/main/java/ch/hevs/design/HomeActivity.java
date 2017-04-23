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
import ch.hevs.design.data.Cepage;
import ch.hevs.design.data.Couleur;
import ch.hevs.design.data.Provider;
import ch.hevs.design.data.Region;
import ch.hevs.design.data.Vin;

public class HomeActivity extends AppCompatActivity {

    public BottomNavBar bottomNavBar = null;
    public SerializeList<Vin> vins = new SerializeList<Vin>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if (savedInstanceState == null) {
            Log.e("maxDB","createDb");
            generateVin();
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
        Log.e("maxDB","saveStat");
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
                Log.e("maxDeb","order wine");
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    // retour des activités enfants
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK){
            Object a = data.getSerializableExtra("res");
            if(requestCode==1){
                Log.e("debugMax","add vin");
                Vin v = (Vin)a;
                vins.add(v);
                bottomNavBar.updateFragment(0);
            }
        }
    }


    //a supprimer
    private void generateVin(){
        List<Cepage> cepages = new ArrayList<Cepage>();
        cepages.add(new Cepage("Gamay"));
        cepages.add(new Cepage("Pinot Noir"));
        cepages.add(new Cepage("Cornalin"));
        cepages.add(new Cepage("Chardonnay"));
        Vin v = new Vin("","vin1","description1",2008,new Couleur("Rouge"),new Region("Italie"),10,22.50,cepages,new Provider("Maxime","Bétrisey","Route du Plat de Chelon","max@max.com"));
        vins.add(v);
    }
}
