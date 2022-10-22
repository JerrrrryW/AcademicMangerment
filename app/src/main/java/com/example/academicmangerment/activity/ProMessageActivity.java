package com.example.academicmangerment.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.academicmangerment.activity.StuActivity;
import com.example.academicmangerment.R;
import com.example.academicmangerment.adapter.MemberListAdapter;
import com.example.academicmangerment.entity.Project;
import com.example.academicmangerment.entity.Student;
import com.example.academicmangerment.fragment.Stu04;
import com.example.academicmangerment.persistence.ProjectDao;

import java.util.ArrayList;
import java.util.List;

public class ProMessageActivity extends AppCompatActivity implements View.OnClickListener/*implements Stu04.SendProject*/ {
    Project project;//接收Stu04的数据
    private EditText stu_sid, stu_name, stu_phone, stu_member;
    private EditText name;
    private Spinner level, achievement_type;
    private EditText subject, budget, economic_analysis, purpose, viable_analysis;
    private RecyclerView member_list;
    private Button submit,member_add;
    private ScrollView scrollView;
    private ViewGroup.LayoutParams scrollViewParams;
    private List<Student> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_sign_up);

        initView();
        setData();
    }

    public void initView() {
        stu_sid = (EditText) findViewById(R.id.stu_sid);
        stu_name = (EditText) findViewById(R.id.stu_name);
        stu_phone = (EditText) findViewById(R.id.stu_phone);
        stu_member = (EditText) findViewById(R.id.stu_member);//参与成员
        name = (EditText) findViewById(R.id.name);
        level = (Spinner) findViewById(R.id.level);
        achievement_type = (Spinner) findViewById(R.id.achievement_type);
        subject = (EditText) findViewById(R.id.subject);
        budget = (EditText) findViewById(R.id.budget);
        economic_analysis = (EditText) findViewById(R.id.economic_analysis);
        purpose = (EditText) findViewById(R.id.purpose);
        viable_analysis = (EditText) findViewById(R.id.viable_analysis);
        submit = (Button) findViewById(R.id.submit);
        member_add = (Button) findViewById(R.id.member_add_btn);
        scrollView = (ScrollView) findViewById(R.id.scrollView1);
        member_list = (RecyclerView) findViewById(R.id.member_list);

        DividerItemDecoration mDivider = new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL);
        member_list.addItemDecoration(mDivider);
        member_list.setLayoutManager(new LinearLayoutManager(this));
        MemberListAdapter memberListAdapter = new MemberListAdapter(this);
        //member_list.setVisibility(View.GONE); //复用至上传一个全新项目时默认没有成员，取消此注释
        //TODO 在此处将项目成员放入studentList
        studentList = memberGenerator();
        memberListAdapter.setData(studentList);
        member_list.setAdapter(memberListAdapter);
    }

    @Override //横竖屏切换时调用
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        if (newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE){
            //如果是横屏了，在这里设置横屏的UI
            Log.println(Log.DEBUG,"ProMessageActivity","onConfigurationChanged-Landscape");
            scrollViewParams = scrollView.getLayoutParams();
            scrollViewParams.width = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 700, getResources().getDisplayMetrics());
            scrollView.setLayoutParams(scrollViewParams);
        }else {
            //否则，在这里设置竖屏的UI
            Log.println(Log.DEBUG,"ProMessageActivity","onConfigurationChanged-Portrait");
            scrollViewParams = scrollView.getLayoutParams();
            scrollViewParams.width=ViewGroup.LayoutParams.MATCH_PARENT;
            scrollView.setLayoutParams(scrollViewParams);
        }
            super.onConfigurationChanged(newConfig);
    }

    public void setData() {
        String pid,sid,sname,userPhone;
        List<String> members = new ArrayList<>();
        List<Project> projects = new ArrayList<>();

        Intent intent = getIntent();
        pid = intent.getStringExtra("Pid");//用于数据库中查询相应Project
        sid = intent.getStringExtra("Sid");
        sname = intent.getStringExtra("Sname");
        userPhone = intent.getStringExtra("Phone");

        Project project = new Project();
        for (Project pro : projects) {
            if (pro.pid == pid) {
                project = pro;
                break;
            }
        }
        setData(stu_sid,sid);
        setData(stu_name, sname);
        setData(stu_phone, userPhone);
        setData(name, project.getName());

        setSpinnerData(level, project.getLevel());
        setSpinnerData(achievement_type, project.getAchievementType());
        setData(subject, project.getSubject());
        setData(budget, Double.toString(project.getBudget()));
        setData(economic_analysis, project.getEconomicAnalysis());
        setData(purpose, project.getPurpose());
        setData(viable_analysis, project.getViableAnalysis());

        submit.setText("更改");
    }

    public void setData(EditText editText, String s) {
        editText.setText(s);
        editText.setKeyListener(null);
    }

    public void setSpinnerData(Spinner spinner, String s) {
        SpinnerAdapter adapter = spinner.getAdapter();
        int k = adapter.getCount();
        for (int i = 0; i < k; i++) {
            if (s.equals(adapter.getItem(i).toString())) {
                spinner.setSelection(i, true);
                break;
            }
        }
        spinner.setClickable(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.member_add_btn:
                //TODO
                break;
            default:
                break;
        }
    }

    private List<Student> memberGenerator(){
        List<Student> students = new ArrayList<Student>();
        students.add(new Student("2220191035","1055689557888","2019/09/01","123","13332284652","ＷＲＨ",
                "无","本科生","大连海事大学","09/22","本科生","中国",1,"2220192757xd@dlmu.edu.cn"));
        students.add(new Student("2220191036","1055689557888","2019/09/01","123","13332284652","ＰＪＳ",
                "无","本科生","大连海事大学","09/22","本科生","中国",1,"2220192757xd@dlmu.edu.cn"));
        students.add(new Student("2220191037","1055689557888","2019/09/01","123","13332284652","ＨＪＫ",
                "无","本科生","大连海事大学","09/22","本科生","中国",1,"2220192757xd@dlmu.edu.cn"));
        return students;
    }
}
