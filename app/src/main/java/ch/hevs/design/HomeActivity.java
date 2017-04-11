package ch.hevs.design;

import android.app.Application;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

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
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
