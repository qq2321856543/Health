package com.wd.login.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.wd.login.R;
import com.wd.login.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

//import com.alibaba.android.arouter.launcher.ARouter;

public class LoginLonginActivity extends AppCompatActivity {

    @BindView(R2.id.tv)
    TextView tv;
    @BindView(R2.id.iv)
    ImageView iv;
//    private TextView tv;
//    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_longin);
        ButterKnife.bind(this);
        tv = findViewById(R.id.tv);
        iv = findViewById(R.id.iv);
        String str = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1577442815232&di=49d50a07a4760c0ec89ddc3651e2b2e2&imgtype=0&src=http%3A%2F%2Fimg.pconline.com.cn%2Fimages%2Fupload%2Fupc%2Ftx%2Fwallpaper%2F1301%2F14%2Fc1%2F17389236_1358146562800.jpg";
        Glide.with(this).load(str).into(iv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginLonginActivity.this, "sdfs", Toast.LENGTH_SHORT).show();
                ARouter.getInstance().build("/mm/MainActivity").navigation();
                // FragmentManager fragmentManager = getSupportFragmentManager();


                //李聪聪分支
            }
        });
    }
}
