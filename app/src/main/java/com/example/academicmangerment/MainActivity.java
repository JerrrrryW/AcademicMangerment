package com.example.academicmangerment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.ViewGroup;

import com.example.academicmangerment.activity.LoginActivity;
import com.example.academicmangerment.activity.StuActivity;
import com.example.academicmangerment.entity.Project;
import com.example.academicmangerment.entity.Student;
import com.example.academicmangerment.entity.Teacher;
import com.example.academicmangerment.persistence.AppDatabase;
import com.example.academicmangerment.persistence.ProjectDao;
import com.example.academicmangerment.persistence.StudentDao;
import com.example.academicmangerment.persistence.TeacherDao;

public class MainActivity extends AppCompatActivity {
    private StudentDao studentDao;
    private TeacherDao teacherDao;
    private ProjectDao projectDao;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //跳转至登录界面
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
//        test();
    }

/*    public void test() {
        //需要在线程中访问数据库
    new Thread(()->{
            db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,"dataBase").build();
            Student student=new Student("123","1055689557888","2019/09/01","123","13332284652","熊迪","无","本科生","大连海事大学","09/22","本科生","中国",1,"2220192757xd@dlmu.edu.cn");
            Teacher teacher=new Teacher("19880003","赵正","12345678","402-12356","DMUzz@163.com");
            Project project=new Project("202210151987","国家级","2021/11/01","2022/10/10",0,"大连海事大学","熊迪",2000,"无","无","论文一项","国家级","智安随行","国家级","计算机","熊迪","无");
            studentDao=db.studentDao();
            teacherDao=db.teacherDao();
            projectDao=db.projectDao();
            studentDao.insert(student);
            teacherDao.insert(teacher);
            projectDao.insert(project);
            System.out.println("成功插入");
//            db.close();
        }).start();
    }*/

}