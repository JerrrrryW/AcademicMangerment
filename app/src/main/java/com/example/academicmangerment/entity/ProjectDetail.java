package com.example.academicmangerment.entity;

import androidx.room.Ignore;

import java.io.Serializable;
import java.util.List;

public class ProjectDetail implements Serializable {
    String pid;
    String achievementType;
    String name;
    String level;
    double budget;

    //新增属性
    @Ignore
    String phone;
    @Ignore
    String sid;
    @Ignore
    String realName;
    int state;
    //成员姓名列表
    @Ignore
    String[] members;

    String tecName;

    String college;
    String subject;
    String economicAnalysis;

    String existingCondition;
    String purpose;
    String viableAnalysis;
    @Ignore
    List<Student> studentList;
    public ProjectDetail() {
    }

    public String strMember(){
        StringBuffer stringBuffer=new StringBuffer();
        for(String c:members){
            stringBuffer.append(c+" ");
        }
        return stringBuffer.toString();
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEconomicAnalysis() {
        return economicAnalysis;
    }

    public void setEconomicAnalysis(String economicAnalysis) {
        this.economicAnalysis = economicAnalysis;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getViableAnalysis() {
        return viableAnalysis;
    }

    public void setViableAnalysis(String viableAnalysis) {
        this.viableAnalysis = viableAnalysis;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getAchievementType() {
        return achievementType;
    }

    public void setAchievementType(String achievementType) {
        this.achievementType = achievementType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String[] getMembers() {
        return members;
    }

    public void setMembers(String[] members) {
        this.members = members;
    }

    public String getTecName() {
        return tecName;
    }

    public void setTecName(String tecName) {
        this.tecName = tecName;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getExistingCondition() {
        return existingCondition;
    }

    public void setExistingCondition(String existingCondition) {
        this.existingCondition = existingCondition;
    }
}
