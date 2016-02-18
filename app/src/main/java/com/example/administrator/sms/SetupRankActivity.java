package com.example.administrator.sms;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.Window;

public class SetupRankActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_rank);

        //initScreen();
    }

    private void initScreen() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        Window window = getWindow();
        ViewGroup.LayoutParams windowLayoutParams = window.getAttributes();
        // 宽度设置为屏幕的0.8
        windowLayoutParams.width = (int) (dm.heightPixels * .8);
        // 高度设置为屏幕的0.5
        windowLayoutParams.height = (int) (dm.widthPixels * 0.5);
    }
}
