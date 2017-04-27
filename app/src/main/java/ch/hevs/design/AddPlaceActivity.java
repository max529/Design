package ch.hevs.design;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ch.hevs.design.data.Pays;
import ch.hevs.design.data.Region;

import static ch.hevs.design.HomeActivity.db;

public class AddPlaceActivity extends AppCompatActivity {
    private List<Pays> pays = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);
        this.setTitle(R.string.add_place);

        pays = db.getCountries();

        Spinner spinnerPays = (Spinner)findViewById(R.id.addCountryPlace);
        ArrayAdapter<Pays> adapterPays = new ArrayAdapter<Pays>(AddPlaceActivity.this,
                android.R.layout.simple_spinner_item,pays);
        adapterPays.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPays.setAdapter(adapterPays);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_place, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.navBtnValidateAddPlace){
            valideRegion();
        }else if(item.getItemId() == android.R.id.home){
            Log.e("finis","fi");
            this.onBackPressed();
            return true;
        }
        return true;
    }

    private void valideRegion(){
        String name = ((EditText)findViewById(R.id.addNamePlace)).getText().toString();
        Spinner spinnerPays = (Spinner)findViewById(R.id.addCountryPlace);
        Pays p = (Pays)spinnerPays.getSelectedItem();

        if(name.length()==0){
            Toast.makeText(AddPlaceActivity.this, R.string.error_place_name,Toast.LENGTH_SHORT).show();
            return;
        }

        db.insertRegion(p.get_id(),name);

        Intent i = new Intent();
        i.putExtra("res",new Region(name,p));
        setResult(RESULT_OK,i);
        finish();
    }
}
