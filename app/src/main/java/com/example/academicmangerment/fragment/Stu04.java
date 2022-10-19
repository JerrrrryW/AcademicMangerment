package com.example.academicmangerment.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.academicmangerment.R;
import com.example.academicmangerment.adapter.Stu04Adapter;
import com.example.academicmangerment.entity.Project;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Stu04#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Stu04 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View view;
    public RecyclerView mRecyclerView;
    private List<Project> mProjectList = new ArrayList<>();
    private Stu04Adapter adapter;

    public Stu04() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Stu04.
     */
    // TODO: Rename and change types and number of parameters
    public static Stu04 newInstance(String param1, String param2) {
        Stu04 fragment = new Stu04();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_stu04, container, false);
        mRecyclerView =(RecyclerView) view.findViewById(R.id.projectsRecyclerView);
        getData();
        adapter = new Stu04Adapter(getActivity());
        adapter.setData(mProjectList);
        mRecyclerView.setAdapter(adapter);
        DividerItemDecoration mDivider = new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(mDivider);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        adapter.setOnItemClickListener(new Stu04Adapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, Project project) {
                Toast.makeText(getActivity(), "一个项目", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
    public void getData() {
        for(int i = 0; i < 10; i++) {
            Project project = new Project();
            project.setAchievementType(new String("论"));
            project.setName("开发软件" + (i +1));
            project.setPid("No."+(i+1));
            project.setBudget(20000+i);
            mProjectList.add(project);
        }
    }
}