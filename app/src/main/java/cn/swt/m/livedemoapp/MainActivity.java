package cn.swt.m.livedemoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    CommonAdapter mCommonAdapter;
    List<LiveBean> datas = new ArrayList<>();
    List<String> urlstring = new ArrayList<>();
    List<String> nameString = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        urlstring.add("http://183.59.185.72:30001/PLTV/88888905/224/3221227316/10000100000000060000000001066428_0.smil#http://125.88.92.166:30001/PLTV/88888956/224/3221227666/1.m3u8");
        urlstring.add("http://183.59.185.72:30001/PLTV/88888905/224/3221227070/10000100000000060000000000698839_0.smil");
        urlstring.add("http://183.59.185.72:30001/PLTV/88888905/224/3221227135/10000100000000060000000000937845_0.smil");
        nameString.add("CCTV-2财经");//
        nameString.add("IPTV-少儿动画");//
        nameString.add("相声小品");//
        for (int i = 0; i < 3; i++) {
            LiveBean liveBean = new LiveBean();
            liveBean.setName(nameString.get(i));
            liveBean.setUrl(urlstring.get(i));
            datas.add(liveBean);
        }

        mRecyclerView.setAdapter(mCommonAdapter = new CommonAdapter<LiveBean>(this, R.layout.item, datas) {
            @Override
            public void convert(ViewHolder holder, LiveBean liveBean, int position) {
                TextView textView = holder.getView(R.id.item_tv);
                textView.setText(liveBean.getName());
            }

        });

        mCommonAdapter.setOnItemClickListener(new CommonAdapter.OnItemClickListener<LiveBean>() {
            @Override
            public void onItemClick(ViewGroup parent, View view, LiveBean liveBean, int position) {
                Intent intent = new Intent(MainActivity.this, LiveDemoActivity.class);
                intent.putExtra("name", liveBean.getName());
                intent.putExtra("url", liveBean.getUrl());
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, LiveBean liveBean, int position) {
                return false;
            }


        });
    }
}
