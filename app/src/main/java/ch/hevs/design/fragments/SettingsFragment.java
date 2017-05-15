package ch.hevs.design.fragments;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Locale;

import ch.hevs.design.HomeActivity;
import ch.hevs.design.LoadingActivity;
import ch.hevs.design.R;
import ch.hevs.design.data.Langue;
import ch.hevs.design.interfaces.DefaultFragment;

/**
 * Created by maxim on 11.04.2017.
 */

public class SettingsFragment extends Fragment implements DefaultFragment {
    private HomeActivity activity;
    public void setActivity(HomeActivity activity){
        this.activity = activity;
    }
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.fragment_settings, container, false);

        ArrayList<Langue> langues = new ArrayList<>();
        langues.add(new Langue("French","fr",1));
        langues.add(new Langue("English","en",2));
        langues.add(new Langue("German","de",3));

        Spinner spinner = (Spinner)rootView.findViewById(R.id.spinnerChangeLanguage);
        ArrayAdapter<Langue> adapter = new ArrayAdapter<Langue>(activity,
                android.R.layout.simple_spinner_item,langues);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        setCurrentValue(spinner);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String l = Locale.getDefault().getLanguage();
                Langue langue = (Langue) parentView.getItemAtPosition(position);
                String languageToLoad  = langue.getInitial();

                if(!l.equals(languageToLoad)){
                    Locale locale = new Locale(languageToLoad);
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = (locale);
                    getResources().updateConfiguration(config,selectedItemView.getResources().getDisplayMetrics());

                    Intent intent = new Intent(activity, HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                return;
            }

        });


        Button synchro = (Button)rootView.findViewById(R.id.btnSync);
        synchro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Syncro();
            }
        });

        return rootView;
    }

    public void setCurrentValue(Spinner spinner){
        String l = Locale.getDefault().getLanguage();
        if(l.equals("fr")){
            spinner.setSelection(0);
        }else if(l.equals("en")){
            spinner.setSelection(1);
        }else if(l.equals("de")){
            spinner.setSelection(2);
        }else{
            spinner.setSelection(1);
        }
    }

    @Override
    public void updateList() {

    }

    public void Syncro(){
        /*List<Vin> vins = db.getAllWines();
        for (Vin v: vins) {
            new VinEndPoint(v).execute();
        }*/
        //<Cepage> cepages = db.getCepages();
        Intent i = new Intent(activity,LoadingActivity.class);
        i.putExtra("save","save");
        activity.startActivityForResult(i,666);
    }
}
