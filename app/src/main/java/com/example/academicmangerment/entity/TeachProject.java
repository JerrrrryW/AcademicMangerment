package com.example.academicmangerment.entity;


import androidx.annotation.NonNull;
import androidx.room.*;

@Entity(primaryKeys = {"tid","pid"})
public class TeachProject {
    @NonNull
    public String tid;
    @NonNull
    public String pid;
    public String states;
}
