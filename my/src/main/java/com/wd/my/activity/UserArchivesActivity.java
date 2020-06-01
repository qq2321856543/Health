package com.wd.my.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.wd.common.base.util.Base.BaseAcitvity;
import com.wd.common.base.util.Base.BasePresenter;
import com.wd.my.R;
import com.wd.my.R2;
import com.wd.my.view.CustomDatePickerDialogFragment;

import java.util.Calendar;

import butterknife.BindView;

public class UserArchivesActivity extends BaseAcitvity implements View.OnClickListener, CustomDatePickerDialogFragment.OnSelectedDateListener{


    private ImageView iv;
    long day = 24 * 60 * 60 * 1000;
    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_user_archives;
    }

    @Override
    protected void initView() {
        iv = findViewById(R.id.iv);
    }

    @Override
    protected void initData() {
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserArchivesActivity.this, "点击了", Toast.LENGTH_SHORT).show();
            showDatePickDialog();
            }
        });
    }

    @Override
    public void onClick(View v) {
//        if (v.getId()==R.id.iv){
//            Toast.makeText(this, "点击了", Toast.LENGTH_SHORT).show();
//            showDatePickDialog();
//        }
    }

    @Override
    public void onSelectedDate(int year, int monthOfYear, int dayOfMonth) {
        Toast.makeText(this,year+"年"+(monthOfYear+1)+"月"+dayOfMonth+"日",Toast.LENGTH_SHORT).show();
    }
    private void showDatePickDialog() {
        CustomDatePickerDialogFragment fragment = new CustomDatePickerDialogFragment();
        fragment.setOnSelectedDateListener(this);
        Bundle bundle = new Bundle();
        Calendar currentDate = Calendar.getInstance();
        currentDate.setTimeInMillis(System.currentTimeMillis());
        currentDate.set(Calendar.HOUR_OF_DAY,0);
        currentDate.set(Calendar.MINUTE,0);
        currentDate.set(Calendar.SECOND,0);
        currentDate.set(Calendar.MILLISECOND,0);
        bundle.putSerializable(CustomDatePickerDialogFragment.CURRENT_DATE,currentDate);


        long start = currentDate.getTimeInMillis() - day * 2;
        long end = currentDate.getTimeInMillis() - day;
        Calendar startDate = Calendar.getInstance();
        startDate.setTimeInMillis(start);
        Calendar endDate = Calendar.getInstance();
        endDate.setTimeInMillis(end);
        bundle.putSerializable(CustomDatePickerDialogFragment.START_DATE,startDate);
        bundle.putSerializable(CustomDatePickerDialogFragment.END_DATE,currentDate);

        fragment.setArguments(bundle);
        fragment.show(getSupportFragmentManager(),CustomDatePickerDialogFragment.class.getSimpleName());
    }
}
