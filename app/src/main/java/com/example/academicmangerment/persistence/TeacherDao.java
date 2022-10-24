package com.example.academicmangerment.persistence;

import androidx.room.*;
import com.example.academicmangerment.entity.Teacher;

import java.util.List;

@Dao
public interface TeacherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Teacher teacher);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTeachers(List<Teacher> teachers);
    @Query("select * from teacher")
    List<Teacher> loadAllTeacher();
    @Query("select * from teacher where tid=:tid")
    Teacher getTeacher(String tid);
    @Update
    void updateTeacher(Teacher teacher);
}
