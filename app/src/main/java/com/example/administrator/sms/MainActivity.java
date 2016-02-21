package com.example.administrator.sms;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final int MainActivity = 0;

    private Button mRankBtn, mContactBtn, mSendBtn;
    private EditText mName;

    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("快捷短信");

        initBtn();
    }

    private void initBtn() {
        mRankBtn = (Button) findViewById(R.id.main_rank_btn);
        mRankBtn.setOnClickListener(listener);

        mContactBtn = (Button) findViewById(R.id.main_contact_btn);
        mContactBtn.setOnClickListener(listener);

        mSendBtn = (Button) findViewById(R.id.main_send_btn);
        mSendBtn.setOnClickListener(listener);

        mName = (EditText) findViewById(R.id.main_contact_et);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.main_rank_btn:
                    i = new Intent(MainActivity.this, RankListActivity.class);
                    startActivity(i);
                    break;
                case R.id.main_contact_btn:
                    i = new Intent(MainActivity.this, ContactListActivity.class);
                    startActivityForResult(i, MainActivity);
                    break;
                case R.id.main_send_btn:

                    break;
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        i = new Intent(MainActivity.this, RankListActivity.class);
        startActivity(i);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MainActivity && resultCode == ContactListActivity.ContactListActivity){
            mName.setText(data.getCharSequenceExtra("number") + "(" + data.getCharSequenceExtra("name") + ")");
        }
    }
}
