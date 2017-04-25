package ch.hevs.design.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import ch.hevs.design.DetailVinActivity;
import ch.hevs.design.HomeActivity;
import ch.hevs.design.R;
import ch.hevs.design.adapter.WineAdapter;
import ch.hevs.design.data.Vin;
import ch.hevs.design.interfaces.DefaultFragment;

/**
 * Created by maxim on 11.04.2017.
 */

public class HomeFragment extends Fragment implements DefaultFragment {
    private HomeActivity activity;
    private View rootView;
    private ListView mListView;
    public HomeFragment(){}

    public void setActivity(HomeActivity activity){
        this.activity = activity;
    }

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        rootView = inflater.inflate(
                R.layout.fragment_home, container, false);

        mListView = (ListView) rootView.findViewById(R.id.listViewWine);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,long arg3) {
                view.setSelected(true);
                Vin v = (Vin)mListView.getItemAtPosition(position);
                Intent intent = new Intent(activity, DetailVinActivity.class);
                intent.putExtra("Vin",v);
                activity.startActivity(intent);
            }
        });
        List<Vin> vins;
        if(activity==null){
            activity = (HomeActivity) getActivity();
        }
        vins = activity.vins;


        WineAdapter adapter = new WineAdapter(rootView.getContext(), vins);
        mListView.setAdapter(adapter);

        return rootView;
    }


    @Override
    public void updateList() {
        List<Vin> vins = activity.vins;
        WineAdapter adapter = new WineAdapter(rootView.getContext(), vins);
        mListView.setAdapter(adapter);
    }
}
