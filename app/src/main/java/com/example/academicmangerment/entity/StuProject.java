package com.example.academicmangerment.entity;


import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = {"sid","pid"})
public class StuProject {
    @NonNull
    public String sid;
    @NonNull
    public String pid;
    public String rank;
}
