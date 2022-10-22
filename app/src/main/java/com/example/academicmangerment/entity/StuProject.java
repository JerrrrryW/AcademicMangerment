package com.example.academicmangerment.entity;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;

@Entity(primaryKeys = {"sid","pid"})
public class StuProject {
    @NonNull
    public String sid;
    @NonNull
    public String pid;
    public String rank;

    public StuProject() {
    }
    @Ignore
    public StuProject(@NonNull String sid, @NonNull String pid, String rank) {
        this.sid = sid;
        this.pid = pid;
        this.rank = rank;
    }

    @NonNull
    public String getSid() {
        return sid;
    }

    public void setSid(@NonNull String sid) {
        this.sid = sid;
    }

    @NonNull
    public String getPid() {
        return pid;
    }

    public void setPid(@NonNull String pid) {
        this.pid = pid;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
