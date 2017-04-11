package ch.hevs.design;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnTabSelectListener;

/**
 * Created by maxim on 11.04.2017.
 */

public class BottomNavBar {
    private HomeActivity activity;
    private CollectionPageAdapter mDemoCollectionPagerAdapter;
    private ViewPager mViewPager;
    private BottomBar bottomBar;
    public BottomNavBar(){}
    public BottomNavBar(BottomBar bottomBar) {
        this.bottomBar = bottomBar;
    }
    public void setActivity(HomeActivity activity){
        this.activity = activity;
    }
    public  HomeActivity getActivity(){
        return  activity;
    }
    public void addPageViewer(){
        mDemoCollectionPagerAdapter =
                new CollectionPageAdapter(
                        activity.getSupportFragmentManager(),activity);
        mViewPager = (ViewPager) activity.findViewById(R.id.pager);
        mViewPager.setAdapter(mDemoCollectionPagerAdapter);


        setAction();
    }

    public void selectTabAtPositionWithoutLoop(int position){

        /*bottomBar.setOnTabSelectListener(null,false);
        bottomBar.selectTabAtPosition(position);
        setAction();*/
    }
    public void setAction(){
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {

                if (tabId == R.id.tab_vin) {
                    activity.setTitle(R.string.navVin);
                    mViewPager.setCurrentItem(0);
                }else if(tabId == R.id.tab_commande){
                    activity.setTitle(R.string.navCommande);
                    mViewPager.setCurrentItem(1);
                }else if(tabId == R.id.tab_mouvement){
                    activity.setTitle(R.string.navMouvement);
                    mViewPager.setCurrentItem(2);
                }else if(tabId == R.id.tab_settings){
                    activity.setTitle(R.string.navParam);
                    mViewPager.setCurrentItem(3);
                }
            }
        });
    }
}
