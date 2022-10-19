package com.example.academicmangerment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;

import com.example.academicmangerment.activity.LoginActivity;
import com.example.academicmangerment.activity.StuActivity;
import com.example.academicmangerment.entity.Student;
import com.example.academicmangerment.persistence.AppDatabase;
import com.example.academicmangerment.persistence.StudentDao;

public class MainActivity extends AppCompatActivity {
    private StudentDao studentDao;
    private AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //跳转至登录界面
        Intent intent=new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        test();
    }
    public void test(){
        //需要在线程中访问数据库
       /* new Thread(()->{
            db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,"dataBase").build();

            db.close();
        }).start();*/
    }
}