package ch.hevs.design;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.roughike.bottombar.BottomBar;

public class HomeActivity extends AppCompatActivity {

    BottomNavBar bottomNavBar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);




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
                this.startActivity(intent);
                break;
            case R.id.navBtnOrderWine:
                Log.e("maxDeb","order wine");
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }
}
