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

    public TeachProject() {
    }
    @Ignore
    public TeachProject(@NonNull String tid, @NonNull String pid, String states) {
        this.tid = tid;
        this.pid = pid;
        this.states = states;
    }

    @NonNull
    public String getTid() {
        return tid;
    }

    public void setTid(@NonNull String tid) {
        this.tid = tid;
    }

    @NonNull
    public String getPid() {
        return pid;
    }

    public void setPid(@NonNull String pid) {
        this.pid = pid;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }
}
