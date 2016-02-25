package com.example.bugs.criminalintent;
import android.content.Context;
import java.util.ArrayList;
import java.util.UUID;
/**
 * Created by SAMSUNG on 2016/2/22.
 */
public class CrimeLab {
    private ArrayList<Crime> mCrimes;
    private Context mAppContext;
    private static CrimeLab sCrimeLab;
    private CrimeLab(Context appContext) {
        mAppContext = appContext;
        mCrimes = new ArrayList<Crime>();
        for (int i = 0; i < 100; i++) {
            Crime c = new Crime();
            c.setSolved(i % 2 == 0);
            c.setTitle("罪行  #" + i);
            mCrimes.add(c);
        }
    }
    public static CrimeLab get(Context c) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(c.getApplicationContext());
        }
        return sCrimeLab;
    }

    public ArrayList<Crime> getCrimes() {
        return mCrimes;
    }

    public Crime getCrime(UUID id) {
        for (Crime c : mCrimes) {
            if (c.getID().equals(id)) {
                return c;
            }
        }
        return null;
    }
}


