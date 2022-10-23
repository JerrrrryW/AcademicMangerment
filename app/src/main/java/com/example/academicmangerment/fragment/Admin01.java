package com.example.academicmangerment.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.academicmangerment.R;
import com.example.academicmangerment.adapter.Admin01Adapter;
import com.example.academicmangerment.adapter.Admin02Adapter;
import com.example.academicmangerment.entity.Student;
import com.example.academicmangerment.entity.Teacher;
import com.example.academicmangerment.persistence.AppDatabase;
import com.example.academicmangerment.persistence.StudentDao;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Admin01#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Admin01 extends Fragment {

    private AppDatabase db;
    private StudentDao studentDao;
    //学生信息数据
    private List<Student> studentList;
    private RecyclerView mRecyclerView;
    private Admin01Adapter admin01Adapter;

    private View view;

    public Admin01() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Admin01.
     */
    // TODO: Rename and change types and number of parameters
    public static Admin01 newInstance() {
        Admin01 fragment = new Admin01();
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
        view = inflater.inflate(R.layout.fragment_admin01, container, false);
        db = Room.databaseBuilder(getContext(), AppDatabase.class, "dataBase").build();
        studentDao = db.studentDao();
        /*initData();*/
        @SuppressLint("HandlerLeak") final Handler handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                studentList = (List<Student>) msg.obj;
                admin01Adapter = new Admin01Adapter(studentList,getContext());
                mRecyclerView = (RecyclerView) view.findViewById(R.id.adm01_recyclerview);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                mRecyclerView.setAdapter(admin01Adapter);
                /*admin01Adapter.setButtonInterface(new Admin01Adapter.ButtonInterface() {
                    @Override
                    public void OnItemClick(View view, int position) {

                        EditText editName = (EditText) view.findViewById(R.id.edit_stu_name);
                        EditText editSex = (EditText) view.findViewById(R.id.edit_stu_sex);
                        EditText editPhone = (EditText) view.findViewById(R.id.edit_stu_phone);
                        EditText editCard = (EditText) view.findViewById(R.id.edit_stu_card);
                        EditText editState = (EditText) view.findViewById(R.id.edit_stu_state);
                        EditText editDegree = (EditText) view.findViewById(R.id.edit_stu_degree);
                        EditText editType = (EditText) view.findViewById(R.id.edit_stu_type);
                        EditText editEmail = (EditText) view.findViewById(R.id.edit_stu_email);
                        EditText editBirth = (EditText) view.findViewById(R.id.edit_stu_birth);
                        EditText editCollege = (EditText) view.findViewById(R.id.edit_stu_college);
                        EditText editSid = (EditText) view.findViewById(R.id.edit_stu_sid);
                        Student student = studentList.get(position);
                        student.setSid(editName.getText().toString());
                        String sex = editSex.getText().toString();
                        student.setSex(sex.equals("男") ? 1 : 0);
                        student.setPhone(editPhone.getText().toString());
                        student.setCard(editCard.getText().toString());
                        student.setState(editState.getText().toString());
                        student.setDegree(editDegree.getText().toString());
                        student.setType(editType.getText().toString());
                        student.setEmail(editEmail.getText().toString());
                        student.setBirthday(editBirth.getText().toString());
                        student.setCollege(editCollege.getText().toString());
                        student.setSid(editSid.getText().toString());
                        new MyThread(student).run();
                    }
                });*/
            }
        };

        new Thread() {
            @Override
            public void run() {
                super.run();
                studentList = studentDao.loadAllStudent();
                Message message = Message.obtain();
                message.obj = studentList;
                handler.sendMessage(message);
            }
        }.start();
        return view;
    }

    /*    public void initData(){
            db = Room.databaseBuilder(getContext(), AppDatabase.class,"dataBase").build();
            studentDao=db.studentDao();
            new Thread(()->{
                studentList=studentDao.loadAllStudent();
                admin01Adapter=new Admin01Adapter(studentList);
                mRecyclerView.setAdapter(admin01Adapter);
            }).start();
        }*/
    class MyThread extends Thread {
        Student student;

        public MyThread() {
        }
        public MyThread(Student student){
            this.student=student;
        }
        @Override
        public void run() {
            studentDao.updateStudent(student);
            super.run();
        }
    }
}