package com.example.academicmangerment.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(primaryKeys = {"sid","pid"})
public class StuProject {
    public String sid;
    public String pid;
    public String rank;
}
