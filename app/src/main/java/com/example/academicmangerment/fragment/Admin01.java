package com.example.academicmangerment.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.academicmangerment.R;
import com.example.academicmangerment.adapter.Admin01Adapter;
import com.example.academicmangerment.entity.Student;
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
        Bundle args = new Bundle();
        fragment.setArguments(args);
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
        view=inflater.inflate(R.layout.fragment_admin01, container, false);
        mRecyclerView=view.findViewById(R.id.adm01_recyclerview);
        //设置线性布局
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        initData();
        return view;
    }
    public void initData(){
        db = Room.databaseBuilder(getContext(), AppDatabase.class,"dataBase").build();
        studentDao=db.studentDao();
        new Thread(()->{
            studentList=studentDao.loadAllStudent();
            admin01Adapter=new Admin01Adapter(studentList);
            mRecyclerView.setAdapter(admin01Adapter);
        }).start();
    }
}