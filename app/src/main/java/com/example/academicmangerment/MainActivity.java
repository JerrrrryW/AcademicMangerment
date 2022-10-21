package com.example.academicmangerment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;

import com.example.academicmangerment.activity.LoginActivity;
import com.example.academicmangerment.activity.StuActivity;
import com.example.academicmangerment.entity.Student;
import com.example.academicmangerment.entity.Teacher;
import com.example.academicmangerment.persistence.AppDatabase;
import com.example.academicmangerment.persistence.StudentDao;
import com.example.academicmangerment.persistence.TeacherDao;

public class MainActivity extends AppCompatActivity {
    private StudentDao studentDao;
    private TeacherDao teacherDao;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //跳转至登录界面
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        test();
    }

    public void test() {
        //需要在线程中访问数据库
    new Thread(()->{
            db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,"dataBase").build();
            Student student=new Student("2220192757","1055689557888","2019/09/01","123456","18307050360","熊迪","无","本科生","大连海事大学","09/22","本科生","China",1,"255679932@qq.com");
            Teacher teacher=new Teacher("19880001","张秀国","12345678","401-12356","DMUzxg@163.com");
            studentDao=db.studentDao();
            teacherDao=db.teacherDao();
            studentDao.insert(student);
            teacherDao.insert(teacher);
            System.out.println("成功插入");
//            db.close();
        }).start();
    }

}