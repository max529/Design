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

import ch.hevs.design.data.Vin;

public class HomeActivity extends AppCompatActivity {

    BottomNavBar bottomNavBar = null;
    public List<Vin> vins = new ArrayList<Vin>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        generateVin();

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomNavBar = new BottomNavBar(bottomBar);
        bottomNavBar.setActivity(HomeActivity.this);

        bottomNavBar.addPageViewer();
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
                Vin v = (Vin)a;
                vins.add(v);
            }
        }
    }


    //a supprimer
    private void generateVin(){
        for(int i = 0;i<25;i++){
            Vin v = new Vin("","Cornalin",2008);
            vins.add(v);
        }



    }
}
