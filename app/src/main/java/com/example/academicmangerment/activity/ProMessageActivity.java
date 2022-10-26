package com.example.academicmangerment.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.academicmangerment.R;
import com.example.academicmangerment.adapter.MemberListAdapter;
import com.example.academicmangerment.custom.OperationDialog;
import com.example.academicmangerment.entity.Project;
import com.example.academicmangerment.entity.ProjectDetail;
import com.example.academicmangerment.entity.Student;
import com.example.academicmangerment.persistence.AppDatabase;
import com.example.academicmangerment.persistence.ProjectDao;

import java.util.List;

public class ProMessageActivity extends AppCompatActivity implements View.OnClickListener /*implements Stu04.SendProject*/ {

    private EditText stu_sid, stu_name, stu_phone, name, subject, budget, economic_analysis, purpose, viable_analysis,dialogEditView;
    private EditText[] editTexts;
    private TextView stateText;
    private Spinner level, achievement_type;
    private RecyclerView member_list;
    private Button submit,approve,reject,midReview,finalCheck,modify,delete,upload,save;
    private Button[] btnBar;
    private ScrollView scrollView;
    private ViewGroup.LayoutParams scrollViewParams;
    private int queryType;
    MemberListAdapter memberListAdapter;

    private List<Student> studentList;
    private ProjectDetail projectDetail;

