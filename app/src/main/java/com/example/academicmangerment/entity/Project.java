package com.example.academicmangerment.entity;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Project implements Serializable {
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

    public Project() {
    }
    @Ignore
    public Project(@NonNull String pid, String achievementType, String beginTime, String endTime, int state, String college, String createUser, double budget, String economicAnalysis, String existingCondition, String expectResult, String level, String name, String purpose, String subject, String userName, String viableAnalysis) {
        this.pid = pid;
        this.achievementType = achievementType;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.state = state;
        this.college = college;
        this.createUser = createUser;
        this.budget = budget;
        this.economicAnalysis = economicAnalysis;
        this.existingCondition = existingCondition;
        this.expectResult = expectResult;
        this.level = level;
        this.name = name;
        this.purpose = purpose;
        this.subject = subject;
        this.userName = userName;
        this.viableAnalysis = viableAnalysis;
    }

    @NonNull
    public String getPid() {
        return pid;
    }

    public void setPid(@NonNull String pid) {
        this.pid = pid;
    }

    public String getAchievementType() {
        return achievementType;
    }

    public void setAchievementType(String achievementType) {
        this.achievementType = achievementType;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public String getEconomicAnalysis() {
        return economicAnalysis;
    }

    public void setEconomicAnalysis(String economicAnalysis) {
        this.economicAnalysis = economicAnalysis;
    }

    public String getExistingCondition() {
        return existingCondition;
    }

    public void setExistingCondition(String existingCondition) {
        this.existingCondition = existingCondition;
    }

    public String getExpectResult() {
        return expectResult;
    }

    public void setExpectResult(String expectResult) {
        this.expectResult = expectResult;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getViableAnalysis() {
        return viableAnalysis;
    }

    public void setViableAnalysis(String viableAnalysis) {
        this.viableAnalysis = viableAnalysis;
    }



}
