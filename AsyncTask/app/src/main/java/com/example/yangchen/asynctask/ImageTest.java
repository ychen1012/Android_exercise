package com.example.yangchen.asynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.net.URL;

public class ImageTest extends AppCompatActivity {
    private ImageView mImageView;
    private ProgressBar mprogressbar;
    private static String URL=
            "http://ss.csdn.net/p?http://mmbiz.qpic.cn/mmbiz_jpg/EQB7yFED1iaTCeWRwC26hH8IJJKpNWJaf8ZPmBYBem7h20ic0UXqcJxib2Ticc34JjZLPiatUsrG0nBxYqo8getlkyg/0?wx_fmt=jpeg";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_test);
        mImageView=findViewById(R.id.image);
        mprogressbar=findViewById(R.id.bar);
        //设置传递进去的参数;
        //开始线程；OnPreExecute()->doInBackGround()->onPostExecute()<运行在主线程>
        new myAsycTask().execute(URL);


    }
    class myAsycTask extends AsyncTask<String,Void,Bitmap>{

        @Override
        protected Bitmap doInBackground(String... strings) {
            String url =strings[0];
            Bitmap bitmap =null;
            URLConnection connection;
            InputStream is;
            try {
                connection=new URL(url).openConnection();
                is=connection.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //解析输入流；

                bitmap= BitmapFactory.decodeStream(bis);
                is.close();
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //将bitmap作为返回值，返回给调用的方法；
            return bitmap;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mprogressbar.setVisibility(View.VISIBLE);
        }

        @Override
        //doInBackGround方法执行完成后，将返回值返回给onPostExecute方法，
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            mprogressbar.setVisibility(View.GONE);
            mImageView.setImageBitmap(bitmap);



        }
    }
}
