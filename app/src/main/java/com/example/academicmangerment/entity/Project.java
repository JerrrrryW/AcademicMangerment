package com.example.academicmangerment.entity;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Project {
    @NonNull
    @PrimaryKey
    public String pid;
    public String achievementType;
    public String beginTime;
    public String endTime;
    public int state;
    public String college;
    public String createUser;
    public double budget;
    public String economicAnalysis;
    public String existingCondition;
    public String expectResult;
    public String level;
    public String name;
    public String purpose;
    public String subject;
    public String userName;
    public String viableAnalysis;

}
