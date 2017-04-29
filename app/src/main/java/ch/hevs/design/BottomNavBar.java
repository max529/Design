package ch.hevs.design;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import ch.hevs.design.adapter.CollectionPageAdapter;
import ch.hevs.design.components.NonSwipeableViewPager;
import ch.hevs.design.interfaces.DefaultFragment;

/**
 * Created by maxim on 11.04.2017.
 */

public class BottomNavBar {
    private HomeActivity activity;
    private Menu menu;
    private CollectionPageAdapter mDemoCollectionPagerAdapter;
    private NonSwipeableViewPager mViewPager;
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

    public CollectionPageAdapter getmDemoCollectionPagerAdapter(){
        return mDemoCollectionPagerAdapter;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void addPageViewer(){
        mDemoCollectionPagerAdapter =
                new CollectionPageAdapter(
                        activity.getSupportFragmentManager(),activity);
        mViewPager = (NonSwipeableViewPager) activity.findViewById(R.id.pager);
        mViewPager.setAdapter(mDemoCollectionPagerAdapter);



        setAction();
    }

    public void updateFragment(int i){
        ViewPager pager = (ViewPager)activity.findViewById(R.id.pager);
        FragmentStatePagerAdapter a = (FragmentStatePagerAdapter) pager.getAdapter();
        DefaultFragment fra = (DefaultFragment) a.instantiateItem(pager, i);
        fra.updateList();
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
                    if(menu!=null) {
                        menu.clear();
                        activity.getMenuInflater().inflate(R.menu.home, menu);

                    }
                    activity.setTitle(R.string.navVin);
                    mViewPager.setCurrentItem(0);

                }else if(tabId == R.id.tab_commande){
                    if(menu!=null) {
                        menu.clear();
                        activity.getMenuInflater().inflate(R.menu.order, menu);
                    }
                    activity.setTitle(R.string.navCommande);
                    mViewPager.setCurrentItem(1);
                }else if(tabId == R.id.tab_mouvement){
                    if(menu!=null) {
                        menu.clear();
                        activity.getMenuInflater().inflate(R.menu.movement, menu);
                    }
                    activity.setTitle(R.string.navMouvement);
                    mViewPager.setCurrentItem(2);
                }else if(tabId == R.id.tab_settings){
                    if(menu!=null) {
                        menu.clear();
                    }
                    activity.setTitle(R.string.navParam);
                    mViewPager.setCurrentItem(3);
                }
            }
        });
    }
}
