package com.example.kb_jay.guidedemo;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kjguidelib.KJGuideView;
import com.example.kjguidelib.KJPageAdater;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private KJGuideView mGuideView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final ArrayList<String> data = new ArrayList<>();
        data.add("测试数据1");
        data.add("测试数据2");
        data.add("测试数据3");

        mGuideView = (KJGuideView) this.findViewById(R.id.demo_view);

        mGuideView.setData(data.size(), new KJPageAdater.KJBindDataListener() {
            @Override
            public View bindViewAndData(ViewGroup container, int pos) {
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.vp_item, container, false);
                TextView tv = (TextView) view.findViewById(R.id.tv_item);
                tv.setText(data.get(pos));
                container.addView(view);
                return view;
            }
        });

        mGuideView.setGuideEventListener(new KJGuideView.GuideEventListener() {
            @Override
            public void onClickSkip() {
                Toast.makeText(MainActivity.this, "点击了跳过", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onClickJump() {
                Toast.makeText(MainActivity.this, "点击了跳转", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
