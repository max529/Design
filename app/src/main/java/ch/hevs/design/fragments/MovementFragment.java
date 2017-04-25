package ch.hevs.design.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import ch.hevs.design.HomeActivity;
import ch.hevs.design.R;
import ch.hevs.design.adapter.MovementAdapter;
import ch.hevs.design.data.Mouvement;
import ch.hevs.design.interfaces.DefaultFragment;

/**
 * Created by maxim on 11.04.2017.
 */

public class MovementFragment extends Fragment implements DefaultFragment {
    private HomeActivity activity;
    private View rootView;
    private ListView mListView;

    public MovementFragment(){}

    public void setActivity(HomeActivity activity){
        this.activity = activity;
    }

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.fragment_movement, container, false);

        mListView = (ListView) rootView.findViewById(R.id.listViewMovement);

        List<Mouvement> mvts;
        if(activity==null){
            activity = (HomeActivity) getActivity();
        }
        mvts = activity.mvts;

        MovementAdapter adapter = new MovementAdapter(rootView.getContext(), mvts);
        mListView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void updateList() {

    }
}
