package ch.hevs.design;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import ch.hevs.design.data.endPoint.CepageEndPoint;
import ch.hevs.design.data.endPoint.CommandEndPoint;
import ch.hevs.design.data.endPoint.CouleurEndPoint;
import ch.hevs.design.data.endPoint.MouvementEndPoint;
import ch.hevs.design.data.endPoint.PaysEndPoint;
import ch.hevs.design.data.endPoint.ProviderEndPoint;
import ch.hevs.design.data.endPoint.RegionEndPoint;
import ch.hevs.design.data.endPoint.VinEndPoint;

import static ch.hevs.design.HomeActivity.db;

public class LoadingActivity extends AppCompatActivity {
    public int cepageOk = 0;
    public int commandOk = 0;
    public int couleurOk = 0;
    public int mouvementOk = 0;
    public int paysOk = 0;
    public int providerOk = 0;
    public int regionOk = 0;
    public int vinOk = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        Intent i = getIntent();
        if(i.getStringExtra("save")!=null && i.getStringExtra("save").equals("save")){
            db.SqlToCloud(this);

        }

        new CepageEndPoint(this).execute();
        new CommandEndPoint(this).execute();
        new CouleurEndPoint(this).execute();
        new MouvementEndPoint(this).execute();
        new PaysEndPoint(this).execute();
        new ProviderEndPoint(this).execute();
        new RegionEndPoint(this).execute();
        new VinEndPoint(this).execute();
    }

    @Override
    public void onBackPressed() {
        return;
    }

    public void check(){
        Log.e("checking","try to chekc");
        Log.e("cepageOk",cepageOk+"");
        Log.e("commandOk",commandOk+"");
        Log.e("couleurOk",couleurOk+"");
        Log.e("mouvementOk",mouvementOk+"");
        Log.e("paysOk",paysOk+"");
        Log.e("providerOk",providerOk+"");
        Log.e("regionOk",regionOk+"");
        Log.e("vinOk",vinOk+"");
        if(
                cepageOk == 1 &&
                commandOk == 1 &&
                couleurOk == 1 &&
                mouvementOk == 1 &&
                paysOk == 1 &&
                providerOk == 1 &&
                regionOk == 1 &&
                vinOk == 1
        ){
            Intent i = new Intent();
            setResult(RESULT_OK,i);
            finish();
        }
    }
}
