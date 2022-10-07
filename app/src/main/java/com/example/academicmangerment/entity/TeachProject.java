package com.example.academicmangerment.entity;

import android.arch.persistence.room.Entity;

@Entity(primaryKeys = {"tid","pid"})
public class TeachProject {
    public String tid;
    public String pid;
    public String states;
}
