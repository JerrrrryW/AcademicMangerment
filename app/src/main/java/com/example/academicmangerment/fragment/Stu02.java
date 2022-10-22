package com.example.academicmangerment.fragment;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.academicmangerment.R;
import com.example.academicmangerment.entity.Student;
import com.example.academicmangerment.persistence.AppDatabase;
import com.example.academicmangerment.persistence.StudentDao;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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

    private AppDatabase db;
    private StudentDao studentDao;

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
    Button fabUpdate;

//    private LayoutInflater inflater_1;
//    ViewGroup container_1;
//    Bundle savedInstanceState_1;

    public Stu02() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Stu02.
     */
    // TODO: Rename and change types and number of parameters
    public static Stu02 newInstance(Student student) {
        Stu02 fragment = new Stu02();
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
        View view = inflater.inflate(R.layout.fragment_stu02_portrait, container, false);
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
        fabUpdate = (Button)view.findViewById(R.id.save_btn_stu02);
        setAttribute(student);


        fabUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                student.setRealName(editName.getText().toString());
                student.setSex("男".equals(editSex.getText().toString())?1:0);
                student.setPhone(editPhone.getText().toString());
                student.setCard(editCard.getText().toString());
                student.setState(editState.getText().toString());
                student.setDegree(editDegree.getText().toString());
                student.setType(editType.getText().toString());
                student.setEmail(editEmail.getText().toString());
                student.setBirthday(editBirth.getText().toString());
                student.setCollege(editCollege.getText().toString());
                student.setSid(editSid.getText().toString());
                db = Room.databaseBuilder(getContext(), AppDatabase.class,"dataBase").build();
                studentDao=db.studentDao();
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        studentDao.updateStudent(student);
                    }
                }.start();
            }
        });
        return view;
    }

    public void setAttribute(Student student) {
        editName.setText(student.getRealName());
        editSex.setText(student.getSex() == 1 ? "男" : "女");
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

    @Override //横竖屏切换时调用
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //如果是横屏了，在这里设置横屏的UI
            Log.println(Log.DEBUG, "Stu02Fragment", "onConfigurationChanged-Landscape");

        } else {
            //否则，在这里设置竖屏的UI
            Log.println(Log.DEBUG, "Stu02Fragment", "onConfigurationChanged-Portrait");

        }
        super.onConfigurationChanged(newConfig);
    }
}