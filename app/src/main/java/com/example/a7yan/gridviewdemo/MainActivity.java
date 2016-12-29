package com.example.a7yan.gridviewdemo;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    public static  final int RequestCode=1;
    private ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.iv);
    }
    public void click(View view)
    {
        Intent intent = new Intent(MainActivity.this,ResultActivity.class);
        startActivityForResult(intent,RequestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==RequestCode &&  resultCode== Activity.RESULT_OK){
            int imageId=data.getIntExtra("imageId",R.mipmap.ic_launcher);
            iv.setImageResource(imageId);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    //监听按键事件

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            new AlertDialog.Builder(this)
                    .setMessage("确定退出?")
                    .setTitle("确定退出?")
                    .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setNegativeButton("取消",null)
                    .show();
            return true;
        }
        //默认工作
        return super.onKeyUp(keyCode, event);
    }
}
