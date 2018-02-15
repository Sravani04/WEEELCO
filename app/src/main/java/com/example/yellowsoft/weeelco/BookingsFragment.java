package com.example.yellowsoft.weeelco;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by yellowsoft on 7/2/18.
 */

public class BookingsFragment extends Fragment {
    LinearLayout header;
    public static BookingsFragment newInstance(int someInt) {
        BookingsFragment myFragment = new BookingsFragment();

        Bundle args = new Bundle();
        args.putInt("someInt", someInt);
        myFragment.setArguments(args);

        return myFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.bookings_screen,container,false);
        header = (LinearLayout) view.findViewById(R.id.header);
        header.setVisibility(View.GONE);
        return view;
    }
}

