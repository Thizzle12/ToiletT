package com.example.henrik.henrikleger10;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Henrik on 12/11/2015.
 */
public class ForsideFragment extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_view_pager, container, false);
     //   TextView textView = (TextView) rootView.findViewById(R.id.section_label);
     //   textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));


        return root;
    }


    @Override
    public void onClick(View v) {
       
    }
}
