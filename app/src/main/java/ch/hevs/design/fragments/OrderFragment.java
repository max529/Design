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
import ch.hevs.design.adapter.OrderAdapter;
import ch.hevs.design.data.Command;
import ch.hevs.design.interfaces.DefaultFragment;

/**
 * Created by maxim on 11.04.2017.
 */

public class OrderFragment extends Fragment implements DefaultFragment {
    private HomeActivity activity;
    private View rootView;
    private ListView mListView;
    public HomeActivity getHomeActivity(){
        return activity;
    }

    public OrderFragment(){}

    public void setActivity(HomeActivity activity){
        this.activity = activity;
    }

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        rootView = inflater.inflate(
                R.layout.fragment_order, container, false);

        mListView = (ListView) rootView.findViewById(R.id.listViewOrder);

        List<Command> commands;
        if(activity==null){
            activity = (HomeActivity) getActivity();
        }
        commands = activity.commands;

        OrderAdapter adapter = new OrderAdapter(rootView.getContext(), commands,OrderFragment.this);
        mListView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void updateList() {
        List<Command> commands = activity.commands;
        OrderAdapter adapter = new OrderAdapter(rootView.getContext(), commands,OrderFragment.this);
        mListView.setAdapter(adapter);
    }
}
