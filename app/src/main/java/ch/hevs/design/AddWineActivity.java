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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ch.hevs.design.components.MultiSpinner;
import ch.hevs.design.data.Cepage;
import ch.hevs.design.data.Couleur;
import ch.hevs.design.data.Provider;
import ch.hevs.design.data.Region;
import ch.hevs.design.data.Vin;

import static ch.hevs.design.HomeActivity.colors;
import static ch.hevs.design.HomeActivity.db;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class AddWineActivity extends AppCompatActivity implements MultiSpinner.MultiSpinnerListener {
    private List<Region> regions = new ArrayList<Region>();
    private List<Cepage> cepages = new ArrayList<Cepage>();
    private List<Provider> providers = new ArrayList<Provider>();

    private ArrayAdapter<Region> adapterRegion = null;
    private ArrayAdapter<Couleur> adapterColor = null;
    private ArrayAdapter<Provider> adapterFournisseur = null;


    private Menu menu;
    private Vin vinToEdit = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_wine);
        this.setTitle(R.string.add_wine);

        // set data for color
        updateListColor();

        //set data for region
        updateListRegion();

        //set cépage
        updateListCepage();

        //set fournisseur
        updateListFournisseur();

        Intent i = getIntent();
        if(i.getSerializableExtra("Vin") instanceof Vin){
            this.setTitle(R.string.edit_wine);
            Vin v = (Vin)i.getSerializableExtra("Vin");
            vinToEdit = v;
            ((TextView)findViewById(R.id.addNameWine)).setText(v.getName());
            ((TextView)findViewById(R.id.addDescrWine)).setText(v.getDescription());
            ((TextView)findViewById(R.id.addYearWine)).setText(v.getAnnee()+"");

            Spinner sColorWine = ((Spinner)findViewById(R.id.addColorWine));
            sColorWine.setSelection(adapterColor.getPosition(v.getCouleur()));

            Spinner sRegionWine = ((Spinner)findViewById(R.id.addRegionWine));
            sRegionWine.setSelection(adapterRegion.getPosition(v.getRegion()));
            Log.e("RegionTo",v.getRegion().toString());
            for(Region r : regions){
                Log.e("Region dispo",r.toString());
            }

            ((TextView)findViewById(R.id.addQuantityWine)).setText(v.getQte()+"");
            ((TextView)findViewById(R.id.addPriceWine)).setText(v.getPrix()+"");

            MultiSpinner multiSpinner = ((MultiSpinner)findViewById(R.id.addCepageWine));
            multiSpinner.setItemsTrue(v.getCepage());

            Spinner sFournisseurWine = ((Spinner)findViewById(R.id.addFournisseurWine));
            sFournisseurWine.setSelection(adapterFournisseur.getPosition(v.getProvider()));

        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_wine, menu);
        this.menu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.navBtnAddRegion:
                intent = new Intent(this, AddPlaceActivity.class);
                this.startActivityForResult(intent,1);
                break;
            case R.id.navBtnAddCepage:
                intent = new Intent(this, AddCepageActivity.class);
                this.startActivityForResult(intent,2);
                break;
            case R.id.navBtnAddProvider:
                intent = new Intent(this, AddProviderActivity.class);
                this.startActivityForResult(intent,3);
                break;
            case R.id.navBtnValidateAddWine:
                validateWine();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    // retour des activités enfants
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK){
            Object a = data.getSerializableExtra("res");
            if(requestCode==1){
                updateListRegion();
            }else if(requestCode == 2){
                updateListCepage();
            }else if(requestCode == 3){
                updateListFournisseur();
            }
        }
    }

    private void validateWine(){
        Vin v = null;
        if(vinToEdit == null) {
            String name = ((EditText) findViewById(R.id.addNameWine)).getText().toString();
            String description = ((EditText) findViewById(R.id.addDescrWine)).getText().toString();
            int year = parseInt(((EditText) findViewById(R.id.addYearWine)).getText().toString());
            Spinner spinnerColor = (Spinner) findViewById(R.id.addColorWine);
            Couleur color = (Couleur) spinnerColor.getSelectedItem();
            Spinner spinnerRegion = (Spinner) findViewById(R.id.addRegionWine);
            Region region = (Region) spinnerRegion.getSelectedItem();
            int qte = parseInt(((EditText) findViewById(R.id.addQuantityWine)).getText().toString());
            double price = parseDouble(((EditText) findViewById(R.id.addPriceWine)).getText().toString());
            MultiSpinner cepage = (MultiSpinner) findViewById(R.id.addCepageWine);
            List<Object> cepObj = cepage.getSelectedItems();
            List<Cepage> ceps = new ArrayList<Cepage>();
            for (Object o : cepObj) {
                Cepage cTemp = (Cepage) o;
                ceps.add(cTemp);
            }
            Spinner spinnerFournisseur = (Spinner) findViewById(R.id.addFournisseurWine);
            Provider provider = (Provider) spinnerFournisseur.getSelectedItem();

            int colorID = colors.indexOf(color);
            db.insertWine(name,description,year,colorID,region.get_id(),price,qte,provider.get_id(),ceps);

        }else{

        }
        Intent i = new Intent();
        setResult(RESULT_OK,i);
        finish();
    }

    // item select pour multiple
    public void onItemsSelected(boolean[] selected) {

    }


    // mise à jour des list
    private void updateListCepage(){
        cepages = db.getCepages();
        MultiSpinner multiSpinner = (MultiSpinner) findViewById(R.id.addCepageWine);
        multiSpinner.setItems(cepages,getString(R.string.cepage_choice),AddWineActivity.this,true);
    }
    private void updateListRegion(){
        regions = db.getRegions();
        Spinner spinnerRegion = (Spinner)findViewById(R.id.addRegionWine);
        adapterRegion = new ArrayAdapter<Region>(AddWineActivity.this,
                android.R.layout.simple_spinner_item,regions);
        adapterRegion.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRegion.setAdapter(adapterRegion);
    }
    private void updateListColor(){


        Spinner spinnerColor = (Spinner)findViewById(R.id.addColorWine);
        adapterColor = new ArrayAdapter<Couleur>(AddWineActivity.this,
                android.R.layout.simple_spinner_item,colors);
        adapterColor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerColor.setAdapter(adapterColor);
    }
    private void updateListFournisseur(){
        providers = db.getProviders();
        Spinner spinnerFournisseur = (Spinner)findViewById(R.id.addFournisseurWine);
        adapterFournisseur = new ArrayAdapter<Provider>(AddWineActivity.this,
                android.R.layout.simple_spinner_item,providers);
        adapterFournisseur.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFournisseur.setAdapter(adapterFournisseur);
    }
}
