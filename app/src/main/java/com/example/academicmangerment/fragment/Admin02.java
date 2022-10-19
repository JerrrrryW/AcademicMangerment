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
import com.example.academicmangerment.adapter.Admin02Adapter;
import com.example.academicmangerment.entity.Teacher;
import com.example.academicmangerment.persistence.AppDatabase;
import com.example.academicmangerment.persistence.TeacherDao;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Admin02#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Admin02 extends Fragment {

    private AppDatabase db;
    private TeacherDao teacherDao;
    //教师数据
    private List<Teacher> teacherList;
    private RecyclerView mRecycleView;
    private Admin02Adapter admin02Adapter;

    private View view;

    public Admin02() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Admin02.
     */
    // TODO: Rename and change types and number of parameters
    public static Admin02 newInstance() {
        Admin02 fragment = new Admin02();
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
        view=inflater.inflate(R.layout.fragment_admin02, container, false);
        mRecycleView=view.findViewById(R.id.adm02_recycleView);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }
    public void initData(){
        db= Room.databaseBuilder(getContext(),AppDatabase.class,"dataBase").build();
        teacherDao=db.teacherDao();
        new Thread(()->{
            teacherList= teacherDao.loadAllTeacher();
            admin02Adapter=new Admin02Adapter(teacherList);
            mRecycleView.setAdapter(admin02Adapter);
        }).start();
    }
}