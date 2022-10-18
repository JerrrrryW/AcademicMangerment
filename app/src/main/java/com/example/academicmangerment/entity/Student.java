package com.example.academicmangerment.entity;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Student {
    @NonNull
    @PrimaryKey
    public String sid;
    public String card;
    public String createTime;
    public String password;
    public String phone;
    public String realName;
    public String state;
    public String type;
    public String college;
    public String birthday;
    public String degree;
    public String nation;
    public int sex;
    public String email;
    public Student(){

    }
    @Ignore
    public Student(String sid, String card, String createTime, String password, String phone, String realName, String state, String type, String college, String birthday, String degree, String nation, int sex, String email) {
        this.sid = sid;
        this.card = card;
        this.createTime = createTime;
        this.password = password;
        this.phone = phone;
        this.realName = realName;
        this.state = state;
        this.type = type;
        this.college = college;
        this.birthday = birthday;
        this.degree = degree;
        this.nation = nation;
        this.sex = sex;
        this.email = email;
    }

    @NonNull
    public String getSid() {
        return sid;
    }

    public void setSid(@NonNull String sid) {
        this.sid = sid;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
