package com.example.academicmangerment;

import org.junit.Test;

import static org.junit.Assert.*;

import android.arch.persistence.room.Room;
import android.content.Context;

//import androidx.test.core.app.ApplicationProvider;

import com.example.academicmangerment.entity.Student;
import com.example.academicmangerment.persistence.AppDatabase;
import com.example.academicmangerment.persistence.StudentDao;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private StudentDao studentDao;
    private AppDatabase db;
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
//        Context context = ApplicationProvider.getApplicationContext();
//        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
//        Student student=new Student("2220192757","1055689557888","2019/09/01","123456","18307050360","熊迪","无","本科生","大连海事大学","09/22","本科生","China",1,"25567.9932@qq.com");
//
//        studentDao=db.studentDao();
//        studentDao.insert(student);
//
//        db.close();
    }
}