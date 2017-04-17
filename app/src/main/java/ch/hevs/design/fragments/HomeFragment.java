package ch.hevs.design.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        List<Vin> tweets = genererVins();

        WineAdapter adapter = new WineAdapter(rootView.getContext(), tweets);
        mListView.setAdapter(adapter);

        return rootView;
    }
    ListView mListView;

    private List<Vin> genererVins(){
        List<Vin> vins = new ArrayList<Vin>();
        vins.add(new Vin("","Vin1",2000));
        vins.add(new Vin("","Vin1",2000));
        vins.add(new Vin("","Vin1",2000));
        vins.add(new Vin("","Vin1",2000));
        vins.add(new Vin("","Vin1",2000));
        vins.add(new Vin("","Vin1",2000));
        vins.add(new Vin("","Vin1",2000));
        vins.add(new Vin("","Vin1",2000));
        vins.add(new Vin("","Vin1",2000));
        vins.add(new Vin("","Vin1",2000));
        vins.add(new Vin("","Vin1",2000));
        vins.add(new Vin("","Vin1",2000));
        vins.add(new Vin("","Vin1",2000));
        vins.add(new Vin("","Vin1",2000));
        vins.add(new Vin("","Vin1",2000));
        vins.add(new Vin("","Vin1",2000));
        return vins;
    }
}
