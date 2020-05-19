package com.wd.mm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//import com.alibaba.android.arouter.facade.annotation.Route;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

//@Route(path = "/mm/MainActivity")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //中发射点发生口角
//        ARouter.getInstance().inject(this);
    }
}
