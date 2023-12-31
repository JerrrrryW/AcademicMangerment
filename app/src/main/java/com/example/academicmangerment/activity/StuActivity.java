package com.example.academicmangerment.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.slidingpanelayout.widget.SlidingPaneLayout;
import androidx.room.Room;

import android.content.res.Configuration;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import com.example.academicmangerment.R;
import com.example.academicmangerment.custom.CusSlidingPaneLayout;

import com.example.academicmangerment.entity.Project;
import com.example.academicmangerment.entity.Student;
import com.example.academicmangerment.fragment.Stu02;
import com.example.academicmangerment.fragment.Stu03;
import com.example.academicmangerment.fragment.Stu04;
import com.example.academicmangerment.persistence.AppDatabase;
import com.example.academicmangerment.persistence.ProjectDao;
import com.example.academicmangerment.persistence.StudentDao;

import java.util.ArrayList;
import java.util.List;

public class StuActivity extends AppCompatActivity implements View.OnClickListener{
    private StudentDao studentDao;

    private ProjectDao projectDao;
    public List<Project> projectList = new ArrayList<>();
    public Student student;
    private AppDatabase db;
    private FrameLayout frameLayout;
    private Fragment stu02,stu03,stu04;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private LinearLayout llbtn1, llbtn2, llbtn3, llbtn4;
    private TextView name;
    private TextView college;
    private static final String TAG = "StuActivity";
    private CusSlidingPaneLayout mSlidingPaneLayout;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu);
        //接收登录时传递的学生信息
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        student=(Student) bundle.getSerializable("student");
        initView();
        initSlidingPaneLayout();
        initFragment();
        id=getIntent().getIntExtra("id",0);
        if(id==1){
            getSupportFragmentManager().beginTransaction().replace(R.id.stu_fragments, Stu02.newInstance(student))
                    .addToBackStack(null).commit();
        }
        /*test();*/
        /*数据库测试*/
       /* test();*/
    }

    private void initView() {
        ImageView head = findViewById(R.id.img_head);
        Glide.with(getBaseContext()).load(R.mipmap.test).into(head);
        mSlidingPaneLayout = findViewById(R.id.slide_layout);
        ImageView btn = findViewById(R.id.btn_pop);
        llbtn1=findViewById(R.id.profile_btn);
        llbtn2=findViewById(R.id.submit_btn);
        llbtn3=findViewById(R.id.management_btn);
        llbtn4=findViewById(R.id.logout_btn);

        btn.setOnClickListener(this);
        llbtn1.setOnClickListener(this);
        llbtn2.setOnClickListener(this);
        llbtn3.setOnClickListener(this);
        llbtn4.setOnClickListener(this);


        name=findViewById(R.id.username);
        college=findViewById(R.id.college);
        name.setText(student.getRealName());
        college.setText(student.getCollege());
//        mSlidingPaneLayout.forbidSlide(false);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(mSlidingPaneLayout.isOpen()){
//                    mSlidingPaneLayout.closePane();
//                }else{
//                    mSlidingPaneLayout.openPane();
//                }
//            }
//        });
    }
    private void initFragment() {
        //获得FragmentManager对象
        fragmentManager = getSupportFragmentManager();
        //开启事务，获得FragmentTransaction对象
        transaction = fragmentManager.beginTransaction();
        //创建需要添加的Fragment
        stu02 = Stu02.newInstance(student);
        stu03 = Stu03.newInstance(student);
        stu04 = Stu04.newInstance(student);
        //向容器内添加或替换碎片，默认情况下为个人信息管理模块
        transaction.replace(R.id.stu_fragments,stu02);
        //提交事务
        transaction.commit();

    }

    private void initSlidingPaneLayout() {//初始化侧滑面板
        final LinearLayout container = findViewById(R.id.stu_container);
        final View leftView = mSlidingPaneLayout.getChildAt(0);
        mSlidingPaneLayout.setPanelSlideListener(new SlidingPaneLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(@NonNull View panel, float slideOffset) {
                //设置侧面栏缩放
                leftView.setPivotX(-leftView.getWidth() / 6.0f);
                leftView.setPivotY(leftView.getHeight() / 2.0f);
                leftView.setScaleX(0.7f + 0.3f * slideOffset);
                leftView.setScaleY(0.7f + 0.3f * slideOffset);

                //设置首页滑动时缩放
                container.setScaleX(1f - 0.1f * slideOffset);
                container.setScaleY(1f - 0.1f * slideOffset);
                container.setElevation(10.0f * slideOffset);
            }

            @Override
            public void onPanelOpened(@NonNull View panel) {

            }

            @Override
            public void onPanelClosed(@NonNull View panel) {

            }
        });

        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mSlidingPaneLayout.isOpen()){
                    mSlidingPaneLayout.closePane();
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        transaction=fragmentManager.beginTransaction();
        switch (v.getId()){
            case R.id.profile_btn:
                transaction.replace(R.id.stu_fragments,stu02);
                transaction.addToBackStack(null);
                transaction.commit();
                //Toast.makeText(this,"Stu02 clicked！",Toast.LENGTH_SHORT).show();
                break;
            case R.id.submit_btn:
                transaction.replace(R.id.stu_fragments,stu03);
                transaction.commit();
                //Toast.makeText(this,"Stu03 clicked！",Toast.LENGTH_SHORT).show();
                break;
            case R.id.management_btn:
                transaction.replace(R.id.stu_fragments,stu04);
                transaction.commit();
                //Toast.makeText(this,"Stu04 clicked！",Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout_btn:
                //跳回登陆界面
                Toast.makeText(this,"登出成功！",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(StuActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_pop:
                if(mSlidingPaneLayout.isOpen()){
                    mSlidingPaneLayout.closePane();
                }else{
                    mSlidingPaneLayout.openPane();
                }
                break;
            default:
                break;
        }
    }


}