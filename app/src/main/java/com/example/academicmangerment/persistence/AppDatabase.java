package com.example.academicmangerment.persistence;



import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.academicmangerment.entity.*;

@Database(entities = {Student.class, Teacher.class, Project.class, StuProject.class, TeachProject.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract StudentDao studentDao();
    public abstract TeacherDao teacherDao();
    public abstract ProjectDao projectDao();
    public abstract StuProjectDao stuProjectDao();
    public abstract TeachProjectDao teachProjectDao();
}
