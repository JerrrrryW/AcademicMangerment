package com.example.academicmangerment.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.slidingpanelayout.widget.SlidingPaneLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.academicmangerment.R;
import com.example.academicmangerment.custom.CusSlidingPaneLayout;
import com.example.academicmangerment.fragment.Admin01;
import com.example.academicmangerment.fragment.Admin02;
import com.example.academicmangerment.fragment.Admin03;
import com.example.academicmangerment.fragment.Admin04;


public class AdminActivity extends AppCompatActivity implements View.OnClickListener{
    private Fragment admin01,admin02,admin03,admin04;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private LinearLayout llbtn1, llbtn2, llbtn3,llbtn4,llbtn5;

    private static final String TAG = "AdminActivity";
    private CusSlidingPaneLayout mSlidingPaneLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        initView();
        initSlidingPaneLayout();
        initFragment();
    }
    public void initView(){
        ImageView head = findViewById(R.id.adm_img_head);
        Glide.with(getBaseContext()).load(R.mipmap.test).into(head);
        mSlidingPaneLayout = findViewById(R.id.adm_slide_layout);
        ImageView btn = findViewById(R.id.adm_btn_pop);
        llbtn1=findViewById(R.id.stu_manger_btn);
        llbtn2=findViewById(R.id.tec_manger_btn);
        llbtn3=findViewById(R.id.project_manger_btn);
        llbtn4=findViewById(R.id.project_class_manger_btn);
        llbtn5=findViewById(R.id.adm_logout_btn);

        btn.setOnClickListener(this);
        llbtn1.setOnClickListener(this);
        llbtn2.setOnClickListener(this);
        llbtn3.setOnClickListener(this);
        llbtn4.setOnClickListener(this);
        llbtn5.setOnClickListener(this);

    }

    public void initFragment(){
        //获得FragmentManager对象
        fragmentManager = getSupportFragmentManager();
        //开启事务，获得FragmentTransaction对象
        transaction = fragmentManager.beginTransaction();
        //创建需要添加的Fragment
        admin01=new Admin01();
        admin02=new Admin02();
        admin03=new Admin03();
        admin04=new Admin04();
        //向容器内添加或替换碎片，默认情况下为个人信息管理模块
        transaction.replace(R.id.adm_fragments,admin01);
        //提交事务
        transaction.commit();
    }

    private void initSlidingPaneLayout() {//初始化侧滑面板
        final LinearLayout container = findViewById(R.id.adm_container);
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
            case R.id.stu_manger_btn:
                transaction.replace(R.id.adm_fragments,admin01);
                transaction.commit();
                //Toast.makeText(this,"Stu02 clicked！",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tec_manger_btn:
                transaction.replace(R.id.adm_fragments,admin02);
                transaction.commit();
                //Toast.makeText(this,"Stu03 clicked！",Toast.LENGTH_SHORT).show();
                break;
            case R.id.project_manger_btn:
                transaction.replace(R.id.adm_fragments,admin03);
                transaction.commit();
                //Toast.makeText(this,"Stu03 clicked！",Toast.LENGTH_SHORT).show();
                break;
            case R.id.project_class_manger_btn:
                transaction.replace(R.id.adm_fragments,admin04);
                transaction.commit();
                //Toast.makeText(this,"Stu03 clicked！",Toast.LENGTH_SHORT).show();
                break;
            case R.id.adm_logout_btn:
                //跳回登陆界面
                Toast.makeText(this,"登出成功！",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(AdminActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.adm_btn_pop:
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