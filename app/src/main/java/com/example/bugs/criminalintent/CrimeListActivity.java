package com.example.bugs.criminalintent;

import android.app.Fragment;

/**
 * Created by SAMSUNG on 2016/2/23.
 */
public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}

