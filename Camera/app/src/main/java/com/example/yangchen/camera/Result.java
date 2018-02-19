package com.example.yangchen.camera;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class Result extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        String path =getIntent().getStringExtra("picPath");
        ImageView imageView =findViewById(R.id.pic);
        Bitmap bitmap= BitmapFactory.decodeFile(path);
        imageView.setImageBitmap(bitmap);
    }
}
