package com.example.yangchen.camera;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void gocamera(View view){
        Intent intent =new Intent(MainActivity.this,CustomCamera.class);
        startActivity(intent);
    }
}
