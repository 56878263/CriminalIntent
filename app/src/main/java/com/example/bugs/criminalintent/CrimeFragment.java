package com.example.bugs.criminalintent;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.UUID;

/**
 * Created by SAMSUNG on 2016/2/22.
 */
public class CrimeFragment extends Fragment {
    private Crime mCrime;  //自定义类
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedChecBox;
public static final String EXTRA_CRIME_ID="com.example.bugs.criminalintent.crune_id";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID crimeID=(UUID) getActivity().getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeID);

    }
public static CrimeFragment newInstances(UUID id){
Bundle args=new Bundle();
    args.putSerializable(CrimeFragment.EXTRA_CRIME_ID,id);
    CrimeFragment fragment=new CrimeFragment();
    fragment.setArguments(args);
    return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime, container, false);
        mTitleField = (EditText) view.findViewById(R.id.crime_title);
        mTitleField.setText(mCrime.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mDateButton = (Button) view.findViewById(R.id.crime_date);
        mDateButton.setText( DateFormat.format("yyyy/MM/dd-E-H:m",mCrime.getDate()));
        mDateButton.setEnabled(false);
        mSolvedChecBox = (CheckBox) view.findViewById(R.id.crime_solved);
        mSolvedChecBox.setChecked(mCrime.isSolved());
        mSolvedChecBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCrime.setSolved(isChecked);
            }
        });

        return view;

    }
}
