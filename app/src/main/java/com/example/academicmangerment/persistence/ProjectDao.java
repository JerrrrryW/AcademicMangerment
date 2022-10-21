package com.example.academicmangerment.persistence;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

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
    @Query("SELECT * " +
            "FROM project, stuproject,student " +
            "WHERE project.pid=stuproject.pid " +
            "AND stuproject.sid= :stu_sid")
    public List<Project> StuProjects(String stu_sid);
}