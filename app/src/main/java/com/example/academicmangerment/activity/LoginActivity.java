package com.example.academicmangerment.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.academicmangerment.R;
import com.example.academicmangerment.entity.Student;
import com.example.academicmangerment.entity.Teacher;
import com.example.academicmangerment.persistence.AppDatabase;
import com.example.academicmangerment.persistence.StudentDao;
import com.example.academicmangerment.persistence.TeacherDao;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private Button stu_btn_enter;
    private Button tec_btn_enter;
    private Button adm_btn_enter;
    private EditText username;
    private EditText password;

    private StudentDao studentDao;
    private TeacherDao teacherDao;
    private AppDatabase db;

    private List<Student> studentList;
    private List<Teacher> teacherList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        readDataBase();
    }

    public void initView() {
        stu_btn_enter = (Button) findViewById(R.id.stu_btn_enter);
        tec_btn_enter = (Button) findViewById(R.id.tec_btn_enter);
        adm_btn_enter = (Button) findViewById(R.id.adm_btn_enter);
        username = findViewById(R.id.et_name);
        password = findViewById(R.id.et_pwd);
        login();
    }


    public void login() {
        //学生登录
        stu_btn_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sid = username.getText().toString().trim();
                String pwd = password.getText().toString().trim();
                if (!TextUtils.isEmpty(sid) && !TextUtils.isEmpty(pwd)) {

                    boolean user = false;
                    Student userdata = null;
                    for (int i = 0; i < studentList.size(); i++) {
                        userdata = studentList.get(i);
                        if (sid.equals(userdata.getSid()) && pwd.equals(userdata.getPassword())) {
                            user = true;
                            break;
                        } else {
                            user = false;
                        }
                    }
                    if (user) {
                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        Intent intent_l = new Intent(LoginActivity.this, StuActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("student", userdata);
                        intent_l.putExtras(bundle);
                        startActivity(intent_l);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "用户名或密码错误！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "用户名或密码不能为空！", Toast.LENGTH_SHORT).show();
                }

                /*Intent intent=new Intent(LoginActivity.this,StuActivity.class);
                startActivity(intent);*/
            }
        });

        //教师登录
        tec_btn_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sid = username.getText().toString().trim();
                String pwd = password.getText().toString().trim();
                if (!TextUtils.isEmpty(sid) && !TextUtils.isEmpty(pwd)) {

                    boolean user = false;
                    Teacher userdata = null;
                    for (int i = 0; i < studentList.size(); i++) {
                        userdata = teacherList.get(i);
                        if (sid.equals(userdata.getTid()) && pwd.equals(userdata.getPassword())) {
                            user = true;
                            break;
                        } else {
                            user = false;
                        }
                    }
                    if (user) {
                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        Intent intent_l = new Intent(LoginActivity.this, TecActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("teacher", userdata);
                        /*System.out.println(userdata);*/
                        intent_l.putExtras(bundle);
                        startActivity(intent_l);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "用户名或密码错误！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "用户名或密码不能为空！", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //管理员登录
        adm_btn_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sid = username.getText().toString().trim();
                String pwd = password.getText().toString().trim();
                if (!TextUtils.isEmpty(sid) && !TextUtils.isEmpty(pwd)) {
                    boolean user = false;
                    if (sid.equals("admin") && pwd.equals("admin")) {
                        user = true;
                    } else {
                        user = false;
                    }
                    if (user) {
                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        Intent intent_l = new Intent(LoginActivity.this, AdminActivity.class);
                        startActivity(intent_l);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "管理员用户名或密码错误！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "管理员用户名或密码不能为空！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void readDataBase() {
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "dataBase").build();
        teacherDao = db.teacherDao();
        studentDao = db.studentDao();
        new Thread(() -> {
            studentList = studentDao.loadAllStudent();
            teacherList = teacherDao.loadAllTeacher();
        }).start();
    }
}