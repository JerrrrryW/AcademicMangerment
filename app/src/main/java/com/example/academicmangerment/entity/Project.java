package com.example.academicmangerment.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity
public class Project {
    @PrimaryKey
    public String pid;
    public String achievementType;
    public Date beginTime;
    public Date endTime;
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
