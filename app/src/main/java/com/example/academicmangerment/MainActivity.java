package com.example.academicmangerment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;

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
//        test();
    }
    public void test(){
        db = Room.inMemoryDatabaseBuilder(getApplicationContext(), AppDatabase.class).build();
        Student student=new Student("2220192757","1055689557888","2019/09/01","123456","18307050360","熊迪","无","本科生","大连海事大学","09/22","本科生","China",1,"25567.9932@qq.com");

        studentDao=db.studentDao();
        studentDao.insert(student);

        db.close();
    }
}