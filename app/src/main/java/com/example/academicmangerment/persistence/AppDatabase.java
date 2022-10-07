package com.example.academicmangerment.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.academicmangerment.entity.*;

@Database(entities = {Student.class, Teacher.class, Project.class, StuProject.class, TeachProject.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract StudentDao studentDao();
    public abstract TeacherDao teacherDao();
    public abstract ProjectDao projectDao();
}
