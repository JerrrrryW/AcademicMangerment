package com.example.academicmangerment.persistence;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.academicmangerment.entity.Teacher;

import java.util.List;

@Dao
public interface TeacherDao {
    @Insert
    void insert(Teacher teacher);
    @Insert
    void insertTeachers(List<Teacher> teachers);
    @Query("select * from teacher")
    List<Teacher> loadAllStudent();
}
