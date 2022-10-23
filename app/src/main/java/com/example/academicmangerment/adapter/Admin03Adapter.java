package com.example.academicmangerment.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.academicmangerment.R;
import com.example.academicmangerment.entity.ProjectDetail;

import java.util.List;

public class Admin03Adapter extends RecyclerView.Adapter<Admin03Adapter.MyViewHolder> {
    private Context mContext;
    private List<ProjectDetail> pjt;
    public Admin03Adapter(Context context) {mContext = context;}

    public void setData(List<ProjectDetail> pjt) {
        this.pjt = pjt;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_adm03, null);
        Admin03Adapter.MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ProjectDetail project = pjt.get(position);
        holder.project_type.setText(""+project.getAchievementType().charAt(0));
        holder.project_name.setText(project.getName());
        holder.project_id.setText(project.getPid());
        holder.project_level.setText(project.getLevel());
        holder.project_budget.setText(String.valueOf(project.getBudget()));


        holder.project_leader.setText(project.getRealName());
        holder.project_member.setText(project.strMember());
        holder.project_teacher.setText(project.getTecName());
        //holder.is_submitted.setText(project.getSubmitted());
        holder.is_approved_college.setText(project.getCollege());
    }

    @Override
    public int getItemCount() {
        return pjt == null ? 0 : pjt.size();
    }
    /**
     * item监听事件的接口
     */
    public interface OnItemClickListener {
        public void OnItemClick(View view,ProjectDetail project);
    }
    private OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView project_type, project_name, project_id, project_level, project_budget, project_leader, project_member, project_teacher;
        TextView is_submitted, is_approved_college, is_midterm_checked, is_final_checked;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            project_type = itemView.findViewById(R.id.adm03_project_type);
            project_name = itemView.findViewById(R.id.adm03_project_name);
            project_id = itemView.findViewById(R.id.adm03_project_id);
            project_level = itemView.findViewById(R.id.adm03_project_level);
            project_budget = itemView.findViewById(R.id.adm03_project_budget);
            project_leader = itemView.findViewById(R.id.adm03_project_leader);
            project_member = itemView.findViewById(R.id.adm03_project_member);
            project_teacher = itemView.findViewById(R.id.adm03_project_teacher);
            is_submitted = itemView.findViewById(R.id.adm03_is_submitted);
            is_approved_college = itemView.findViewById(R.id.adm03_is_approved_college);
            is_midterm_checked = itemView.findViewById(R.id.adm03_is_midterm_checked);
            is_final_checked = itemView.findViewById(R.id.adm03_is_final_checked);
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
