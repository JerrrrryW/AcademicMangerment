package com.example.academicmangerment.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.academicmangerment.R;
import com.example.academicmangerment.entity.Project;
import com.example.academicmangerment.fragment.Stu04;

import java.util.List;



public class MyProjectAdapter extends RecyclerView.Adapter<MyProjectAdapter.myViewHolder> {
    private Context mContext;
    private List<Project> pjt;
    public MyProjectAdapter(Context context) {mContext = context;}

    /***
     * 设置数据更新界面
     * @return
     */
    public void setData(List<Project> pjt) {
        this.pjt = pjt;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_project_brief, null);
        myViewHolder myViewHolder = new myViewHolder(view);
        return myViewHolder;
    }

    @NonNull
    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        Project project = pjt.get(position);
        holder.project_type.setText(project.getAchievementType());
        holder.project_name.setText(project.getName());
        holder.project_num.setText(project.getPid());
        holder.project_level.setText(project.getLevel());
        holder.project_budget.setText(String.valueOf(project.getBudget()));

        /**暂未开发
         * holder.project_leader.setText(project.getLeader());
         holder.project_member.setText(project.getMember());
         * holder.project_teacher.setText(project.getTeacher());
         * holder.is_submitted.setText(project.getSubmitted());
         * holder.is_approved_college.setText(project.getCollege());
         * holder.is_midterm_checked.setText(project.getMidtermChecked());
         * holder.is_final_checked.setText(project.getFinalChecked());
         */
    }

    /**
     * Item总数
     * @return
     */
    @Override
    public int getItemCount() {
        return pjt == null ? 0 : pjt.size();
    }

    /**
     * item监听事件的接口
     */
    public interface OnItemClickListener {
        public void OnItemClick(View view,Project project);
    }
    private OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    class myViewHolder extends RecyclerView.ViewHolder {
        TextView project_type, project_name, project_num, project_level, project_budget, project_leader, project_member, project_teacher;
        TextView is_submitted, is_approved_college, is_midterm_checked, is_final_checked;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            project_type = itemView.findViewById(R.id.project_type);
            project_name = itemView.findViewById(R.id.project_name);
            project_num = itemView.findViewById(R.id.project_num);
            project_level = itemView.findViewById(R.id.project_level);
            project_budget = itemView.findViewById(R.id.project_budget);
            project_leader = itemView.findViewById(R.id.project_leader);
            project_member = itemView.findViewById(R.id.project_member);
            project_teacher = itemView.findViewById(R.id.project_teacher);
            is_submitted = itemView.findViewById(R.id.is_submitted);
            is_approved_college = itemView.findViewById(R.id.is_approved_college);
            is_midterm_checked = itemView.findViewById(R.id.is_midterm_checked);
            is_final_checked = itemView.findViewById(R.id.is_final_checked);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener != null) {
                        onItemClickListener.OnItemClick(v,pjt.get(getLayoutPosition()));
                    }
                }
            });
        }
    }
}
