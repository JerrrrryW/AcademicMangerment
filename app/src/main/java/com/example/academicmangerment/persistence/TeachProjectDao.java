package com.example.academicmangerment.persistence;

import androidx.room.*;

import com.example.academicmangerment.entity.TeachProject;

import java.util.List;

@Dao
public interface TeachProjectDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTeachPros(List<TeachProject> teachProjects);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTeachPro(TeachProject teachProject);
}
