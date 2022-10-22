package com.example.academicmangerment.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Teacher implements Serializable {
    @NonNull
    @PrimaryKey
    public String tid;
    public String tecName;
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

    public String getTecName() {
        return tecName;
    }

    public void setTecName(String tecName) {
        this.tecName = tecName;
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

    public Teacher() {
    }
    @Ignore
    public Teacher(@NonNull String tid, String tecName, String password, String tel, String email) {
        this.tid = tid;
        this.tecName = tecName;
        this.password = password;
        this.tel = tel;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "tid='" + tid + '\'' +
                ", realName='" + tecName + '\'' +
                ", password='" + password + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
