package ch.hevs.design;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        this.setTitle(R.string.navCommande);

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_vin) {
                    Intent i = new Intent(OrderActivity.this,HomeActivity.class);
                    startActivity(i);
                }else if(tabId == R.id.tab_commande){
                    Intent i = new Intent(OrderActivity.this,OrderActivity.class);
                    startActivity(i);
                }else if(tabId == R.id.tab_mouvement){
                    OrderActivity.this.setTitle(R.string.navMouvement);
                }else if(tabId == R.id.tab_settings){
                    OrderActivity.this.setTitle(R.string.navParam);
                }
            }
        });
    }
}
