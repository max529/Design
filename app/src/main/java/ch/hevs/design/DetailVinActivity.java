package ch.hevs.design;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import ch.hevs.design.data.Vin;

public class DetailVinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_vin);


        Intent i = getIntent();
        Vin v = (Vin)i.getSerializableExtra("Vin");

        String title = v.getName();
        if(v.getProvider()!=null){
            title = v.getName()+" ("+v.getProvider().toString()+")";
        }
        this.setTitle(title);
        ((TextView)findViewById(R.id.descrAnneeVin)).setText(v.getAnnee()+"");
        ((TextView)findViewById(R.id.descrInfoVin)).setText(v.getDescription());
        ((ImageView)findViewById(R.id.descrImgVin)).setImageResource(R.drawable.wine_default);
        if(!v.getImg().equals("")){
            Bitmap bMap = BitmapFactory.decodeFile(v.getImg());
            ((ImageView)findViewById(R.id.descrImgVin)).setImageBitmap(bMap);
        }
        ((TextView)findViewById(R.id.descrPceVin)).setText(getString(R.string.stock_restant_pce,v.getQte()));
        ((TextView)findViewById(R.id.descrPlaceVin)).setText(getString(R.string.originaire_de,v.getRegion().toString()));
        ((TextView)findViewById(R.id.descrCeapgeVin)).setText(getString(R.string.compos_de,v.getCepageString()));

    }
}
