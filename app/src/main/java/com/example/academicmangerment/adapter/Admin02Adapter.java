package com.example.academicmangerment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.academicmangerment.R;
import com.example.academicmangerment.entity.Teacher;

import java.util.List;

public class Admin02Adapter extends RecyclerView.Adapter<Admin02Adapter.ViewHolder> {
    private List<Teacher> teacherList;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void setAttribute(Teacher teacher){

        }
    }

    public Admin02Adapter(){}
    public Admin02Adapter(List<Teacher> teacherList){
        this.teacherList=teacherList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_adm02,parent);
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
