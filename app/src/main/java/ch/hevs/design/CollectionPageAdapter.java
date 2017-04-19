package ch.hevs.design;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import ch.hevs.design.fragments.HomeFragment;
import ch.hevs.design.fragments.MovementFragment;
import ch.hevs.design.fragments.OrderFragment;
import ch.hevs.design.fragments.SettingsFragment;

/**
 * Created by maxim on 11.04.2017.
 */

public class CollectionPageAdapter extends FragmentStatePagerAdapter {
    private HomeActivity activity;
    public CollectionPageAdapter(FragmentManager fm, HomeActivity activity){
        super(fm);
        this.activity = activity;
    }
    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;

        BottomNavBar bottomNavBar = activity.bottomNavBar;
        bottomNavBar.selectTabAtPositionWithoutLoop(i);


        if(i==0){
            HomeFragment h = new HomeFragment();
            h.setActivity(activity);
            fragment = h;

        }else if(i==1){
            fragment = new OrderFragment();
        }else if(i==2){
            fragment = new MovementFragment();
        }else if(i==3){
            fragment = new SettingsFragment();
        }

        return fragment;

    }

    @Override
    public int getCount() {
        return 4;
    }
}
