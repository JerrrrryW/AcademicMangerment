package com.example.academicmangerment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.slidingpanelayout.widget.SlidingPaneLayout;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.bumptech.glide.Glide;

import com.example.academicmangerment.custom.CusSlidingPaneLayout;

import com.example.academicmangerment.entity.Student;
import com.example.academicmangerment.persistence.AppDatabase;
import com.example.academicmangerment.persistence.StudentDao;

public class MainActivity extends AppCompatActivity {
    private StudentDao studentDao;
    private AppDatabase db;

    private static final String TAG = "MainActivity";
    private CusSlidingPaneLayout mSlidingPaneLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView head = findViewById(R.id.img_head);
        Glide.with(getBaseContext()).load(R.mipmap.test).into(head);
        mSlidingPaneLayout = findViewById(R.id.slide_layout);
        ImageView btn = findViewById(R.id.btn_pop);

        mSlidingPaneLayout.forbidSlide(false);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mSlidingPaneLayout.isOpen()){
                    mSlidingPaneLayout.closePane();
                }else{
                    mSlidingPaneLayout.openPane();
                }
            }
        });

        initSlidingPaneLayout();
    }

    private void initSlidingPaneLayout() {
        final LinearLayout container = findViewById(R.id.main_container);
        final View leftView = mSlidingPaneLayout.getChildAt(0);
        mSlidingPaneLayout.setPanelSlideListener(new SlidingPaneLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(@NonNull View panel, float slideOffset) {
                //设置侧面栏缩放
                leftView.setPivotX(-leftView.getWidth() / 6.0f);
                leftView.setPivotY(leftView.getHeight() / 2.0f);
                leftView.setScaleX(0.7f + 0.3f * slideOffset);
                leftView.setScaleY(0.7f + 0.3f * slideOffset);

                //设置首页滑动时缩放
                container.setScaleX(1f - 0.1f * slideOffset);
                container.setScaleY(1f - 0.1f * slideOffset);
                container.setElevation(10.0f * slideOffset);
            }

            @Override
            public void onPanelOpened(@NonNull View panel) {

            }

            @Override
            public void onPanelClosed(@NonNull View panel) {

            }
        });

        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mSlidingPaneLayout.isOpen()){
                    mSlidingPaneLayout.closePane();
                }
            }
        });
    }
    public void test(){
        db = Room.inMemoryDatabaseBuilder(getApplicationContext(), AppDatabase.class).build();
        Student student=new Student("2220192759","1055689557888","2019/09/01","123456","18307050360","熊迪","无","本科生","大连海事大学","09/22","本科生","China",1,"25567.9932@qq.com");

        studentDao=db.studentDao();
        studentDao.insert(student);

        db.close();
    }
}