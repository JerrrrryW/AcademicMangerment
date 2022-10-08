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

}
