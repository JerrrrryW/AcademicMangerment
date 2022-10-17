package com.example.academicmangerment.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.academicmangerment.MainActivity;
import com.example.academicmangerment.R;
import com.example.academicmangerment.entity.Student;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    private Button btn_enter;
    private EditText username;
    private  EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }
    public void initView(){
        btn_enter = (Button) findViewById(R.id.btn_enter);
        username = findViewById(R.id.et_name);
        password = findViewById(R.id.et_pwd);
    }

    public void login(){
        btn_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString().trim();
                String password = password.getText().toString().trim();
                if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)){
                    ArrayList<Student> data = mSQlite.getAllDATA();
                    boolean user = false;
                    for(int i = 0; i < data.size(); i++){
                        Student userdata = data.get(i);
                        if(name.equals(userdata.getName()) && password.equals(userdata.getPassword())){
                            user = true;
                            break;
                        } else{
                            user = false;
                        }
                    }
                    if(user){
                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        Intent intent_l = new Intent(LoginActivity.this, MainActivity.class);
                        intent_l.putExtra("username", username.getText().toString());
                        intent_l.putExtra("password", password);
                        startActivity(intent_l);
                        finish();
                    } else{
                        Toast.makeText(LoginActivity.this, "用户名或密码错误！", Toast.LENGTH_SHORT).show();
                    }
                } else{
                    Toast.makeText(LoginActivity.this, "用户名或密码不能为空！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}