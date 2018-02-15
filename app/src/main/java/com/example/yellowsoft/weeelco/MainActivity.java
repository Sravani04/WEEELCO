package com.example.yellowsoft.weeelco;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.IdRes;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import java.util.Locale;

import it.sephiroth.android.library.bottomnavigation.BottomNavigation;

public class MainActivity extends AppCompatActivity  implements BottomNavigation.OnMenuItemSelectionListener{
    private ActionBarDrawerToggle mDrawerToggle;
    private AHBottomNavigation mBottomNavigation;
    public ViewPager mViewPager;
    private TabsAdapter tabsAdapter;
    private DrawerLayout mDrawerLayout;
    int previous_postion=0;
    boolean previous_page;
    String pre="0";
    private static long back_pressed;
    ImageView page_back,logo,lan;
    String act;
    Toolbar toolbar;

    private void setupActionBar() {
//set action bar
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setIcon(new ColorDrawable(getResources().getColor(android.R.color.transparent)));


        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT);

        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.action_bar, null);

        logo=(ImageView) v.findViewById(R.id.logo);
        page_back = (ImageView) v.findViewById(R.id.page_back);
        lan = (ImageView) v.findViewById(R.id.lan);
        lan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,LanguageScreen.class);
                startActivity(intent);
            }
        });

        getSupportActionBar().setCustomView(v, layoutParams);
        Toolbar parent = (Toolbar) v.getParent();
        parent.setContentInsetsAbsolute(0, 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Session.forceRTLIfSupported(this);
        act = getIntent().getStringExtra("act");
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mBottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
        mBottomNavigation.addItem(new AHBottomNavigationItem("Search", R.drawable.search_active));
        mBottomNavigation.addItem(new AHBottomNavigationItem("Bookings", R.drawable.car_inactive));
        mBottomNavigation.addItem(new AHBottomNavigationItem("Account", R.drawable.account_inactive));
        mBottomNavigation.addItem(new AHBottomNavigationItem("More", R.drawable.more_inactive));
        mBottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        mBottomNavigation.setDefaultBackgroundColor(Color.parseColor("#ffffff"));
        mBottomNavigation.setAccentColor(Color.parseColor("#b6aa8e"));
        mBottomNavigation.setInactiveColor(Color.parseColor("#444444"));
        mBottomNavigation.setForceTint(true);
        mBottomNavigation.setCurrentItem(0);
        mBottomNavigation.setNotificationBackgroundColor(Color.parseColor("#F63D2B"));
        mBottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                mViewPager.setCurrentItem(position);
                return true;
            }
        });
        mBottomNavigation.setOnNavigationPositionListener(new AHBottomNavigation.OnNavigationPositionListener() {
            @Override public void onPositionChange(int y) {
            }
        });



        tabsAdapter = new TabsAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.setAdapter(tabsAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }


            @Override
            public void onPageSelected(int position) {
                try {

                    if (position == 0)
                        mBottomNavigation.setCurrentItem(position, false);
                    else if (position == 1)
                        mBottomNavigation.setCurrentItem(position, false);
                    else if (position == 2)
                        mBottomNavigation.setCurrentItem(position, false);
                    else if (position == 3)
                        mBottomNavigation.setCurrentItem(position, false);


                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                setHeader(position);
            }


            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });




        toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        setSupportActionBar(toolbar);
        setupActionBar();
        setHeader(0);


        if(act.equals("1")){
            mViewPager.setCurrentItem(2);
        }else if (act.equals("0")){
            mViewPager.setCurrentItem(0);
        }



        RelativeLayout mainView = (RelativeLayout) findViewById(R.id.mainView);
    }


    @Override
    public void onBackPressed() {
        Log.e("pp_pp_back",String.valueOf(previous_postion));
        if(mViewPager.getCurrentItem()!=0){
            if(mViewPager.getCurrentItem()==1) {
                mViewPager.setCurrentItem(previous_postion, false);
            }else if(mViewPager.getCurrentItem()==2) {
                mViewPager.setCurrentItem(previous_postion, false);
            }else if(mViewPager.getCurrentItem()==3) {
                mViewPager.setCurrentItem(previous_postion, false);
            }else
                mViewPager.setCurrentItem(0, false);
        }

        else
            finish();

    }


    @Override
    public void onMenuItemSelect(@IdRes int i, int i1, boolean b) {

    }

    @Override
    public void onMenuItemReselect(@IdRes int i, int i1, boolean b) {
        if (i1 == 2 && !previous_page && mViewPager.getCurrentItem() != 3) {

            try {
                //tabsAdapter.newsFragment.get_news("0");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        previous_page = false;
    }


    private void setHeader(int pos) {
        logo.setVisibility(View.GONE);

        switch (pos) {

            case 0:
                logo.setVisibility(View.VISIBLE);
                lan.setVisibility(View.VISIBLE);

                break;

            case 1:
                logo.setVisibility(View.VISIBLE);
                lan.setVisibility(View.GONE);
                break;

            case 2:
                logo.setVisibility(View.VISIBLE);
                lan.setVisibility(View.GONE);
//                page_title.setText("Categories");
                break;
            case 3:

                logo.setVisibility(View.VISIBLE);
                lan.setVisibility(View.GONE);

                break;

            default:

                break;
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        try {
            if(pre.equals("1")){
                mBottomNavigation.setCurrentItem(0);
            }
//            tabsAdapter.profileFragment.get_members();
        }catch (Exception e){
            e.printStackTrace();
        }


    }


}
