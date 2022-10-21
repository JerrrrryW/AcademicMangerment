package com.example.academicmangerment.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.academicmangerment.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Admin04#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Admin04 extends Fragment {

    public Admin04() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment Admin04.
     */
    // TODO: Rename and change types and number of parameters
    public static Admin04 newInstance() {
        Admin04 fragment = new Admin04();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin04, container, false);
    }
}