package com.example.academicmangerment.persistence;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.academicmangerment.entity.Project;
import com.example.academicmangerment.entity.ProjectDetail;
import com.example.academicmangerment.entity.Student;

import java.util.List;
@Dao
public interface ProjectDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProject(Project project);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProjects(List<Project> projects);
    @Query("select * from project")
    List<Project> loadAllProject();
/*    @Query("SELECT * " +
            "FROM project, stuproject,student " +
            "WHERE project.pid=stuproject.pid " +
            "AND stuproject.sid= :stu_sid")
    public List<Project> StuProjects(String stu_sid);*/

    //根据学号查询项目详细信息
    @Query("select project.pid,project.achievementType,project.name,project.level,project.budget," +
            "project.subject,project.economicAnalysis,project.purpose,project.viableAnalysis," +
            "project.state,teacher.tecName,student.college" +
            " from project,teacher,student,stuproject,teachproject" +
            " where stuproject.sid=:sid and stuproject.pid=project.pid " +
            "and project.pid=teachproject.pid and teachproject.tid=teacher.tid " +
            "and stuproject.sid=student.sid")
    List<ProjectDetail> getProjectDetail(String sid);

    //根据项目号找成员
    @Query("select student.realName from student,stuproject where stuproject.pid=:pid " +
            "and stuproject.sid=student.sid and stuproject.rank='2'")
    String[] getMembers(String pid);

    @Query("select student.* from student,stuproject where stuproject.pid=:pid " +
            "and stuproject.rank='2' and stuproject.sid=student.sid")
    List<Student> getMemberStudents(String pid);

    //根据项目号找队长姓名和电话学号
    @Query("select student.realName from student,stuproject where stuproject.pid=:pid " +
            "and stuproject.rank='1' and stuproject.sid=student.sid")
    String getLeader(String pid);
    @Query("select student.phone from student,stuproject where stuproject.pid=:pid " +
            "and stuproject.rank='1' and stuproject.sid=student.sid")
    String getLeaderPhone(String pid);
    @Query("select student.sid from student,stuproject where stuproject.pid=:pid " +
            "and stuproject.rank='1' and stuproject.sid=student.sid")
    String getLeaderSid(String pid);

    @Query("select distinct project.pid,project.achievementType,project.name,project.level,project.budget," +
            "project.subject,project.economicAnalysis,project.purpose,project.viableAnalysis," +
            "project.state,teacher.tecName,student.college" +
            " from project,teacher,student,stuproject,teachproject" +
            " where stuproject.pid=project.pid " +
            "and project.pid=teachproject.pid and teachproject.tid=teacher.tid " +
            "and stuproject.sid=student.sid")
    List<ProjectDetail> getProjectDetail();
}