package com.example.academicmangerment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.academicmangerment.R;
import com.example.academicmangerment.entity.ProjectDetail;
import com.example.academicmangerment.entity.Student;

import java.util.List;

public class Admin01Adapter extends RecyclerView.Adapter<Admin01Adapter.ViewHolder> {
    //所需数据
    private List<Student> studentList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        EditText editName;
        EditText editSex;
        EditText editPhone;
        EditText editCard;
        EditText editState;
        EditText editDegree;
        EditText editType;
        EditText editEmail;
        EditText editBirth;
        EditText editCollege;
        EditText editSid;


        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            imageView = view.findViewById(R.id.adm_stu_img);
            editName = (EditText) view.findViewById(R.id.edit_stu_name);
            editSex = (EditText) view.findViewById(R.id.edit_stu_sex);
            editPhone = (EditText) view.findViewById(R.id.edit_stu_phone);
            editCard = (EditText) view.findViewById(R.id.edit_stu_card);
            editState = (EditText) view.findViewById(R.id.edit_stu_state);
            editDegree = (EditText) view.findViewById(R.id.edit_stu_degree);
            editType = (EditText) view.findViewById(R.id.edit_stu_type);
            editEmail = (EditText) view.findViewById(R.id.edit_stu_email);
            editBirth = (EditText) view.findViewById(R.id.edit_stu_birth);
            editCollege = (EditText) view.findViewById(R.id.edit_stu_college);
            editSid = (EditText) view.findViewById(R.id.edit_stu_sid);

            //设置监听

        }
        //设置属性
        public void setAttribute(Student student){
            editName.setText(student.getRealName());
            editSex.setText(student.getSex()==1?"男":"女");
            editPhone.setText(student.getPhone());
            editCard.setText(student.getCard());
            editState.setText(student.getState());
            editDegree.setText(student.getDegree());
            editType.setText(student.getType());
            editEmail.setText(student.getEmail());
            editBirth.setText(student.getBirthday());
            editCollege.setText(student.getCollege());
            editSid.setText(student.getSid());
        }
    }
    public Admin01Adapter(){}
    public Admin01Adapter(List<Student> studentList) {
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public Admin01Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //记住加false属性
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_adm01, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Admin01Adapter.ViewHolder holder, int position) {
        holder.setAttribute(studentList.get(position));
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    /**
     * item监听事件的接口
     */
    public interface OnItemClickListener {
        public void OnItemClick(View view, Student student);
    }
    private OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


}
