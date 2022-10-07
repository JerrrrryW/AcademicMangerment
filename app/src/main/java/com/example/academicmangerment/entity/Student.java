package com.example.academicmangerment.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Student {
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
}
