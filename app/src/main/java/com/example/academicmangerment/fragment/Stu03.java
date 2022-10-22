package com.example.academicmangerment.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.academicmangerment.R;
import com.example.academicmangerment.entity.Student;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Stu03#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Stu03 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "student";

    // TODO: Rename and change types of parameters
    private Student student;

    public Stu03() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment Stu03.
     */
    // TODO: Rename and change types and number of parameters
    public static Stu03 newInstance(Student student) {
        Stu03 fragment = new Stu03();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, student);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            student = (Student) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stu03, container, false);
    }
}