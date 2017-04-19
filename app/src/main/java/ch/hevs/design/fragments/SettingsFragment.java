package ch.hevs.design.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ch.hevs.design.R;
import ch.hevs.design.interfaces.DefaultFragment;

/**
 * Created by maxim on 11.04.2017.
 */

public class SettingsFragment extends Fragment implements DefaultFragment {
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.fragment_settings, container, false);

        return rootView;
    }

    @Override
    public void updateList() {

    }
}
