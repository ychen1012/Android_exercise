package com.example.yangchen.test;

import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;


public class MainActivity extends AppCompatActivity {
    private BaiduMap mBitmap;
    private MapView mMapView = null;




    //定位相关：
    private LocationClient mlocationclient;
    private MyLoationListener myLoationListener;
    private boolean isFirstin =true;
    private double mLatitude;
    private double mLongtitude;
    private BitmapDescriptor mbitmapDescriptor;
    private MyOrientationListen myOrientationListen;
    private float mcurrentx ;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBitmap=mMapView.getMap();
        MapStatusUpdate msu=MapStatusUpdateFactory.zoomTo(15.0f);
        mBitmap.setMapStatus(msu);
        initLocation();

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }
    protected void onStart() {

        super.onStart();
        mBitmap.setMyLocationEnabled(true);
        if (!mlocationclient.isStarted()){
            mlocationclient.start();
            myOrientationListen.start();
        }

    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }
    protected void onStop() {

        super.onStop();
        mBitmap.setMyLocationEnabled(false);
        mlocationclient.stop();
        myOrientationListen.stop();
    }



    private void initLocation(){
        mlocationclient =new LocationClient(this);
        myLoationListener=new MyLoationListener();
        mlocationclient.registerLocationListener(myLoationListener);
        LocationClientOption option =new LocationClientOption();
        option.setCoorType("bd09ll");
        option.setIsNeedAddress(true);
        option.setOpenGps(true);
        option.setScanSpan(1000);
        mlocationclient.setLocOption(option);
        mbitmapDescriptor= BitmapDescriptorFactory.fromResource(R.drawable.dingwei);
        myOrientationListen =new MyOrientationListen(this);
        myOrientationListen.setmOn(new MyOrientationListen.OnOrientationListener() {
            @Override
            public void onOrientationChanged(float x) {
                mcurrentx=x;

            }
        });


    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.map_common:
                mBitmap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                break;
            case R.id.map_site:
                mBitmap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.map_traffic:
                if (mBitmap.isTrafficEnabled()){
                    mBitmap.setTrafficEnabled(false);
                    item.setTitle("实时交通（关闭）");
                }else {
                    mBitmap.setTrafficEnabled(true);
                    item.setTitle("实时交通（打开）");
                }
            case R.id.map_address:
                LatLng latLng =new LatLng(mLatitude,mLongtitude);
                MapStatusUpdate mapStatusUpdate =MapStatusUpdateFactory.newLatLng(latLng);
                mBitmap.animateMapStatus(mapStatusUpdate);


        }




        return super.onOptionsItemSelected(item);
    }
    private class MyLoationListener implements BDLocationListener{

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            MyLocationData data = new MyLocationData.Builder()
                    .direction(mcurrentx)
                    .accuracy(bdLocation.getRadius())//
                    .latitude(bdLocation.getLatitude())//
                    .longitude(bdLocation.getLongitude()).build();
            mBitmap.setMyLocationData(data);
            MyLocationConfiguration config =new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL,true,mbitmapDescriptor);
            mBitmap.setMyLocationConfiguration(config);


            mLatitude=bdLocation.getLatitude();
            mLongtitude=bdLocation.getLongitude();

            if(isFirstin){
                LatLng latLng =new LatLng(bdLocation.getLatitude(),bdLocation.getLongitude());
                MapStatusUpdate msu =MapStatusUpdateFactory.newLatLng(latLng);
                mBitmap.animateMapStatus(msu);
                isFirstin=false;
                Toast.makeText(MainActivity.this,bdLocation.getAddrStr()+bdLocation.getLatitude()+bdLocation.getLongitude(),Toast.LENGTH_SHORT).show();
            }

        }
    }
}
