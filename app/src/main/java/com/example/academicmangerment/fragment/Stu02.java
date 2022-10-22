package com.example.academicmangerment.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.academicmangerment.R;
import com.example.academicmangerment.entity.Student;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Stu02#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Stu02 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "student";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private Student student;

    ImageView imageView;
    EditText editName;
    EditText editSex;
    EditText editPhone;
    EditText editCard;
    EditText editState;
    EditText editDegree;
    EditText editType;
    EditText editEmail;
    EditText editBirth;
    EditText editCollege;
    EditText editSid;

    public Stu02() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment Stu02.
     */
    // TODO: Rename and change types and number of parameters
    public static Stu02 newInstance(Student student) {
        Stu02 fragment = new Stu02();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1,student);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            student =(Student) getArguments().getSerializable(ARG_PARAM1);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_stu02, container, false);
        imageView = view.findViewById(R.id.stu_img);
        editName = (EditText) view.findViewById(R.id.edit_stu02_name);
        editSex = (EditText) view.findViewById(R.id.edit_stu02_sex);
        editPhone = (EditText) view.findViewById(R.id.edit_stu02_phone);
        editCard = (EditText) view.findViewById(R.id.edit_stu02_card);
        editState = (EditText) view.findViewById(R.id.edit_stu02_state);
        editDegree = (EditText) view.findViewById(R.id.edit_stu02_degree);
        editType = (EditText) view.findViewById(R.id.edit_stu02_type);
        editEmail = (EditText) view.findViewById(R.id.edit_stu02_email);
        editBirth = (EditText) view.findViewById(R.id.edit_stu02_birth);
        editCollege = (EditText) view.findViewById(R.id.edit_stu02_college);
        editSid = (EditText) view.findViewById(R.id.edit_stu02_sid);
        setAttribute(student);
        return view;
    }
    public void setAttribute(Student student){
        editName.setText(student.getRealName());
        editSex.setText(student.getSex()==1?"男":"女");
        editPhone.setText(student.getPhone());
        editCard.setText(student.getCard());
        editState.setText(student.getState());
        editDegree.setText(student.getDegree());
        editType.setText(student.getType());
        editEmail.setText(student.getEmail());
        editBirth.setText(student.getBirthday());
        editCollege.setText(student.getCollege());
        editSid.setText(student.getSid());
    }
}