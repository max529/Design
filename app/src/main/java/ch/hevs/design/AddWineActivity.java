package ch.hevs.design;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
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
    private String imgWinePath = "";
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

            imgWinePath = v.getImg();
            Bitmap bitmap=null;
            File f= new File(imgWinePath);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            try {
                bitmap = BitmapFactory.decodeStream(new FileInputStream(f), null, options);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            ImageView my_img_view = (ImageView ) findViewById (R.id.addShowWineImg);
            my_img_view.setImageBitmap(bitmap);

        }

        Button addImg = (Button)findViewById(R.id.addWineImg);
        addImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 4);
            }
        });

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
            //Object a = data.getSerializableExtra("res");
            if(requestCode==1){
                updateListRegion();
            }else if(requestCode == 2){
                updateListCepage();
            }else if(requestCode == 3){
                updateListFournisseur();
            }else if(requestCode == 4){
                setImgWine(data);
            }
        }
    }

    private void setImgWine(Intent data){
        Uri selectedImage = data.getData();
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),selectedImage);
            SecureRandom random = new SecureRandom();
            String uniquePath = new BigInteger(130, random).toString(32)+".png";
            String newPath = getFilesDir().getPath()+"/"+uniquePath;

            FileOutputStream out = new FileOutputStream(newPath);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.close();
            Log.e("debug",newPath);
            imgWinePath = newPath;
            ImageView my_img_view = (ImageView ) findViewById (R.id.addShowWineImg);
            my_img_view.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }


        /*File f =new File(data.getData()).getAbsolutePath();
        Log.e("debug",f.exists()+"");*/

    }

    private void validateWine(){
        Log.e("createDB",imgWinePath);
        if(vinToEdit == null) {
            String name = ((EditText) findViewById(R.id.addNameWine)).getText().toString();
            if(name.length()==0){
                Toast.makeText(AddWineActivity.this,"Please add a wine name",Toast.LENGTH_SHORT).show();
                return;
            }


            String description = ((EditText) findViewById(R.id.addDescrWine)).getText().toString();
            if(description.length()==0){
                Toast.makeText(AddWineActivity.this,"Please add a wine description",Toast.LENGTH_SHORT).show();
                return;
            }

            String tempYear = ((EditText) findViewById(R.id.addYearWine)).getText().toString();
            if(tempYear.length()==0){
                Toast.makeText(AddWineActivity.this,"Please add a wine year",Toast.LENGTH_SHORT).show();
                return;
            }

            int year = parseInt(tempYear);
            Spinner spinnerColor = (Spinner) findViewById(R.id.addColorWine);
            Couleur color = (Couleur) spinnerColor.getSelectedItem();
            Spinner spinnerRegion = (Spinner) findViewById(R.id.addRegionWine);
            Region region = (Region) spinnerRegion.getSelectedItem();

            String tempQte = ((EditText) findViewById(R.id.addQuantityWine)).getText().toString();
            if(tempQte.length()==0){
                Toast.makeText(AddWineActivity.this,"Please add a wine quantity",Toast.LENGTH_SHORT).show();
                return;
            }

            int qte = parseInt(tempQte);

            String tempPrice = ((EditText) findViewById(R.id.addPriceWine)).getText().toString();
            if(tempPrice.length()==0){
                Toast.makeText(AddWineActivity.this,"Please add a wine price",Toast.LENGTH_SHORT).show();
                return;
            }
            double price = parseDouble(tempPrice);

            MultiSpinner cepage = (MultiSpinner) findViewById(R.id.addCepageWine);
            List<Object> cepObj = cepage.getSelectedItems();
            if(cepObj.size()==0){
                Toast.makeText(AddWineActivity.this,"Please select at least one cepage",Toast.LENGTH_SHORT).show();
                return;
            }

            List<Cepage> ceps = new ArrayList<Cepage>();
            for (Object o : cepObj) {
                Cepage cTemp = (Cepage) o;
                ceps.add(cTemp);
            }
            Spinner spinnerFournisseur = (Spinner) findViewById(R.id.addFournisseurWine);
            Provider provider = (Provider) spinnerFournisseur.getSelectedItem();

            int colorID = colors.indexOf(color);
            db.insertWine(imgWinePath,name,description,year,colorID,region.get_id(),price,qte,provider.get_id(),ceps);

        }else{
            String name = ((EditText) findViewById(R.id.addNameWine)).getText().toString();
            if(name.length()==0){
                Toast.makeText(AddWineActivity.this,"Please add a wine name",Toast.LENGTH_SHORT).show();
                return;
            }


            String description = ((EditText) findViewById(R.id.addDescrWine)).getText().toString();
            if(description.length()==0){
                Toast.makeText(AddWineActivity.this,"Please add a wine description",Toast.LENGTH_SHORT).show();
                return;
            }

            String tempYear = ((EditText) findViewById(R.id.addYearWine)).getText().toString();
            if(tempYear.length()==0){
                Toast.makeText(AddWineActivity.this,"Please add a wine year",Toast.LENGTH_SHORT).show();
                return;
            }

            int year = parseInt(tempYear);
            Spinner spinnerColor = (Spinner) findViewById(R.id.addColorWine);
            Couleur color = (Couleur) spinnerColor.getSelectedItem();
            Spinner spinnerRegion = (Spinner) findViewById(R.id.addRegionWine);
            Region region = (Region) spinnerRegion.getSelectedItem();

            String tempQte = ((EditText) findViewById(R.id.addQuantityWine)).getText().toString();
            if(tempQte.length()==0){
                Toast.makeText(AddWineActivity.this,"Please add a wine quantity",Toast.LENGTH_SHORT).show();
                return;
            }

            int qte = parseInt(tempQte);

            String tempPrice = ((EditText) findViewById(R.id.addPriceWine)).getText().toString();
            if(tempPrice.length()==0){
                Toast.makeText(AddWineActivity.this,"Please add a wine price",Toast.LENGTH_SHORT).show();
                return;
            }
            double price = parseDouble(tempPrice);

            MultiSpinner cepage = (MultiSpinner) findViewById(R.id.addCepageWine);
            List<Object> cepObj = cepage.getSelectedItems();
            if(cepObj.size()==0){
                Toast.makeText(AddWineActivity.this,"Please select at least one cepage",Toast.LENGTH_SHORT).show();
                return;
            }

            List<Cepage> ceps = new ArrayList<Cepage>();
            for (Object o : cepObj) {
                Cepage cTemp = (Cepage) o;
                ceps.add(cTemp);
            }
            Spinner spinnerFournisseur = (Spinner) findViewById(R.id.addFournisseurWine);
            Provider provider = (Provider) spinnerFournisseur.getSelectedItem();

            int colorID = colors.indexOf(color);
            db.updateWine(vinToEdit.get_id(),imgWinePath,name,description,year,colorID,region.get_id(),price,qte,provider.get_id(),ceps);
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
