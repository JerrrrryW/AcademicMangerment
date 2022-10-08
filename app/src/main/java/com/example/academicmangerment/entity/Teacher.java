package com.example.academicmangerment.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Teacher {
    @NonNull
    @PrimaryKey
    public String tid;
    public String realName;
    public String password;
    public String tel;
    public String email;
}
