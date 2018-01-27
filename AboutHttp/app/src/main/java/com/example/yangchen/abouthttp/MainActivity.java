package com.example.yangchen.abouthttp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;


public class MainActivity extends Activity {
    OkHttpClient okHttpClient=new OkHttpClient();
    private TextView tv_shoew;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_shoew=(TextView)findViewById(R.id.tv_show);
    }
public void doPost(View view){

    FormEncodingBuilder requestBodyBuilder=new FormEncodingBuilder();
    RequestBody requestBody=requestBodyBuilder.add("name","yyyy").add("pwd","1234").build();
    Request.Builder builder=new Request.Builder();
  Request request=  builder.url("http://api.nohttp.net/upload").post(requestBody).build();
    Call call=okHttpClient.newCall(request);
    //执行 call；
    call.enqueue(new Callback() {
        @Override
        public void onFailure(Request request, IOException e) {

        }

        @Override
        public void onResponse(Response response) throws IOException {
            final String res =response.body().string();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tv_shoew.setText(res);
                }
            });

        }
    });


}
public void doGet(View view){
        //1.拿到OKhttp对象

    //构造Request；
    Request.Builder builder=new Request.Builder();
    final Request request=builder.get().url("http://api.nohttp.net/upload?name=yanzhenjie&pwd=123").build();
    //将Request封装为Call；
    Call call=okHttpClient.newCall(request);
    //执行 call；
    call.enqueue(new Callback() {
        @Override
        public void onFailure(Request request, IOException e) {

        }

        @Override
        public void onResponse(Response response) throws IOException {
            final String res =response.body().string();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tv_shoew.setText(res);
                }
            });

        }
    });
    }





    }





