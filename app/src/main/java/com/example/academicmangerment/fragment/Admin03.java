package com.example.academicmangerment.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.academicmangerment.R;
import com.example.academicmangerment.activity.ProMessageActivity;
import com.example.academicmangerment.adapter.Admin03Adapter;
import com.example.academicmangerment.adapter.Stu04Adapter;
import com.example.academicmangerment.entity.ProjectDetail;
import com.example.academicmangerment.persistence.AppDatabase;
import com.example.academicmangerment.persistence.ProjectDao;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Admin03#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Admin03 extends Fragment {

    private ProjectDao projectDao;
    private AppDatabase db;

    private View view;
    public RecyclerView mRecyclerView;
    private List<ProjectDetail> mProjectList ;
    private Admin03Adapter adapter;
    GridLayoutManager gridLayoutManager;
    LinearLayoutManager linearLayoutManager;
    public TextView textView;

    public Admin03() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment Admin03.
     */
    // TODO: Rename and change types and number of parameters
    public static Admin03 newInstance() {
        Admin03 fragment = new Admin03();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        gridLayoutManager = new GridLayoutManager(getActivity(),2);
        linearLayoutManager = new LinearLayoutManager(getActivity());

        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_admin03, container, false);

        mRecyclerView =(RecyclerView) view.findViewById(R.id.adm03_projectsRecyclerView);
        //设置RecyclerView布局
        DividerItemDecoration mDivider = new DividerItemDecoration(getActivity(),DividerItemDecoration.HORIZONTAL);
        mRecyclerView.addItemDecoration(mDivider);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        adapter=new Admin03Adapter(getActivity());
        //从数据库中读取项目信息
        @SuppressLint("HandlerLeak")
        final Handler handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                mProjectList=(List<ProjectDetail>) msg.obj;
                adapter.setData(mProjectList);
                mRecyclerView.setAdapter(adapter);

            }
        };
        db= Room.databaseBuilder(getContext(), AppDatabase.class,"dataBase").build();
        projectDao=db.projectDao();
        new Thread(){
            @Override
            public void run() {
                super.run();
                mProjectList=projectDao.getProjectDetail();
                for(ProjectDetail p:mProjectList){
                    p.setMembers(projectDao.getMembers(p.getPid()));
                    p.setStudentList(projectDao.getMemberStudents(p.getPid()));
                    p.setRealName(projectDao.getLeader(p.getPid()));
                    p.setPhone(projectDao.getLeaderPhone(p.getPid()));
                    p.setSid(projectDao.getLeaderSid(p.getPid()));
                }
                Message message=Message.obtain();
                message.obj=mProjectList;
                handler.sendMessage(message);
            }
        }.start();
        adapter.setOnItemClickListener(new Admin03Adapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, ProjectDetail project) {
                Intent intent = new Intent(getActivity(), ProMessageActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("projectDetail",project);
                intent.putExtras(bundle);
                startActivity(intent);
                Toast.makeText(getActivity(), "一个项目"+project.getPid(), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
    @Override //横竖屏切换时调用
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        if (newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE){
            //如果是横屏了，在这里设置横屏的UI
            Log.println(Log.DEBUG,"Adm03","onConfigurationChanged-Landscape");
            mRecyclerView.setLayoutManager(gridLayoutManager);
        }else {
            //否则，在这里设置竖屏的UI
            Log.println(Log.DEBUG,"Adm03","onConfigurationChanged-Portrait");
            mRecyclerView.setLayoutManager(linearLayoutManager);
        }
        super.onConfigurationChanged(newConfig);
    }
}