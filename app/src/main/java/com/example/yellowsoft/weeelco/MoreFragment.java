package com.example.yellowsoft.weeelco;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by yellowsoft on 7/2/18.
 */

public class MoreFragment extends Fragment {
    public static MoreFragment newInstance(int someInt) {
        MoreFragment myFragment = new MoreFragment();

        Bundle args = new Bundle();
        args.putInt("someInt", someInt);
        myFragment.setArguments(args);

        return myFragment;
    }
}