    private AppDatabase db;
    private ProjectDao projectDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_sign_up);
        //获取传输来数据
        Intent intent=getIntent();
        projectDetail=(ProjectDetail) intent.getExtras().getSerializable("projectDetail");
        queryType=intent.getExtras().getInt("queryType");
        initView();
        setViewByState(projectDetail.getState());
        initData();
    }

    public void initView() {
        stu_sid = (EditText) findViewById(R.id.stu_sid);
        stu_name = (EditText) findViewById(R.id.stu_name);
        stu_phone = (EditText) findViewById(R.id.stu_phone);
        //stu_member = (EditText) findViewById(R.id.stu_member);//参与成员
        name = (EditText) findViewById(R.id.name);
        level = (Spinner) findViewById(R.id.level);
        achievement_type = (Spinner) findViewById(R.id.achievement_type);
        subject = (EditText) findViewById(R.id.subject);
        budget = (EditText) findViewById(R.id.budget);
        economic_analysis = (EditText) findViewById(R.id.economic_analysis);
        purpose = (EditText) findViewById(R.id.purpose);
        viable_analysis = (EditText) findViewById(R.id.viable_analysis);
        stateText = findViewById(R.id.detail_project_state);
        editTexts = new EditText[]{stu_sid, stu_name, stu_phone, name, subject, budget, economic_analysis, purpose, viable_analysis};
        for (EditText et : editTexts) { et.setEnabled(false);}//编辑框设置为不可编辑

        //绑定弹窗中的EditView组件
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_final_score,null);
        dialogEditView = dialogView.findViewById(R.id.dialogEditText);

        //member_add = (Button) findViewById(R.id.member_add_btn);
        scrollView = (ScrollView) findViewById(R.id.scrollView1);
        member_list = (RecyclerView) findViewById(R.id.member_list);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,"dataBase").build();
        projectDao=db.projectDao();

        finalCheck = findViewById(R.id.detail_FinalCheck_btn);
        midReview = findViewById(R.id.detail_midReviewCheck_btn);
        approve= (Button) findViewById(R.id.detail_approve_btn);
        reject= (Button) findViewById(R.id.detail_reject_btn);
        submit = (Button) findViewById(R.id.detail_submit_btn);
        modify = findViewById(R.id.detail_modify_btn);
        delete = findViewById(R.id.detail_delete_btn);
        upload = findViewById(R.id.detail_upload_btn);
        save = findViewById(R.id.detail_save_btn);
        btnBar = new Button[]{submit,approve,reject,midReview,finalCheck,modify,delete,upload,save};

        //按钮监听
        finalCheck.setOnClickListener(this);
        midReview.setOnClickListener(this);
        approve.setOnClickListener(this);
        reject.setOnClickListener(this);
        submit.setOnClickListener(this);
        modify.setOnClickListener(this);
        delete.setOnClickListener(this);
        upload.setOnClickListener(this);
        save.setOnClickListener(this);
        findViewById(R.id.detail_return_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { finish();  }
        });

        DividerItemDecoration mDivider = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        member_list.addItemDecoration(mDivider);
        member_list.setLayoutManager(new GridLayoutManager(this,1));
        memberListAdapter = new MemberListAdapter(this);
        //member_list.setVisibility(View.GONE); //复用至上传一个全新项目时默认没有成员，取消此注释
        //在此处将项目成员放入studentList
        studentList = projectDetail.getStudentList();
        memberListAdapter.setData(studentList);
        member_list.setAdapter(memberListAdapter);
    }
    public void setViewByState(int state){//根据状态设置布局
        for (Button btn : btnBar){//所有按钮变为不可见
            btn.setVisibility(View.GONE);
        }
        switch (state){
            case 0:
                stateText.setText("未提交");
                if(queryType==0) {//学生端
                    save.setVisibility(View.VISIBLE);
                    submit.setVisibility(View.VISIBLE);
                    delete.setVisibility(View.VISIBLE);
                }
                break;
            case 1:
                stateText.setText("等待教师审核");
                if(queryType==0) {//学生端
                    modify.setVisibility(View.VISIBLE);
                    delete.setVisibility(View.VISIBLE);
                }
                if(queryType==1) {//教师端
                    reject.setVisibility(View.VISIBLE);
                    approve.setVisibility(View.VISIBLE);
                }
                break;
            case 2:
                stateText.setText("等待教师重新审核");
                if(queryType==0) {//学生端
                    modify.setVisibility(View.VISIBLE);
                    delete.setVisibility(View.VISIBLE);
                }
                break;
            case 3:
                stateText.setText("已被指导教师驳回");
                if(queryType==0) {//学生端
                    modify.setVisibility(View.VISIBLE);
                    delete.setVisibility(View.VISIBLE);
                }
                break;
            case 4:
                stateText.setText("等待学院审核");
                if(queryType==2) {//管理员端
                    reject.setVisibility(View.VISIBLE);
                    approve.setVisibility(View.VISIBLE);
                }
                break;
            case 5:
                stateText.setText("已被学院驳回");
                if(queryType==0) {//学生端
                    modify.setVisibility(View.VISIBLE);
                    delete.setVisibility(View.VISIBLE);
                }
                break;
            case 6:
                stateText.setText("已立项");
                break;
            case 7:
                stateText.setText("中期检查已开启");
                if(queryType==0)
                    upload.setVisibility(View.VISIBLE);
                if(queryType==2)
                    midReview.setVisibility(View.VISIBLE);
                break;
            case 8:
                stateText.setText("中期检查未通过");
                break;
            case 9:
                stateText.setText("中期检查通过");
                break;
            case 10:
                stateText.setText("结题答辩已开启");
                if(queryType==0)
                    upload.setVisibility(View.VISIBLE);
                if(queryType==2)
                    finalCheck.setVisibility(View.VISIBLE);
                break;
            case 11:
                stateText.setText("结项答辩未通过");
                break;
            case 12:
                stateText.setText("结项答辩已通过");
                break;
            default:
                break;
        }
    }

    public void initData() {
        stu_sid.setText(projectDetail.getSid());
        stu_name.setText(projectDetail.getRealName());
        stu_phone.setText(projectDetail.getPhone());
        name.setText(projectDetail.getName());

        subject.setText(projectDetail.getSubject());
        budget.setText(""+projectDetail.getBudget());
        economic_analysis.setText(projectDetail.getEconomicAnalysis());
        purpose.setText(projectDetail.getPurpose());
        viable_analysis.setText(projectDetail.getViableAnalysis());

        setSpinnerData(level,projectDetail.getLevel());
        setSpinnerData(achievement_type, projectDetail.getAchievementType());
        submit.setText("更改");
    }

    public void setSpinnerData(Spinner spinner, String s) {
        SpinnerAdapter adapter = spinner.getAdapter();
        int k = adapter.getCount();
        for (int i = 0; i < k; i++) {
            if (s.equals(adapter.getItem(i).toString())) {
                spinner.setSelection(i, true);
                break;
            }
        }
        spinner.setClickable(false);
    }

    @Override //横竖屏切换时调用
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        if (newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE){
            //如果是横屏了，在这里设置横屏的UI
            Log.println(Log.DEBUG,"ProMessageActivity","onConfigurationChanged-Landscape");
            scrollViewParams = scrollView.getLayoutParams();
            scrollViewParams.width = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 700, getResources().getDisplayMetrics());
        }else {
            //否则，在这里设置竖屏的UI
            Log.println(Log.DEBUG,"ProMessageActivity","onConfigurationChanged-Portrait");
            scrollViewParams = scrollView.getLayoutParams();
            scrollViewParams.width=ViewGroup.LayoutParams.MATCH_PARENT;
        }
        scrollView.setLayoutParams(scrollViewParams);
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.detail_FinalCheck_btn:
                Log.println(Log.DEBUG,"detail","final-check button clicked!");
                final OperationDialog finalScoreDialog = new OperationDialog(this);
                finalScoreDialog.setMessage("请输入答辩分数")
                        .setTitle("请确认结题答辩结果")
                        .setImageResId(R.mipmap.teacher)
                        .setSingle(false).setEditText(true)//设置两个按钮且需要编辑框
                        .setPositive("通过").setNegtive("不通过")
                        .setOnClickBottomListener(new OperationDialog.OnClickBottomListener() {
                            @Override
                            public void onPositiveClick() {
                                finalScoreDialog.dismiss();
                                new Thread() {
                                    @Override
                                    public void run() {
                                        super.run();
                                        Project project = projectDao.getProject(projectDetail.getPid());
                                        project.setState(12);
                                        String score = dialogEditView.getText().toString();//TODO 获取到的分数字符串为空
                                        project.setExistingCondition(score);
                                        projectDetail.setState(12);
                                        projectDao.updateProject(project);
                                    }
                                }.start();
                                setViewByState(12);
                                Toast.makeText(ProMessageActivity.this,"操作成功：项目"+projectDetail.getPid()+"结项通过",Toast.LENGTH_SHORT).show();
                            }
                            @Override
                            public void onNegtiveClick() {
                                finalScoreDialog.dismiss();
                                new Thread() {
                                    @Override
                                    public void run() {
                                        super.run();
                                        Project project = projectDao.getProject(projectDetail.getPid());
                                        project.setState(11);
                                        project.setExistingCondition(dialogEditView.getText().toString());
                                        projectDetail.setState(11);
                                        projectDao.updateProject(project);
                                    }
                                }.start();
                                setViewByState(11);
                                Toast.makeText(ProMessageActivity.this,"操作成功："+projectDetail.getPid()+"结项未通过",Toast.LENGTH_SHORT).show();
                            }
                        }).show();
                break;

            case R.id.detail_midReviewCheck_btn:
                Log.println(Log.DEBUG,"detail","midterm review button clicked!");
                final OperationDialog midReviewDialog = new OperationDialog(this);
                midReviewDialog.setTitle("请确认中期检查结果")
                        .setImageResId(R.mipmap.teacher)
                        .setSingle(false).setEditText(false)//设置是否启用两个按钮和编辑框
                        .setPositive("通过").setNegtive("不通过")
                        .setOnClickBottomListener(new OperationDialog.OnClickBottomListener() {
                            @Override
                            public void onPositiveClick() {
                                midReviewDialog.dismiss();
                                new Thread() {
                                    @Override
                                    public void run() {
                                        super.run();
                                        Project project = projectDao.getProject(projectDetail.getPid());
                                        project.setState(9);
                                        projectDetail.setState(9);
                                        projectDao.updateProject(project);
                                    }
                                }.start();
                                setViewByState(9);
                                Toast.makeText(ProMessageActivity.this,"操作成功：项目"+projectDetail.getPid()+"中期通过",Toast.LENGTH_SHORT).show();
                            }
                            @Override
                            public void onNegtiveClick() {
                                midReviewDialog.dismiss();
                                new Thread() {
                                    @Override
                                    public void run() {
                                        super.run();
                                        Project project = projectDao.getProject(projectDetail.getPid());
                                        project.setState(8);
                                        projectDetail.setState(8);
                                        projectDao.updateProject(project);
                                    }
                                }.start();
                                setViewByState(8);
                                Toast.makeText(ProMessageActivity.this,"操作成功："+projectDetail.getPid()+"中期未通过",Toast.LENGTH_SHORT).show();
                            }
                        }).show();
                break;

            case R.id.detail_approve_btn:
                Log.println(Log.DEBUG,"detail","approve button clicked!");
                final OperationDialog approveDialog = new OperationDialog(this);
                approveDialog.setTitle("请确认审核结果")
                        .setImageResId(R.mipmap.teacher)
                        .setPositive("审核通过")
                        .setSingle(true).setEditText(false)//设置是否启用两个按钮和编辑框
                        .setOnClickBottomListener(new OperationDialog.OnClickBottomListener() {
                            @Override
                            public void onPositiveClick() {
                                approveDialog.dismiss();
                                new Thread(){
                                    @Override
                                    public void run() {
                                        super.run();
                                        Project project=projectDao.getProject(projectDetail.getPid());
                                        //教师审核
                                        if(queryType==1){
                                            project.setState(4);
                                            projectDetail.setState(4);
                                        }else{
                                            project.setState(6);
                                            projectDetail.setState(6);
                                        }
                                        projectDao.updateProject(project);
                                    }
                                }.start();
                                if(queryType==1) setViewByState(4); else setViewByState(6);
                                Toast.makeText(ProMessageActivity.this,"操作成功：项目"+projectDetail.getPid()+"审核通过",Toast.LENGTH_SHORT).show();
                            }
                            @Override
                            public void onNegtiveClick() {
                                approveDialog.dismiss();
                            }
                        }).show();
                break;

            case R.id.detail_reject_btn:
                Log.println(Log.DEBUG,"detail","reject button clicked!");
                final OperationDialog rejectDialog = new OperationDialog(this);
                rejectDialog.setTitle("请确认审核结果")
                        .setImageResId(R.mipmap.teacher)
                        .setPositive("审核不通过")
                        .setMessage("修改意见")
                        .setSingle(true).setEditText(true)//设置是否启用两个按钮和编辑框
                        .setOnClickBottomListener(new OperationDialog.OnClickBottomListener() {
                            @Override
                            public void onPositiveClick() {
                                rejectDialog.dismiss();
                                new Thread(){
                                    @Override
                                    public void run() {
                                        super.run();
                                        Project project=projectDao.getProject(projectDetail.getPid());
                                        //教师审核
                                        if(queryType==1){
                                            project.setState(3);
                                            projectDetail.setState(3);
                                        }else{
                                            project.setState(5);
                                            projectDetail.setState(5);
                                        }
                                        projectDao.updateProject(project);
                                    }
                                }.start();
                                if(queryType==1) setViewByState(3); else setViewByState(5);
                                Toast.makeText(ProMessageActivity.this,"操作成功：项目"+projectDetail.getPid()+"审核通过",Toast.LENGTH_SHORT).show();
                            }
                            @Override
                            public void onNegtiveClick() {
                                rejectDialog.dismiss();
                            }
                        }).show();
                break;
            case R.id.detail_submit_btn:
                Log.println(Log.DEBUG,"detail","submit button clicked!");
                final OperationDialog submitDialog = new OperationDialog(this);
                submitDialog.setTitle("确认提交项目审核")
                        .setImageResId(R.mipmap.teacher)
                        .setPositive("提交项目")
                        .setSingle(true).setEditText(false)//设置是否启用两个按钮和编辑框
                        .setOnClickBottomListener(new OperationDialog.OnClickBottomListener() {
                            @Override
                            public void onPositiveClick() {
                                submitDialog.dismiss();
                                new Thread(){
                                    @Override
                                    public void run() {
                                        super.run();
                                        //TODO 提交项目信息 本界面修改提交后跳转至状态2
                                    }
                                }.start();
                                setViewByState(2);
                                Toast.makeText(ProMessageActivity.this,"操作成功：项目"+projectDetail.getPid()+"已提交",Toast.LENGTH_SHORT).show();
                            }
                            @Override
                            public void onNegtiveClick() {
                            }
                        }).show();
                break;
            case R.id.detail_modify_btn:
                Log.println(Log.DEBUG,"detail","modify button clicked!");
                for (EditText et : editTexts) { et.setEnabled(true);}//编辑框设置为可编辑
                setViewByState(0);
                //TODO 配置成员列表和 Adapter 切换按钮可用状态
                break;
            case R.id.detail_delete_btn:
                Log.println(Log.DEBUG,"detail","delete button clicked!");
                final OperationDialog deleteDialog = new OperationDialog(this);
                deleteDialog.setTitle("确定要删除这个项目吗")
                        .setImageResId(R.mipmap.teacher)
                        .setPositive("删除项目")
                        .setSingle(true).setEditText(false)//设置是否启用两个按钮和编辑框
                        .setOnClickBottomListener(new OperationDialog.OnClickBottomListener() {
                            @Override
                            public void onPositiveClick() {
                                deleteDialog.dismiss();
                                new Thread(){
                                    @Override
                                    public void run() {
                                        super.run();
                                        //TODO 数据删除该项目
                                    }
                                }.start();
                                finish();
                                Toast.makeText(ProMessageActivity.this,"操作成功：项目"+projectDetail.getPid()+"删除",Toast.LENGTH_SHORT).show();
                            }
                            @Override
                            public void onNegtiveClick() {
                            }
                        }).show();
                break;
            case R.id.detail_upload_btn:
                Log.println(Log.DEBUG,"detail","upload button clicked!");
                new AlertDialog.Builder(this)
                        .setTitle("请选择成果上传方式")
                        .setIcon(R.mipmap.student)
                        .setItems(new String[]{"上传图片", "上传文件", "使用相机拍摄"}, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which){
                                    case 0:
                                        //打开相册

                                        break;
                                    case 1:
                                        //打开文件管理器
                                        break;
                                    case 2:
                                        //打开相机
                                        break;
                                }
                            }
                        }).show();
                break;
            case R.id.detail_save_btn:
                Log.println(Log.DEBUG,"detail","detail button clicked!");
                //TODO 保存项目
                Toast.makeText(ProMessageActivity.this,"操作成功：项目"+projectDetail.getPid()+"保存",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
