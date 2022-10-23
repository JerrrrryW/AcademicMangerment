package com.example.academicmangerment.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.academicmangerment.R;
import com.example.academicmangerment.entity.Teacher;
import com.example.academicmangerment.persistence.AppDatabase;
import com.example.academicmangerment.persistence.TeacherDao;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Tec02#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tec02 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "teacher";

    // TODO: Rename and change types of parameters
    private Teacher teacher;
    ImageView imageView;
    EditText editName;
    EditText editPhone;
    EditText editEmail;
    EditText editTid;
    Button saveButton;

    private AppDatabase db;
    private TeacherDao teacherDao;

    public Tec02() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment Tec02.
     */
    // TODO: Rename and change types and number of parameters
    public static Tec02 newInstance(Teacher teacher) {
        Tec02 fragment = new Tec02();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, teacher);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            teacher=(Teacher) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_tec02, container, false);
        imageView = view.findViewById(R.id.tec_img);
        editName = (EditText) view.findViewById(R.id.edit_tec02_name);
        editEmail = (EditText) view.findViewById(R.id.edit_tec02_email);
        editPhone = (EditText) view.findViewById(R.id.edit_tec02_phone);
        editTid = (EditText) view.findViewById(R.id.edit_tec02_tid);
        setAttribute(teacher);
        saveButton=(Button) view.findViewById(R.id.save_btn_tec02);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teacher.setTecName(editName.getText().toString());
                teacher.setEmail(editEmail.getText().toString());
                teacher.setTel(editPhone.getText().toString());
                teacher.setTid(editTid.getText().toString());
                db = Room.databaseBuilder(getContext(), AppDatabase.class,"dataBase").build();
                teacherDao=db.teacherDao();
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        teacherDao.updateTeacher(teacher);
                    }
                }.start();
            }
        });
        return view;
    }
    public void setAttribute(Teacher teacher) {
        editName.setText(teacher.getTecName());
        editEmail.setText(teacher.getEmail());
        editPhone.setText(teacher.getTel());
        editTid.setText(teacher.getTid());
    }
}