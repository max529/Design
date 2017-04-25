package ch.hevs.design;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import ch.hevs.design.components.SerializeList;
import ch.hevs.design.data.Vin;

import static ch.hevs.design.HomeActivity.db;

public class AddOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);
        this.setTitle(getString(R.string.add_order));

        SerializeList<Vin> vins = db.getAllWines();

        Spinner spinnerWine = (Spinner)findViewById(R.id.addOrderListWine);
        ArrayAdapter<Vin> adapterWine = new ArrayAdapter<Vin>(AddOrderActivity.this,
                android.R.layout.simple_spinner_item,vins);
        adapterWine.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerWine.setAdapter(adapterWine);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_order, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.navBtnValidateAddOrder){
            valideOrder();
        }else if(item.getItemId() == android.R.id.home){
            this.onBackPressed();
            return true;
        }
        return true;
    }

    private void valideOrder() {
        String temp = ((EditText)findViewById(R.id.addOrderQte)).getText().toString();
        int qte = Integer.parseInt(temp);
        Spinner spinnerWine = (Spinner)findViewById(R.id.addOrderListWine);
        Vin v = (Vin)spinnerWine.getSelectedItem();

        db.insertCommand(v.get_id(),qte,0);

        Intent i = new Intent();
        setResult(RESULT_OK,i);
        finish();
    }
}