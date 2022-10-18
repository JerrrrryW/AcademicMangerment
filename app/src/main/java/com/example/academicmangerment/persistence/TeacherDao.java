package com.example.academicmangerment.persistence;

import androidx.room.*;
import com.example.academicmangerment.entity.Teacher;

import java.util.List;

@Dao
public interface TeacherDao {
    @Insert
    void insert(Teacher teacher);
    @Insert
    void insertTeachers(List<Teacher> teachers);
    @Query("select * from teacher")
    List<Teacher> loadAllTeacher();
}
