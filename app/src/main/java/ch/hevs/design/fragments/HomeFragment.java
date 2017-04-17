package ch.hevs.design.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ch.hevs.design.R;
import ch.hevs.design.adapter.WineAdapter;
import ch.hevs.design.data.Vin;

/**
 * Created by maxim on 11.04.2017.
 */

public class HomeFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.fragment_home, container, false);

        mListView = (ListView) rootView.findViewById(R.id.listViewWine);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,long arg3) {
                view.setSelected(true);
                Vin v = (Vin)mListView.getItemAtPosition(position);
                Log.e("maxDeb",v.getName());
            }
        });

        List<Vin> tweets = genererVins();


        WineAdapter adapter = new WineAdapter(rootView.getContext(), tweets);
        mListView.setAdapter(adapter);

        return rootView;
    }
    ListView mListView;

    private List<Vin> genererVins(){
        List<Vin> vins = new ArrayList<Vin>();
        for(int i=0;i<20;i++){
            vins.add(new Vin("","Vin"+i,1980+i));
        }
        return vins;
    }
}
