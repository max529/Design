package ch.hevs.design;

import android.app.Application;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_vin) {
                    Intent i = new Intent(HomeActivity.this,HomeActivity.class);
                    startActivity(i);
                }else if(tabId == R.id.tab_commande){
                    Intent i = new Intent(HomeActivity.this,OrderActivity.class);
                    startActivity(i);
                }else if(tabId == R.id.tab_mouvement){
                    HomeActivity.this.setTitle(R.string.navMouvement);
                }else if(tabId == R.id.tab_settings){
                    HomeActivity.this.setTitle(R.string.navParam);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
