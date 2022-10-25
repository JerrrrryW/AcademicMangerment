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


public class Stu04Adapter extends RecyclerView.Adapter<Stu04Adapter.MyViewHolder> {
    private Context mContext;
    private List<ProjectDetail> pjt;

    public Stu04Adapter(Context context) {
        mContext = context;
    }

    /***
     * 设置数据更新界面
     * @return
     */
    public void setData(List<ProjectDetail> pjt) {
        this.pjt = pjt;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_project_brief, null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @NonNull
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ProjectDetail project = pjt.get(position);
        holder.project_type.setText("" + project.getAchievementType().charAt(0));
        holder.project_name.setText(project.getName());
        holder.project_id.setText(project.getPid());
        holder.project_level.setText(project.getLevel());
        holder.project_budget.setText(String.valueOf(project.getBudget()));

        holder.project_leader.setText(project.getRealName());
        holder.project_member.setText(project.strMember());
        holder.project_teacher.setText(project.getTecName());
        int state = project.getState();
        examine(holder,state);
        //holder.is_submitted.setText(project.getSubmitted());
        //holder.is_approved_college.setText(project.getCollege());
        //holder.is_midterm_checked.setText(project.getMidtermChecked());
        //holder.is_final_checked.setText(project.getFinalChecked());
    }
    //设置状态
    public void examine(MyViewHolder holder,int state){
        switch (state){
            case 0:
                holder.is_submitted.setText("未提交");
                holder.is_approved_teacher.setText("待审核");
                holder.is_approved_college.setText("待审核");
                holder.is_midterm_checked.setText("未开启");
                holder.is_final_checked.setText("未开启");
                break;
            case 1:
                holder.is_submitted.setText("已提交");
                holder.is_approved_teacher.setText("待审核");
                holder.is_approved_college.setText("待审核");
                holder.is_midterm_checked.setText("未开启");
                holder.is_final_checked.setText("未开启");
                break;
            case 2:
                holder.is_submitted.setText("已提交");
                holder.is_approved_teacher.setText("待审核");
                holder.is_approved_college.setText("待审核");
                holder.is_midterm_checked.setText("未开启");
                holder.is_final_checked.setText("未开启");
                break;
            case 3:
                holder.is_submitted.setText("待修改");
                holder.is_approved_teacher.setText("教师驳回");
                holder.is_approved_college.setText("待审核");
                holder.is_midterm_checked.setText("未开启");
                holder.is_final_checked.setText("未开启");
                break;
            case 4:
                holder.is_submitted.setText("已提交");
                holder.is_approved_teacher.setText("审核通过");
                holder.is_approved_college.setText("待审核");
                holder.is_midterm_checked.setText("未开启");
                holder.is_final_checked.setText("未开启");
                break;
            case 5:
                holder.is_submitted.setText("待修改");
                holder.is_approved_teacher.setText("审核通过");
                holder.is_approved_college.setText("学院驳回");
                holder.is_midterm_checked.setText("未开启");
                holder.is_final_checked.setText("未开启");
                break;
            case 6:
                holder.is_submitted.setText("已立项");
                holder.is_approved_teacher.setText("审核通过");
                holder.is_approved_college.setText("审核通过");
                holder.is_midterm_checked.setText("未开启");
                holder.is_final_checked.setText("未开启");
                break;
            case 7:
                holder.is_submitted.setText("已立项");
                holder.is_approved_teacher.setText("审核通过");
                holder.is_approved_college.setText("审核通过");
                holder.is_midterm_checked.setText("已开启");
                holder.is_final_checked.setText("未开启");
                break;
            case 8:
                holder.is_submitted.setText("已结项");
                holder.is_approved_teacher.setText("审核通过");
                holder.is_approved_college.setText("审核通过");
                holder.is_midterm_checked.setText("未通过");
                holder.is_final_checked.setText("未开启");
                break;
            case 9:
                holder.is_submitted.setText("已立项");
                holder.is_approved_teacher.setText("审核通过");
                holder.is_approved_college.setText("审核通过");
                holder.is_midterm_checked.setText("检查通过");
                holder.is_final_checked.setText("未开启");
                break;
            case 10:
                holder.is_submitted.setText("已立项");
                holder.is_approved_teacher.setText("审核通过");
                holder.is_approved_college.setText("审核通过");
                holder.is_midterm_checked.setText("检查通过");
                holder.is_final_checked.setText("已开启");
                break;
            case 11:
                holder.is_submitted.setText("已结项");
                holder.is_approved_teacher.setText("审核通过");
                holder.is_approved_college.setText("审核通过");
                holder.is_midterm_checked.setText("检查通过");
                holder.is_final_checked.setText("答辩未通过");
                break;
            case 12:
                holder.is_submitted.setText("已结项");
                holder.is_approved_teacher.setText("审核通过");
                holder.is_approved_college.setText("审核通过");
                holder.is_midterm_checked.setText("检查通过");
                holder.is_final_checked.setText("检查通过");
                break;
        }
    }
    /**
     * Item总数
     *
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
        public void OnItemClick(View view, ProjectDetail project);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView project_type, project_name, project_id, project_level, project_budget, project_leader, project_member, project_teacher;
        TextView is_submitted, is_approved_college, is_midterm_checked, is_final_checked,is_approved_teacher;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            project_type = itemView.findViewById(R.id.project_type);
            project_name = itemView.findViewById(R.id.project_name);
            project_id = itemView.findViewById(R.id.project_id);
            project_level = itemView.findViewById(R.id.project_level);
            project_budget = itemView.findViewById(R.id.project_budget);
            project_leader = itemView.findViewById(R.id.project_leader);
            project_member = itemView.findViewById(R.id.project_member);
            project_teacher = itemView.findViewById(R.id.project_teacher);
            is_submitted = itemView.findViewById(R.id.is_submitted);
            is_approved_teacher=itemView.findViewById(R.id.is_approved_teacher);
            is_approved_college = itemView.findViewById(R.id.is_approved_college);
            is_midterm_checked = itemView.findViewById(R.id.is_midterm_checked);
            is_final_checked = itemView.findViewById(R.id.is_final_checked);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.OnItemClick(v, pjt.get(getLayoutPosition()));
                    }
                }
            });
        }
    }
}
