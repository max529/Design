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

import java.util.ArrayList;
import java.util.List;

import ch.hevs.design.components.MultiSpinner;
import ch.hevs.design.data.Cepage;
import ch.hevs.design.data.Couleur;
import ch.hevs.design.data.Pays;
import ch.hevs.design.data.Provider;
import ch.hevs.design.data.Region;
import ch.hevs.design.data.Vin;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class AddWineActivity extends AppCompatActivity implements MultiSpinner.MultiSpinnerListener {
    private List<Couleur> colors = new ArrayList<Couleur>();
    private List<Region> regions = new ArrayList<Region>();
    private List<Cepage> cepages = new ArrayList<Cepage>();
    private List<Provider> providers = new ArrayList<Provider>();



    private Menu menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_wine);
        this.setTitle(R.string.add_wine);

        generateData();

        // set data for color
        updateListColor();

        //set data for region
        updateListRegion();

        //set cépage
        updateListCepage();

        //set fournisseur
        updateListFournisseur();

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
        Log.e("maxDeb","Je suis de");
        if(resultCode==RESULT_OK){
            Object a = data.getSerializableExtra("res");
            if(requestCode==2){
                Cepage c = (Cepage)a;
                cepages.add(c);
                updateListCepage();
            }else if(requestCode == 1){
                Region r = (Region)a;
                regions.add(r);
                updateListRegion();
            }
        }
    }

    private void validateWine(){
        String name = ((EditText)findViewById(R.id.addNameWine)).getText().toString();
        String description = ((EditText)findViewById(R.id.addDescrWine)).getText().toString();
        int year = parseInt(((EditText)findViewById(R.id.addYearWine)).getText().toString());
        Spinner spinnerColor = (Spinner)findViewById(R.id.addColorWine);
        Couleur color = (Couleur)spinnerColor.getSelectedItem();
        Spinner spinnerRegion = (Spinner)findViewById(R.id.addRegionWine);
        Region region = (Region)spinnerRegion.getSelectedItem();
        int qte = parseInt(((EditText)findViewById(R.id.addQuantityWine)).getText().toString());
        double price = parseDouble(((EditText)findViewById(R.id.addPriceWine)).getText().toString());
        MultiSpinner cepage = (MultiSpinner) findViewById(R.id.addCepageWine);
        List<Object> cepObj = cepage.getSelectedItems();
        List<Cepage> ceps = new ArrayList<Cepage>();
        for (Object o:cepObj) {
            Cepage cTemp = (Cepage)o;
            ceps.add(cTemp);
        }
        Spinner spinnerFournisseur = (Spinner)findViewById(R.id.addFournisseurWine);
        Provider provider = (Provider)spinnerFournisseur.getSelectedItem();

        //implentation des controls


        Vin v = new Vin("",name,description,year,color,region,qte,price,ceps,provider);


        Log.e("ImplementDB","Implementation de l'ajout de vin");
        Intent i = new Intent();
        i.putExtra("res",v);
        setResult(RESULT_OK,i);
        finish();
    }

    // item select pour multiple
    public void onItemsSelected(boolean[] selected) {

    }


    // mise à jour des list
    private void updateListCepage(){
        MultiSpinner multiSpinner = (MultiSpinner) findViewById(R.id.addCepageWine);
        multiSpinner.setItems(cepages,"coucou",AddWineActivity.this,true);
    }
    private void updateListRegion(){
        Spinner spinnerRegion = (Spinner)findViewById(R.id.addRegionWine);
        ArrayAdapter<Region> adapterRegion = new ArrayAdapter<Region>(AddWineActivity.this,
                android.R.layout.simple_spinner_item,regions);
        adapterRegion.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRegion.setAdapter(adapterRegion);
    }
    private void updateListColor(){
        Spinner spinnerColor = (Spinner)findViewById(R.id.addColorWine);
        ArrayAdapter<Couleur> adapterColor = new ArrayAdapter<Couleur>(AddWineActivity.this,
                android.R.layout.simple_spinner_item,colors);
        adapterColor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerColor.setAdapter(adapterColor);
    }
    private void updateListFournisseur(){
        Spinner spinnerFournisseur = (Spinner)findViewById(R.id.addFournisseurWine);
        ArrayAdapter<Provider> adapterFournisseur = new ArrayAdapter<Provider>(AddWineActivity.this,
                android.R.layout.simple_spinner_item,providers);
        adapterFournisseur.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFournisseur.setAdapter(adapterFournisseur);
    }

    //toDelete
    private void generateData(){
        cepages.add(new Cepage("Gamay"));
        cepages.add(new Cepage("Pinot Noir"));
        cepages.add(new Cepage("Cornalin"));
        cepages.add(new Cepage("Chardonnay"));

        regions.add(new Region("aléatoire"));
        regions.add(new Region("Valais", new Pays("Suisse","CH")));
        regions.add(new Region("Bordeaux",new Pays("France","FR")));

        colors.add(new Couleur("Rouge"));
        colors.add(new Couleur("Blanc"));
        colors.add(new Couleur("Rosé"));

        providers.add(new Provider("Bétrisey","Maxime","Route du plat","max@hevs.com"));
        providers.add(new Provider("Rebelo","Hugo","Route de la montagne","hugo@hevs.com"));
        providers.add(new Provider("Seligman","Robert","Route de la plaine","robert@hevs.com"));

    }
}
