package com.example.academicmangerment.fragment;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.academicmangerment.R;
import com.example.academicmangerment.adapter.Admin02Adapter;
import com.example.academicmangerment.adapter.MemberListAdapter;
import com.example.academicmangerment.entity.Project;
import com.example.academicmangerment.entity.StuProject;
import com.example.academicmangerment.entity.Student;
import com.example.academicmangerment.entity.TeachProject;
import com.example.academicmangerment.entity.Teacher;
import com.example.academicmangerment.persistence.AppDatabase;
import com.example.academicmangerment.persistence.ProjectDao;
import com.example.academicmangerment.persistence.StuProjectDao;
import com.example.academicmangerment.persistence.StudentDao;
import com.example.academicmangerment.persistence.TeachProjectDao;
import com.example.academicmangerment.persistence.TeacherDao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Stu03#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Stu03 extends Fragment /*implements View.OnClickListener*/ {


    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "student";


    private Student student;
    private Teacher teacher;
    private String selectType;
    private String selectLevel;

    private TextView tec_name;
    private EditText stu_sid, stu_name, stu_phone, stu_member,tec_tid;
    private EditText proName;
    private Spinner level, achievement_type;
    private EditText subject, budget, economic_analysis, purpose, viable_analysis;
    private RecyclerView member_list;
    private Button submit, member_add,tec_add;
    private ScrollView scrollView;
    private ViewGroup.LayoutParams scrollViewParams;

    MemberListAdapter memberListAdapter;
    private List<Student> studentList;

    private AppDatabase db;
    private StudentDao studentDao;
    private ProjectDao projectDao;
    private TeacherDao teacherDao;
    private StuProjectDao stuProjectDao;
    private TeachProjectDao teachProjectDao;

    private View v;

    public Stu03() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Stu03.
     */

    public static Stu03 newInstance(Student student) {
        Stu03 fragment = new Stu03();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, student);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            student = (Student) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_stu03, container, false);
        stu_sid = (EditText) v.findViewById(R.id.edit_stu03_sid);
        stu_name = (EditText) v.findViewById(R.id.edit_stu03_name);
        stu_phone = (EditText) v.findViewById(R.id.edit_stu03_phone);
        stu_member = (EditText) v.findViewById(R.id.edit_stu03_member);//参与成员
        proName = (EditText) v.findViewById(R.id.edit_stu03_proName);
        level = (Spinner) v.findViewById(R.id.stu03_level);
        achievement_type = (Spinner) v.findViewById(R.id.stu03_achievement_type);
        subject = (EditText) v.findViewById(R.id.edit_stu03_subject);
        budget = (EditText) v.findViewById(R.id.edit_stu03_budget);
        economic_analysis = (EditText) v.findViewById(R.id.edit_stu03_economic_analysis);
        purpose = (EditText) v.findViewById(R.id.edit_stu03_purpose);
        viable_analysis = (EditText) v.findViewById(R.id.edit_stu03_viable_analysis);
        tec_name=(TextView) v.findViewById(R.id.stu03_tec_name);
        tec_tid=(EditText) v.findViewById(R.id.edit_stu03_tec);
        tec_add=(Button) v.findViewById(R.id.stu03_tec_add_btn);

        stu_sid.setFocusable(false);
        stu_sid.setFocusableInTouchMode(false);
        stu_sid.setText(student.getSid());

        stu_name.setFocusable(false);
        stu_name.setFocusableInTouchMode(false);
        stu_name.setText(student.getRealName());

        stu_phone.setFocusable(false);
        stu_phone.setFocusableInTouchMode(false);
        stu_phone.setText(student.getPhone());

        submit = (Button) v.findViewById(R.id.stu03_submit);
        member_add = (Button) v.findViewById(R.id.stu03_member_add_btn);

        scrollView = (ScrollView) v.findViewById(R.id.stu03_scrollView);
        member_list = (RecyclerView) v.findViewById(R.id.stu03_member_list);

        DividerItemDecoration mDivider = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        member_list.addItemDecoration(mDivider);
        member_list.setLayoutManager(new GridLayoutManager(getContext(), 1));
        memberListAdapter = new MemberListAdapter(getContext());
        //member_list.setVisibility(View.GONE); //复用至上传一个全新项目时默认没有成员，取消此注释

        studentList = new ArrayList<Student>();
        memberListAdapter.setData(studentList);
        member_list.setAdapter(memberListAdapter);

        db = Room.databaseBuilder(getContext(), AppDatabase.class, "dataBase").build();
        studentDao = db.studentDao();
        projectDao = db.projectDao();
        stuProjectDao=db.stuProjectDao();
        teachProjectDao=db.teachProjectDao();
        teacherDao=db.teacherDao();

        //选择器监听
        achievement_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectType=Stu03.this.getResources().getStringArray(R.array.projectTypeList)[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        level.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectLevel=Stu03.this.getResources().getStringArray(R.array.projectLevelList)[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //添加指导老师监听
        tec_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tid=tec_tid.getText().toString();
                @SuppressLint("HandlerLeak") final Handler handler = new Handler() {
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        super.handleMessage(msg);
                       teacher=(Teacher) msg.obj;
                        if(teacher!=null){
                            tec_name.setText(teacher.getTecName());

                        }else{
                            Toast.makeText(getContext(),"此老师不存在",Toast.LENGTH_SHORT);
                        }
                    }
                };
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        teacher=teacherDao.getTeacher(tid);
                        Message message=Message.obtain();
                        message.obj=teacher;
                        handler.sendMessage(message);
                    }
                }.start();
            }
        });
        //提交
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Project project=new Project();
                project.setPid(new Date().getTime()+"");
                project.setAchievementType(selectType);
                project.setCreateUser(student.getRealName());
                project.setBudget(Double.parseDouble(budget.getText().toString()));
                project.setBeginTime(new Date().toString());
                project.setCollege(student.getCollege());
                project.setEconomicAnalysis(economic_analysis.getText().toString());
                project.setExpectResult(purpose.getText().toString());
                project.setLevel(selectLevel);
                project.setName(proName.getText().toString());
                project.setSubject(subject.getText().toString());
                project.setState(1);
                project.setViableAnalysis(viable_analysis.getText().toString());
                project.setPurpose(purpose.getText().toString());
                List<StuProject> stuProjectList=new ArrayList<>();
                for(Student s:studentList){
                    StuProject stuProject=new StuProject(s.getSid(),project.getPid(),"2");
                    stuProjectList.add(stuProject);
                }
                stuProjectList.add(new StuProject(student.getSid(),project.getPid(),"1"));

                @SuppressLint("HandlerLeak") final Handler handler = new Handler() {
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        super.handleMessage(msg);
                        if(msg.what==1){
                            //清空所有内容
                            clearAll();
                            Toast.makeText(getContext(),"提交成功",Toast.LENGTH_SHORT);
                            //跳转到项目管理
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.stu_fragments,Stu04.newInstance(student))
                                    .addToBackStack(null).commit();

                        }
                    }
                };
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        projectDao.insertProject(project);
                        /*Toast.makeText(getContext(),"提交成功",Toast.LENGTH_SHORT);*/
                        teachProjectDao.insertTeachPro(new TeachProject(teacher.tid,project.getPid(),"0"));
                        stuProjectDao.insertStuProjects(stuProjectList);
                        Message msg=Message.obtain();
                        msg.what=1;
                        handler.sendMessage(msg);
                    }
                }.start();
            }
        });
        member_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*System.out.println("点击");*/
                String sid = stu_member.getText().toString();
                @SuppressLint("HandlerLeak") final Handler handler = new Handler() {
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        super.handleMessage(msg);
                        Student student=(Student) msg.obj;
                        if(student!=null){
                            studentList.add(student);
                            memberListAdapter.setData(studentList);
                        }else{
                            Toast.makeText(getContext(),"此学生不存在",Toast.LENGTH_SHORT);
                        }
                    }
                };
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        Student student = studentDao.getStudent(sid);
                        Message message=Message.obtain();
                        message.obj=student;
                        handler.sendMessage(message);
                    }
                }.start();

            }
        });
        return v;
    }

    @Override //横竖屏切换时调用
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //如果是横屏了，在这里设置横屏的UI
            Log.println(Log.DEBUG, "stu03", "onConfigurationChanged-Landscape");
            scrollViewParams = scrollView.getLayoutParams();
            scrollViewParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 700, getResources().getDisplayMetrics());
        } else {
            //否则，在这里设置竖屏的UI
            Log.println(Log.DEBUG, "stu03", "onConfigurationChanged-Portrait");
            scrollViewParams = scrollView.getLayoutParams();
            scrollViewParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        }
        scrollView.setLayoutParams(scrollViewParams);
        super.onConfigurationChanged(newConfig);
    }

    //clear
    public void clearAll(){
        stu_member.setText("");
        proName.setText("");
        subject.setText("");
        budget.setText("0");
        economic_analysis.setText("");
        purpose.setText("");
        viable_analysis.setText("");
        tec_name.setText("");
        tec_tid.setText("");
    }
    /*@Override
    public void onClick(View v) {
        System.out.println("点击");
        switch (v.getId()){
            case R.id.stu03_member_add_btn:

                String sid=stu_member.getText().toString();
                Student student=studentDao.getStudent(sid);
                if(student!=null){
                    studentList.add(student);
                    memberListAdapter.setData(studentList);
                }else{
                    Toast.makeText(getContext(),"此学生不存在",Toast.LENGTH_SHORT);
                }
                break;
            case R.id.stu03_save_btn:

                break;
            case R.id.stu03_submit_btn:

                break;
            default:
                break;
        }
    }*/
}