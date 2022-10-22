package com.example.academicmangerment.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.academicmangerment.R;
import com.example.academicmangerment.entity.Student;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MemberListAdapter extends RecyclerView.Adapter<MemberListAdapter.MyViewHolder> {
    private Context mContext;
    private List<Student> stu;
    public MemberListAdapter(Context context) {mContext = context;}

    /***
     * 设置数据更新界面
     * @return
     */
    public void setData(List<Student> stu) {
        this.stu = stu;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_project_member, null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @NonNull
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Student student = stu.get(position);
        holder.member_id.setText(student.getSid());
        holder.member_name.setText(student.getRealName());
    }

    /**
     * Item总数
     * @return
     */
    @Override
    public int getItemCount() {
        return stu == null ? 0 : stu.size();
    }

    /**
     * item监听事件的接口
     */
    public interface OnItemClickListener {
        public void OnItemClick(View view,Student project);
    }
    private OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView member_id,member_name;
        Button delete_btn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            member_id = itemView.findViewById(R.id.member_item_id);
            member_name = itemView.findViewById(R.id.member_item_name);
            delete_btn = itemView.findViewById(R.id.member_delete_btn);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener != null) {
                        onItemClickListener.OnItemClick(v,stu.get(getLayoutPosition()));
                    }
                }
            });
        }
    }
}