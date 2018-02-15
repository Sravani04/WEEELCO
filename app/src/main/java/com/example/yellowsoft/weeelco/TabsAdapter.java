package com.example.yellowsoft.weeelco;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

/**
 * Created by yellowsoft on 7/2/18.
 */

public class TabsAdapter extends FragmentStatePagerAdapter {
    HomeFragment homeFragment;
    BookingsFragment bookingsFragment;
    AccountFragment accountFragment;
    MoreFragment moreFragment;
    Context context;

    public TabsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Log.e("pos", String.valueOf(position));

        if (position == 0) {
            return HomeFragment.newInstance(position);

        } else if (position == 1) {
            bookingsFragment = BookingsFragment.newInstance(position);
            return bookingsFragment;


        }else if(position == 2){
            accountFragment = AccountFragment.newInstance(position);
            return accountFragment;

        }else if(position == 3) {
            moreFragment = MoreFragment.newInstance(position);
            return moreFragment;
        } else
            return DemoFragment.newInstance(position);

    }

    @Override
    public int getCount() {
        return 5;
    }


}

