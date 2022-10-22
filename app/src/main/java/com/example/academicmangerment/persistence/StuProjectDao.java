package com.example.academicmangerment.persistence;

import androidx.room.*;
import androidx.room.Insert;

import com.example.academicmangerment.entity.StuProject;

import java.util.List;

@Dao
public interface StuProjectDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertStuProjects(List<StuProject> stuProjects);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertStuProject(StuProject stuProject);
}
