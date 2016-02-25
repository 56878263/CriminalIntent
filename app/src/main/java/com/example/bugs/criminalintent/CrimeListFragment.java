package com.example.bugs.criminalintent;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;;

import java.util.ArrayList;

/**
 * Created by SAMSUNG on 2016/2/22.
 */
public class CrimeListFragment extends ListFragment {
    private ArrayList<Crime> mCrimes;
    private static final String TAG = "CrimeListFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.crimes_title);
        mCrimes = CrimeLab.get(getActivity()).getCrimes();
        ArrayAdapter<Crime> adapter = new CrimeAdapter(mCrimes);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Crime c = ((CrimeAdapter) getListAdapter()).getItem(position);
        Log.d(TAG, "我单击了  " + c.getTitle());
        Intent intent=new Intent(getActivity(),CrimeActivity.class);
        intent.putExtra(CrimeFragment.EXTRA_CRIME_ID,c.getID());
        startActivity(intent);
    }

    private class CrimeAdapter extends ArrayAdapter<Crime> {
        public CrimeAdapter(ArrayList<Crime> crimes) {
            super(getActivity(), 0, crimes);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_itme_crime, null);
            }
            TextView title = (TextView) convertView.findViewById(R.id.crime_list_item_titleTextView);
            TextView date = (TextView) convertView.findViewById(R.id.crime_list_item_DateTextView);
            CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.crime_list_item_solvedCheckBox);
            Crime crime = getItem(position);
            title.setText(crime.getTitle());
            checkBox.setChecked(crime.isSolved());
            date.setText(DateFormat.format("yyyy年MM月dd  HH:mm", crime.getDate()));
            return convertView;
        }

    }


}
