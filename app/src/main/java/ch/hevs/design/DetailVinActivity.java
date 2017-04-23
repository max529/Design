package ch.hevs.design;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import ch.hevs.design.data.Vin;

public class DetailVinActivity extends AppCompatActivity {
    private Vin v = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_vin);


        Intent i = getIntent();
        v = (Vin)i.getSerializableExtra("Vin");

        String title = v.getName();
        if(v.getProvider()!=null){
            title = v.getName()+" ("+v.getProvider().toString()+")";
        }
        this.setTitle(title);
        ((TextView)findViewById(R.id.descrAnneeVin)).setText(v.getAnnee()+"");
        ((TextView)findViewById(R.id.descrInfoVin)).setText(v.getDescription());
        ((ImageView)findViewById(R.id.descrImgVin)).setImageResource(R.drawable.wine_default);
        /*if(!v.getImg().equals("")){
            Bitmap bMap = BitmapFactory.decodeFile(v.getImg());
            ((ImageView)findViewById(R.id.descrImgVin)).setImageBitmap(bMap);
        }*/
        ((TextView)findViewById(R.id.descrPceVin)).setText(getString(R.string.stock_restant_pce,v.getQte()));
        ((TextView)findViewById(R.id.descrPlaceVin)).setText(getString(R.string.originaire_de,v.getRegion().toString()));
        ((TextView)findViewById(R.id.descrCeapgeVin)).setText(getString(R.string.compos_de,v.getCepageString()));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.info_wine, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.navBtnEditWine:
                intent = new Intent(this, AddWineActivity.class);
                intent.putExtra("Vin",v);
                this.startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }
}
