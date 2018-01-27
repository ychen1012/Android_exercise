package yangchen.myapplication;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private RadioButton rb_first,rb_second,rb_third,rb_fourth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        manager=getFragmentManager();
        transaction=manager.beginTransaction();
        transaction.add(R.id.content_layout,new FirstFragment());
        transaction.commit();
        initView();
    }
    public void initView(){
        rb_first=findViewById(R.id.rd_first);
        rb_second=findViewById(R.id.rd_second);
        rb_third=findViewById(R.id.rd_third);
        rb_fourth=findViewById(R.id.rd_fourth);
        rb_first.setOnClickListener(this);
        rb_second.setOnClickListener(this);
        rb_third.setOnClickListener(this);
        rb_fourth.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        transaction=manager.beginTransaction();
        switch (view.getId()){
            case R.id.rd_first:
                transaction.replace(R.id.content_layout,new FirstFragment());
                break;
            case R.id.rd_second:
                transaction.replace(R.id.content_layout,new SecondFragment());
                break;
            case R.id.rd_third:
                transaction.replace(R.id.content_layout,new ThirdFragment());
                break;
            case R.id.rd_fourth:
                transaction.replace(R.id.content_layout,new FourthFragment());
                break;
        }
        transaction.commit();

    }
}
