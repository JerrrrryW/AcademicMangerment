package com.example.academicmangerment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.academicmangerment.R;
import com.example.academicmangerment.entity.Student;
import com.example.academicmangerment.fragment.Admin01;
import com.example.academicmangerment.persistence.AppDatabase;
import com.example.academicmangerment.persistence.StudentDao;

import java.util.List;

public class Admin01Adapter extends RecyclerView.Adapter<Admin01Adapter.ViewHolder> {
    //所需数据
    private List<Student> studentList;
    private ButtonInterface buttonInterface;
    private AppDatabase db;
    private StudentDao studentDao;

    private Context context;

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
        Button saveButton;

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


            //按钮
            saveButton=(Button) view.findViewById(R.id.save_btn_adm01);

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
    public Admin01Adapter(List<Student> studentList,Context context) {
        this.context=context;
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
        Admin01Adapter.ViewHolder mholder=holder;
        int p=position;
        //设置按钮监听
        holder.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(buttonInterface!=null){
                    Student student=studentList.get(p);
                    student.setSid(mholder.editName.getText().toString());
                    String sex = mholder.editSex.getText().toString();
                    student.setSex(sex.equals("男") ? 1 : 0);
                    student.setPhone(mholder.editPhone.getText().toString());
                    student.setCard(mholder.editCard.getText().toString());
                    student.setState(mholder.editState.getText().toString());
                    student.setDegree(mholder.editDegree.getText().toString());
                    student.setType(mholder.editType.getText().toString());
                    student.setEmail(mholder.editEmail.getText().toString());
                    student.setBirthday(mholder.editBirth.getText().toString());
                    student.setCollege(mholder.editCollege.getText().toString());
                    student.setSid(mholder.editSid.getText().toString());

                    new MyThread(student).start();
                    //接口实例化后的而对象，调用重写后的方法
                    /*buttonInterface.OnItemClick(view,p);*/
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    /**
     * item监听事件的接口
     */
    public interface ButtonInterface {
        public void OnItemClick(View view, int position);
    }
    /**
     *按钮点击事件需要的方法
     */
    public void setButtonInterface(ButtonInterface buttonInterface) {
        this.buttonInterface = buttonInterface;
    }
    class MyThread extends Thread {
        Student student;

        public MyThread() {
        }
        public MyThread(Student student){
            this.student=student;
        }
        @Override
        public void run() {
            db = Room.databaseBuilder(context, AppDatabase.class, "dataBase").build();
            studentDao = db.studentDao();
            studentDao.updateStudent(student);
            super.run();
        }
    }

}
