package com.example.academicmangerment.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.academicmangerment.R;
import com.example.academicmangerment.adapter.MemberListAdapter;
import com.example.academicmangerment.entity.Project;
import com.example.academicmangerment.entity.ProjectDetail;
import com.example.academicmangerment.entity.Student;
import com.example.academicmangerment.persistence.AppDatabase;
import com.example.academicmangerment.persistence.ProjectDao;

import java.util.ArrayList;
import java.util.List;

public class ProMessageActivity extends AppCompatActivity implements View.OnClickListener /*implements Stu04.SendProject*/ {
    Project project;//接收Stu04的数据
    private EditText stu_sid, stu_name, stu_phone, stu_member;
    private EditText name;
    private Spinner level, achievement_type;
    private EditText subject, budget, economic_analysis, purpose, viable_analysis;
    private RecyclerView member_list;
    private Button submit,member_add,approve,reject,midReview,finalCheck,modify,delete,upload,save;
    private ScrollView scrollView;
    private ViewGroup.LayoutParams scrollViewParams;
    private int queryType;
    MemberListAdapter memberListAdapter;

    private List<Student> studentList;
    private ProjectDetail projectDetail;

    private AppDatabase db;
    private ProjectDao projectDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_sign_up);
        //获取传输来数据
        Intent intent=getIntent();
        projectDetail=(ProjectDetail) intent.getExtras().getSerializable("projectDetail");
        queryType=intent.getExtras().getInt("queryType");
        initView();
        initData();
    }

    public void initView() {
        stu_sid = (EditText) findViewById(R.id.stu_sid);
        stu_name = (EditText) findViewById(R.id.stu_name);
        stu_phone = (EditText) findViewById(R.id.stu_phone);
        //stu_member = (EditText) findViewById(R.id.stu_member);//参与成员
        name = (EditText) findViewById(R.id.name);
        level = (Spinner) findViewById(R.id.level);
        achievement_type = (Spinner) findViewById(R.id.achievement_type);
        subject = (EditText) findViewById(R.id.subject);
        budget = (EditText) findViewById(R.id.budget);
        economic_analysis = (EditText) findViewById(R.id.economic_analysis);
        purpose = (EditText) findViewById(R.id.purpose);
        viable_analysis = (EditText) findViewById(R.id.viable_analysis);

        //member_add = (Button) findViewById(R.id.member_add_btn);
        scrollView = (ScrollView) findViewById(R.id.scrollView1);
        member_list = (RecyclerView) findViewById(R.id.member_list);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,"dataBase").build();
        projectDao=db.projectDao();

        finalCheck = findViewById(R.id.detail_FinalCheck_btn);
        midReview = findViewById(R.id.detail_midReviewCheck_btn);
        approve= (Button) findViewById(R.id.detail_approve_btn);
        reject= (Button) findViewById(R.id.detail_reject_btn);
        submit = (Button) findViewById(R.id.detail_submit_btn);
        modify = findViewById(R.id.detail_modify_btn);
        delete = findViewById(R.id.detail_delete_btn);
        upload = findViewById(R.id.detail_upload_btn);
        save = findViewById(R.id.detail_save_btn);
        //按钮监听
        finalCheck.setOnClickListener(this);
        midReview.setOnClickListener(this);
        approve.setOnClickListener(this);
        reject.setOnClickListener(this);
        submit.setOnClickListener(this);
        modify.setOnClickListener(this);
        delete.setOnClickListener(this);
        upload.setOnClickListener(this);
        save.setOnClickListener(this);

        DividerItemDecoration mDivider = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        member_list.addItemDecoration(mDivider);
        member_list.setLayoutManager(new GridLayoutManager(this,1));
        memberListAdapter = new MemberListAdapter(this);
        //member_list.setVisibility(View.GONE); //复用至上传一个全新项目时默认没有成员，取消此注释
        //在此处将项目成员放入studentList
        studentList = projectDetail.getStudentList();
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
        }else {
            //否则，在这里设置竖屏的UI
            Log.println(Log.DEBUG,"ProMessageActivity","onConfigurationChanged-Portrait");
            scrollViewParams = scrollView.getLayoutParams();
            scrollViewParams.width=ViewGroup.LayoutParams.MATCH_PARENT;
        }
        scrollView.setLayoutParams(scrollViewParams);
        super.onConfigurationChanged(newConfig);
    }

    public void initData() {
        stu_sid.setText(projectDetail.getSid());
        stu_name.setText(projectDetail.getRealName());
        stu_phone.setText(projectDetail.getPhone());
        name.setText(projectDetail.getName());

        subject.setText(projectDetail.getSubject());
        budget.setText(""+projectDetail.getBudget());
        economic_analysis.setText(projectDetail.getEconomicAnalysis());
        purpose.setText(projectDetail.getPurpose());
        viable_analysis.setText(projectDetail.getViableAnalysis());

        setSpinnerData(level,projectDetail.getLevel());
        setSpinnerData(achievement_type, projectDetail.getAchievementType());
        submit.setText("更改");
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
            case R.id.detail_FinalCheck_btn:

                break;
            case R.id.detail_midReviewCheck_btn:

                break;
            case R.id.detail_approve_btn:
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        Project project=projectDao.getProject(projectDetail.getPid());
                        //教师审核
                        if(queryType==1){
                            project.setState(4);
                            projectDetail.setState(4);

                        }else{
                            project.setState(6);
                            projectDetail.setState(6);
                        }
                        projectDao.updateProject(project);
                    }
                }.start();
                break;
            case R.id.detail_reject_btn:
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        Project project=projectDao.getProject(projectDetail.getPid());
                        //教师审核
                        if(queryType==1){
                            project.setState(3);
                            projectDetail.setState(3);
                        }else{
                            project.setState(5);
                            projectDetail.setState(5);
                        }
                        projectDao.updateProject(project);
                    }
                }.start();
            case R.id.detail_submit_btn:

                break;
            case R.id.detail_modify_btn:

                break;
            case R.id.detail_delete_btn:

                break;
            case R.id.detail_upload_btn:

                break;
            case R.id.detail_save_btn:

                break;
            default:
                break;
        }
    }
}
