package com.example.academicmangerment.persistence;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.academicmangerment.entity.Project;
import com.example.academicmangerment.entity.ProjectDetail;

import java.util.List;
@Dao
public interface ProjectDao {
    @Insert
    void insert(Project project);
    @Insert
    void insertProject(List<Project> projects);
    @Query("select * from project")
    List<Project> loadAllProject();
/*    @Query("SELECT * " +
            "FROM project, stuproject,student " +
            "WHERE project.pid=stuproject.pid " +
            "AND stuproject.sid= :stu_sid")
    public List<Project> StuProjects(String stu_sid);*/

    //根据学号查询项目详细信息
    @Query("select project.pid,project.achievementType,project.name,project.level,project.budget,project.userName,project.state,teacher.tecName,student.college,student.phone as userPhone" +
            " from project,teacher,student,stuproject,teachproject" +
            " where stuproject.sid=:sid and stuproject.pid=project.pid " +
            "and project.pid=teachproject.pid and teachproject.tid=teacher.tid and stuproject.sid=student.sid")
    List<ProjectDetail> getProjectDetail(String sid);

    //根据项目号找成员
    @Query("select student.realName from student,stuproject where stuproject.pid=:pid " +
            "and stuproject.sid=student.sid and stuproject.rank='2'")
    String[] getMembers(String pid);
}