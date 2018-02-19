package com.example.yangchen.camera;

import android.app.Activity;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by yangchen on 2018/2/18.
 */

public class CustomCamera extends Activity implements SurfaceHolder.Callback{
    private Camera mCamera;
    private SurfaceView mPreview;
    private SurfaceHolder mHolder;
    private Camera.PictureCallback mpictureCallback =new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] bytes, Camera camera) {
            File tempFile =new File("/sdcard/temp.png");
            try {
                FileOutputStream fos =new FileOutputStream(tempFile);
                fos.write(bytes);
                fos.close();
                Intent intent1 =new Intent(CustomCamera.this,Result.class);
                intent1.putExtra("picPath",tempFile.getAbsolutePath());
                startActivity(intent1);
                CustomCamera.this.finish();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customcamera);
        mPreview=findViewById(R.id.preview);
        mHolder=mPreview.getHolder();
        mHolder.addCallback(this);
        mPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCamera.autoFocus(null);
            }
        });
    }
    public void camera(View view){
        Camera.Parameters parameters =mCamera.getParameters();
        parameters.setPictureFormat(ImageFormat.JPEG);
        parameters.setPreviewSize(800,400);
        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
        mCamera.autoFocus(new Camera.AutoFocusCallback() {
            @Override
            public void onAutoFocus(boolean b, Camera camera) {
                if (b){
                    mCamera.takePicture(null,null,mpictureCallback);
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mCamera==null){
            mCamera=getCamera();
            if (mHolder!=null){
                setStartPreview(mCamera,mHolder);
            }
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseCamera();

    }






    private Camera getCamera(){
        //调用硬件
        Camera camera;
        try{
            camera =Camera.open();
        }catch (Exception e){
            camera=null;
        }

        return camera;
    }
    private void setStartPreview(Camera camera,SurfaceHolder holder){
        try {
            camera.setPreviewDisplay(holder);//将系统camera角度调整；
            camera.startPreview();
            camera.setDisplayOrientation(90);
        } catch (IOException e) {
            e.printStackTrace();
        }


        //显示实时预览
    }
    private void releaseCamera(){
       if (mCamera!=null){
           mCamera.setPreviewCallback(null);
           mCamera.stopPreview();
           mCamera.release();
           mCamera=null;
       }




    }


    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        mCamera.stopPreview();
        setStartPreview(mCamera,mHolder);

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        releaseCamera();

    }
}
