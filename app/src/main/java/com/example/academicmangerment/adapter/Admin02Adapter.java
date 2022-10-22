package com.example.academicmangerment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.academicmangerment.R;
import com.example.academicmangerment.entity.Teacher;

import java.util.List;

public class Admin02Adapter extends RecyclerView.Adapter<Admin02Adapter.ViewHolder> {
    private List<Teacher> teacherList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        EditText editName;
        EditText editPhone;
        EditText editEmail;
        EditText editTid;

        public ViewHolder(@NonNull View view) {
            super(view);
            imageView = view.findViewById(R.id.adm_tec_img);
            editName = (EditText) view.findViewById(R.id.edit_tec_name);
            editEmail = (EditText) view.findViewById(R.id.edit_tec_email);
            editPhone = (EditText) view.findViewById(R.id.edit_tec_phone);
            editTid = (EditText) view.findViewById(R.id.edit_tec_tid);
        }

        public void setAttribute(Teacher teacher) {
            editName.setText(teacher.getTecName());
            editEmail.setText(teacher.getEmail());
            editPhone.setText(teacher.getTel());
            editTid.setText(teacher.getTid());
        }
    }

    public Admin02Adapter() {
    }

    public Admin02Adapter(List<Teacher> teacherList) {
        this.teacherList = teacherList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_adm02, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setAttribute(teacherList.get(position));
    }

    @Override
    public int getItemCount() {
        return teacherList.size();
    }


}
