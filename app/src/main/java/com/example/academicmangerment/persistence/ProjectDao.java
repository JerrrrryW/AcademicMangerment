package com.example.academicmangerment.persistence;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.academicmangerment.entity.Project;

import java.util.List;

@Dao
public interface ProjectDao {
    @Insert
    void insert(Project project);
    @Insert
    void insertProject(List<Project> projects);
    @Query("select * from project")
    List<Project> loadAllProject();
}
