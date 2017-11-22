package cn.swt.m.livedemoapp;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.TextView;

import com.dl7.player.media.IjkPlayerView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 介绍：这里写介绍
 * 作者：sweet
 * 邮箱：sunwentao@priemdu.cn
 * 时间: 2017/11/22
 */
public class LiveDemoActivity extends AppCompatActivity {
    TextView timeTv;
    TextView mNameTv;
    IjkPlayerView mPlayerView;
    Uri mUri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.live_activity);
        String name = getIntent().getStringExtra("name");
        String url = getIntent().getStringExtra("url");
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd    hh:mm:ss");
        String date = sDateFormat.format(new Date());
        mNameTv = (TextView) findViewById(R.id.tv_name);
        timeTv = (TextView) findViewById(R.id.time);
        mPlayerView = (IjkPlayerView) findViewById(R.id.jkview);
        mUri = Uri.parse("http://covertness.qiniudn" +
                ".com/android_zaixianyingyinbofangqi_test_baseline.mp4");
        mPlayerView.init()
                .setVideoPath(url)
                .setMediaQuality(IjkPlayerView.MEDIA_QUALITY_HIGH)
                .enableDanmaku()
                .start();
        timeTv.setText(date);
        mNameTv.setText(name);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPlayerView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPlayerView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPlayerView.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mPlayerView.configurationChanged(newConfig);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mPlayerView.handleVolumeKey(keyCode)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        if (mPlayerView.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }

}
