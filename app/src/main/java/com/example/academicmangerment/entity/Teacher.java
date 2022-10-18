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

    @NonNull
    public String getTid() {
        return tid;
    }

    public void setTid(@NonNull String tid) {
        this.tid = tid;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
