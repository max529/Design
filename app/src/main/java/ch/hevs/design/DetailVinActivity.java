package ch.hevs.design;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import ch.hevs.design.data.Vin;

import static ch.hevs.design.HomeActivity.db;

public class DetailVinActivity extends AppCompatActivity {
    private Vin v = null;
    private int getNumberOut = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_vin);


        Intent i = getIntent();
        v = (Vin)i.getSerializableExtra("Vin");
        Log.e("debug",v.toStringInfo());
        updateInfo();

        Button sortir = (Button)findViewById(R.id.buttonOutWine);
        sortir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(DetailVinActivity.this);

                dialog.setContentView(R.layout.dialog_wine_away);
                dialog.setTitle("Custom Alert Dialog");

                final EditText numberout = (EditText) dialog.findViewById(R.id.numbergetout);
                Button btnSave          = (Button) dialog.findViewById(R.id.save);
                Button btnCancel        = (Button) dialog.findViewById(R.id.cancel);
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getNumberOut = Integer.parseInt(numberout.getText().toString());
                        giveWineOut();
                        dialog.cancel();
                    }
                });
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                dialog.show();
            }
        });


    }

    public void giveWineOut(){
        if(getNumberOut>v.getQte()){
            getNumberOut = v.getQte();
        }
        db.getOutWine(getNumberOut,v.get_id());
        db.insertMovement(v.get_id(),0,getNumberOut);
        getNumberOut = 0;
        v = db.getWine(v.get_id());
        updateInfo();
        Toast.makeText(DetailVinActivity.this,"vin sorti",Toast.LENGTH_SHORT).show();
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
                this.startActivityForResult(intent,1);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK){
            if(requestCode==1){
                v = db.getWine(v.get_id());
                updateInfo();
            }
        }
    }

    public void updateInfo(){
        String title = v.getName();
        if(v.getProvider()!=null){
            title = v.getName()+" ("+v.getProvider().toString()+")";
        }
        this.setTitle(title);
        ((TextView)findViewById(R.id.descrAnneeVin)).setText(v.getAnnee()+"");
        ((TextView)findViewById(R.id.descrInfoVin)).setText(v.getDescription());
        ((ImageView)findViewById(R.id.descrImgVin)).setImageResource(R.drawable.wine_default);
        if(v.getImg()!=null && !v.getImg().equals("")){
            Bitmap bMap = BitmapFactory.decodeFile(v.getImg());
            ((ImageView)findViewById(R.id.descrImgVin)).setImageBitmap(bMap);
        }
        ((TextView)findViewById(R.id.descrPceVin)).setText(getString(R.string.stock_restant_pce,v.getQte()));
        ((TextView)findViewById(R.id.descrPlaceVin)).setText(getString(R.string.originaire_de,v.getRegion().toString()));
        ((TextView)findViewById(R.id.descrCeapgeVin)).setText(getString(R.string.compos_de,v.getCepageString()));
    }
}
