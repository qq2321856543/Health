package com.wd.my.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wd.common.base.util.Base.BaseAcitvity;
import com.wd.common.base.util.Base.BasePresenter;
import com.wd.common.base.util.util.RetrofitUtil;
import com.wd.my.R;
import com.wd.my.R2;
import com.wd.my.bean.AddUserArchivesBean;
import com.wd.my.bean.Bean;
import com.wd.my.bean.UpImageBean;
import com.wd.my.contract.ICoolor_TwoUserArchives;
import com.wd.my.persenter.Presenter_TwoUserArchives;
import com.wd.my.view.CustomDatePickerDialogFragment;
import com.wildma.pictureselector.PictureSelector;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class UserArchivesActivity extends BaseAcitvity implements ICoolor_TwoUserArchives.IVew,View.OnClickListener, CustomDatePickerDialogFragment.OnSelectedDateListener{


    long day = 24 * 60 * 60 * 1000;
    @BindView(R2.id.et_zheng)
    EditText et_zheng;
    @BindView(R2.id.et_xian)
    EditText et_xian;
    @BindView(R2.id.et_wang)
    EditText et_wang;
    @BindView(R2.id.et_zui)
    EditText et_zui;
    @BindView(R2.id.et_kai)
    EditText et_kai;
    @BindView(R2.id.et_jie)
    EditText et_jie;
    @BindView(R2.id.iv_kai)
    ImageView iv_kai;
    @BindView(R2.id.iv_jie)
    ImageView iv_jie;
    @BindView(R2.id.et_guo)
    EditText et_guo;
    @BindView(R2.id.iv_jia)
    ImageView iv_jia;
    @BindView(R2.id.ll)
    LinearLayout ll;
    @BindView(R2.id.tv_ok)
    TextView tv_ok;
    Boolean is=true;
    List<File> files = new ArrayList<>();

    @Override
    protected BasePresenter initPresenter() {
        return new Presenter_TwoUserArchives(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_user_archives;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        iv_kai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                is=true;
            showDatePickDialog();
            }
        });
        iv_jie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                is=false;
                showDatePickDialog();
            }
        });
        iv_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PictureSelector
                        .create(UserArchivesActivity.this,PictureSelector.SELECT_REQUEST_CODE)
                        .selectPicture(true,200,200,1,1);
            }
        });
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bean bean = new Bean();
                bean.setDiseaseMain(et_zheng.getText().toString());
                bean.setDiseaseNow(et_xian.getText().toString());
                bean.setDiseaseBefore(et_wang.getText().toString());
                bean.setTreatmentHospitalRecent(et_zui.getText().toString());
                bean.setTreatmentProcess(et_guo.getText().toString());
                bean.setTreatmentStartTime(et_kai.getText().toString());
                bean.setTreatmentEndTime(et_jie.getText().toString());
                Gson gson = new Gson();
                String json = gson.toJson(bean);
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
                RequestBody body = RetrofitUtil.getInstance().getRequsetBody(files, new HashMap<>());
                BasePresenter presenter = getPresenter();
                if (presenter != null) {
                    ((ICoolor_TwoUserArchives.IPresenter)presenter).getAddUserArchives(requestBody);
                    ((ICoolor_TwoUserArchives.IPresenter)presenter).getUpImage(body);
                }
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
        if (is){
            et_kai.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth+"-");
        }else {
            et_jie.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth+"-");
        }
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK&&requestCode==PictureSelector.SELECT_REQUEST_CODE){
            if (data != null) {
                String stringExtra = data.getStringExtra(PictureSelector.PICTURE_PATH);
                File file = new File(stringExtra);
                files.add(file);
                ImageView imageView = new ImageView(UserArchivesActivity.this);
                LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(5,0,5,0);
                imageView.setImageURI(Uri.fromFile(new File(stringExtra)));
                ll.addView(imageView,layoutParams);
//                File file = new File(stringExtra);
//                ArrayList<File> files = new ArrayList<>();
//                files.add(file);
//                HashMap<String, String> map = new HashMap<>();
//                BasePresenter presenter = getPresenter();

            }
        }
    }

    @Override
    public void getAddUserArchives(AddUserArchivesBean addUserArchivesBean) {
        Toast.makeText(this, ""+addUserArchivesBean.getMessage(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(UserArchivesActivity.this, UserArchivesOneMainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void getUpImage(UpImageBean upImageBean) {
        Toast.makeText(this, ""+upImageBean.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
