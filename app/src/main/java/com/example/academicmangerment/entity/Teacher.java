package com.example.academicmangerment.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Teacher {
    @PrimaryKey
    public String tid;
    public String realName;
    public String password;
    public String tel;
    public String email;
}
