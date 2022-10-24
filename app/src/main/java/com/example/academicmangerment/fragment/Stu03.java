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
import android.widget.SpinnerAdapter;

import com.example.academicmangerment.R;
import com.example.academicmangerment.adapter.MemberListAdapter;
import com.example.academicmangerment.entity.ProjectDetail;
import com.example.academicmangerment.entity.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Stu03#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Stu03 extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "student";
    View view;

    // TODO: Rename and change types of parameters
    private Student student;
    private EditText stu_sid, stu_name, stu_phone, stu_member;
    private EditText name;
    private Spinner level, achievement_type;
    private EditText subject, budget, economic_analysis, purpose, viable_analysis;
    private RecyclerView member_list;
    private Button submit,member_add;
    private ScrollView scrollView;
    private ViewGroup.LayoutParams scrollViewParams;

    MemberListAdapter memberListAdapter;
    private List<Student> studentList;
    private ProjectDetail projectDetail;

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
        view = inflater.inflate(R.layout.fragment_stu03, container, false);
        initView();
        return inflater.inflate(R.layout.fragment_stu03, container, false);
    }

    public void initView() {
        stu_sid = (EditText) view.findViewById(R.id.stu_sid);
        stu_name = (EditText) view.findViewById(R.id.stu_name);
        stu_phone = (EditText) view.findViewById(R.id.stu_phone);
        stu_member = (EditText) view.findViewById(R.id.stu_member);//参与成员
        name = (EditText) view.findViewById(R.id.name);
        level = (Spinner) view.findViewById(R.id.level);
        achievement_type = (Spinner) view.findViewById(R.id.achievement_type);
        subject = (EditText) view.findViewById(R.id.subject);
        budget = (EditText) view.findViewById(R.id.budget);
        economic_analysis = (EditText) view.findViewById(R.id.economic_analysis);
        purpose = (EditText) view.findViewById(R.id.purpose);
        viable_analysis = (EditText) view.findViewById(R.id.viable_analysis);

        submit = (Button) view.findViewById(R.id.submit);
        member_add = (Button) view.findViewById(R.id.member_add_btn);
        scrollView = (ScrollView) view.findViewById(R.id.scrollView1);
        member_list = (RecyclerView) view.findViewById(R.id.member_list);

        DividerItemDecoration mDivider = new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL);
        member_list.addItemDecoration(mDivider);
        member_list.setLayoutManager(new GridLayoutManager(getActivity(),1));
        memberListAdapter = new MemberListAdapter(getActivity());
        //member_list.setVisibility(View.GONE); //复用至上传一个全新项目时默认没有成员，取消此注释
        studentList = new ArrayList<Student>();
        memberListAdapter.setData(studentList);
        member_list.setAdapter(memberListAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.member_add_btn:
                //TODO 在此处完成：获取学号、核对服务器、添加成员进List<Student>后刷新成员列表
                break;
            default:
                break;
        }
    }
}