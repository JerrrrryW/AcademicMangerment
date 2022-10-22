package com.example.academicmangerment.persistence;

import androidx.room.*;
import com.example.academicmangerment.entity.Student;

import java.util.List;

@Dao
public interface StudentDao {
    //新增单个学生
    @Insert(onConflict = OnConflictStrategy.REPLACE)  // or OnConflictStrategy.IGNORE
    void insert(Student student);
    @Insert
    void insertStudents(List<Student> students);
    //查询所有学生
    @Query("select * from student")
    List<Student> loadAllStudent();

    //根据学号修改学生信息
    @Update
    void updateStudent(Student student);

}
