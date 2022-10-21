package com.example.academicmangerment.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.academicmangerment.R;
import com.example.academicmangerment.entity.Project;
import com.example.academicmangerment.fragment.Stu04;

public class ProMessageActivity extends AppCompatActivity /*implements Stu04.SendProject*/{
    Project project;//接收Stu04的数据
    private EditText stu_sid,stu_name,stu_phone,stu_member;
    private EditText name;
    Spinner level,achievement_type;
    private EditText subject,budget,economic_analysis,purpose,viable_analysis;
    private Button submit;
    private ScrollView scrollView;
    private ViewGroup.LayoutParams scrollViewParams;

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
        stu_member = (EditText) findViewById(R.id.stu_member);
        name = (EditText) findViewById(R.id.name);
        level = (Spinner) findViewById(R.id.level);
        achievement_type = (Spinner) findViewById(R.id.achievement_type);
        subject = (EditText) findViewById(R.id.subject);
        budget = (EditText) findViewById(R.id.budget);
        economic_analysis = (EditText) findViewById(R.id.economic_analysis);
        purpose = (EditText) findViewById(R.id.purpose);
        viable_analysis = (EditText) findViewById(R.id.viable_analysis);
        submit = (Button) findViewById(R.id.submit);
        scrollView = (ScrollView) findViewById(R.id.scrollView1);
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
        String pid;
        Intent intent = getIntent();
        pid = intent.getStringExtra("Pid");//用于数据库中查询相应Project
        String Name = intent.getStringExtra("Name");
        name.setText(Name);
        name.setKeyListener(null);
    }
}
