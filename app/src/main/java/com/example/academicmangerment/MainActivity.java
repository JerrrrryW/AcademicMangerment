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
import com.example.academicmangerment.entity.StuProject;
import com.example.academicmangerment.entity.Student;
import com.example.academicmangerment.entity.TeachProject;
import com.example.academicmangerment.entity.Teacher;
import com.example.academicmangerment.persistence.AppDatabase;
import com.example.academicmangerment.persistence.ProjectDao;
import com.example.academicmangerment.persistence.StuProjectDao;
import com.example.academicmangerment.persistence.StudentDao;
import com.example.academicmangerment.persistence.TeachProjectDao;
import com.example.academicmangerment.persistence.TeacherDao;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private StudentDao studentDao;
    private TeacherDao teacherDao;
    private ProjectDao projectDao;
    private StuProjectDao stuProjectDao;
    private TeachProjectDao teachProjectDao;
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
    new Thread(){
        @Override
        public void run() {
            super.run();
            db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,"dataBase").build();
            Student student1=new Student("2220192757","1055689557888","2019/09/01","123456","13332284652","熊迪","无","学生","大连海事大学","09/22","本科生","中国",1,"2220192757xd@dlmu.edu.cn");
            Student student2=new Student("2220192470","1055689557889","2019/09/02","123456","13332284653","潘建树","无","学生","大连海事大学","09/23","本科生","中国",1,"2220192470pjs@dlmu.edu.cn");
            Student student3=new Student("2220192480","1055689557890","2019/09/03","123456","13332284654","汪睿涵","无","专业学委","大连海事大学","09/24","本科生","中国",1,"2220192480wrh@dlmu.edu.cn");
            Student student4=new Student("2220192490","1055689557898","2019/09/04","123456","13332284655","黄俊凯","无","学生","大连海事大学","09/25","本科生","中国",1,"2220192490hjk@dlmu.edu.cn");
            List<Student> studentList=new ArrayList<>();
            studentList.add(student1);
            studentList.add(student2);
            studentList.add(student3);
            studentList.add(student4);

            Teacher teacher1=new Teacher("19880001","张秀国","12345678","402-12356","DMUzxg@163.com");
            Teacher teacher2=new Teacher("19880002","张德珍","12345678","403-12356","DMUzdz@163.com");
            Teacher teacher3=new Teacher("19880003","赵正","12345678","404-12356","DMUzz@163.com");
            List<Teacher> teacherList=new ArrayList<>();
            teacherList.add(teacher1);
            teacherList.add(teacher2);
            teacherList.add(teacher3);

            Project project1=new Project("202210151987","论文","2021/11/01","2022/10/11",10,"大连海事大学","熊迪",2000,"无","无","论文一项","国家级","疫情舆情可视化","国家级","计算机","熊迪","无");
            Project project2=new Project("202210151988","毕业设计","2021/11/02","2022/10/12",1,"大连海事大学","潘建树",3000,"无","无","国家级项目","国家级","智安随行","国家级","计算机","潘建树","无");
            Project project3=new Project("202210151989","竞赛","2021/11/03","2022/10/13",1,"大连海事大学","汪睿涵",4000,"无","无","国家级一等奖","国家级","智慧停车","国家级","计算机","汪睿涵","无");
            Project project4=new Project("202210151990","项目","2021/11/04","2022/10/14",1,"大连海事大学","黄俊凯",5000,"无","无","国家级项目","国家级","Dilidili","国家级","计算机","黄俊凯","无");
            List<Project> projectList=new ArrayList<>();
            projectList.add(project1);
            projectList.add(project2);
            projectList.add(project3);
            projectList.add(project4);

            StuProject stuProject1=new StuProject("2220192757","202210151987","1");
            StuProject stuProject8=new StuProject("2220192480","202210151987","2");
            StuProject stuProject9=new StuProject("2220192490","202210151987","2");
            StuProject stuProject2=new StuProject("2220192757","202210151988","2");
            StuProject stuProject3=new StuProject("2220192757","202210151989","2");
            StuProject stuProject4=new StuProject("2220192757","202210151990","2");
            StuProject stuProject5=new StuProject("2220192470","202210151988","1");
            StuProject stuProject6=new StuProject("2220192480","202210151989","1");
            StuProject stuProject7=new StuProject("2220192490","202210151990","1");
            List<StuProject> stuProjectList=new ArrayList<>();
            stuProjectList.add(stuProject1);
            stuProjectList.add(stuProject2);
            stuProjectList.add(stuProject3);
            stuProjectList.add(stuProject4);
            stuProjectList.add(stuProject5);
            stuProjectList.add(stuProject6);
            stuProjectList.add(stuProject7);
            stuProjectList.add(stuProject8);
            stuProjectList.add(stuProject9);

            TeachProject teachProject1=new TeachProject("19880001","202210151987","0");
            TeachProject teachProject2=new TeachProject("19880002","202210151988","0");
            TeachProject teachProject3=new TeachProject("19880003","202210151989","0");
            TeachProject teachProject4=new TeachProject("19880001","202210151990","0");
            List<TeachProject> teachProjectList=new ArrayList<>();
            teachProjectList.add(teachProject1);
            teachProjectList.add(teachProject2);
            teachProjectList.add(teachProject3);
            teachProjectList.add(teachProject4);

            studentDao=db.studentDao();
            teacherDao=db.teacherDao();
            projectDao=db.projectDao();
            stuProjectDao=db.stuProjectDao();
            teachProjectDao=db.teachProjectDao();

            studentDao.insertStudents(studentList);
            teacherDao.insertTeachers(teacherList);
            projectDao.insertProjects(projectList);
            stuProjectDao.insertStuProjects(stuProjectList);
            teachProjectDao.insertTeachPros(teachProjectList);

            System.out.println("成功插入");
//            db.close();
        }
    }.start();
    }

}