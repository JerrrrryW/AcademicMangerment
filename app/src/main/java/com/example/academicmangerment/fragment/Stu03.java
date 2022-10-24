package com.example.academicmangerment.fragment;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;

import com.example.academicmangerment.R;
import com.example.academicmangerment.adapter.MemberListAdapter;
import com.example.academicmangerment.entity.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Stu03#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Stu03 extends Fragment implements View.OnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "student";

    // TODO: Rename and change types of parameters
    private Student student;

    private EditText stu_sid, stu_name, stu_phone, stu_member;
    private EditText proName;
    private Spinner level, achievement_type;
    private EditText subject, budget, economic_analysis, purpose, viable_analysis;
    private RecyclerView member_list;
    private Button submit,member_add;
    private ScrollView scrollView;
    private ViewGroup.LayoutParams scrollViewParams;

    MemberListAdapter memberListAdapter;
    private List<Student> studentList;

    private  View v;
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
        v=inflater.inflate(R.layout.fragment_stu03, container, false);
        stu_sid = (EditText) v.findViewById(R.id.edit_stu03_sid);
        stu_name = (EditText) v.findViewById(R.id.edit_stu03_name);
        stu_phone = (EditText) v.findViewById(R.id.edit_stu03_phone);
        stu_member = (EditText) v.findViewById(R.id.edit_stu03_member);//参与成员
        proName = (EditText) v.findViewById(R.id.edit_stu03_proName);
        level = (Spinner) v.findViewById(R.id.stu03_level);
        achievement_type = (Spinner) v.findViewById(R.id.stu03_achievement_type);
        subject = (EditText) v.findViewById(R.id.edit_stu03_subject);
        budget = (EditText) v.findViewById(R.id.edit_stu03_budget);
        economic_analysis = (EditText) v.findViewById(R.id.edit_stu03_economic_analysis);
        purpose = (EditText) v.findViewById(R.id.edit_stu03_purpose);
        viable_analysis = (EditText) v.findViewById(R.id.edit_stu03_viable_analysis);

        submit = (Button) v.findViewById(R.id.stu03_submit);
        member_add = (Button) v.findViewById(R.id.stu03_member_add_btn);
        scrollView = (ScrollView) v.findViewById(R.id.stu03_scrollView);
        member_list = (RecyclerView) v.findViewById(R.id.stu03_member_list);

        DividerItemDecoration mDivider = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        member_list.addItemDecoration(mDivider);
        member_list.setLayoutManager(new GridLayoutManager(getContext(),1));
        memberListAdapter = new MemberListAdapter(getContext());
        //member_list.setVisibility(View.GONE); //复用至上传一个全新项目时默认没有成员，取消此注释
        //TODO 在此处将项目成员放入studentList
        studentList = new ArrayList<Student>();
        memberListAdapter.setData(studentList);
        member_list.setAdapter(memberListAdapter);
        return v;
    }

    @Override //横竖屏切换时调用
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        if (newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE){
            //如果是横屏了，在这里设置横屏的UI
            Log.println(Log.DEBUG,"stu03","onConfigurationChanged-Landscape");
            scrollViewParams = scrollView.getLayoutParams();
            scrollViewParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 700, getResources().getDisplayMetrics());
        }else {
            //否则，在这里设置竖屏的UI
            Log.println(Log.DEBUG,"stu03","onConfigurationChanged-Portrait");
            scrollViewParams = scrollView.getLayoutParams();
            scrollViewParams.width=ViewGroup.LayoutParams.MATCH_PARENT;
        }
        scrollView.setLayoutParams(scrollViewParams);
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.stu03_member_add_btn:
                //TODO
                break;
            case R.id.stu03_save_btn:
                //TODO
                break;
            case R.id.stu03_submit_btn:
                //TODO
                break;
            default:
                break;
        }
    }
}