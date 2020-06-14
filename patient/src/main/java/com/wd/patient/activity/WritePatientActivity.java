package com.wd.patient.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.common.base.util.Base.BaseAcitvity;
import com.wd.common.base.util.Base.BasePresenter;
import com.wd.common.base.util.util.HttpUtil;
import com.wd.patient.R;
import com.wd.patient.R2;
import com.wd.patient.adapter.Adapter_Ke;
import com.wd.patient.adapter.IllnessAdapter;
import com.wd.patient.adapter.PicAdapter;
import com.wd.patient.bean.IllnessBean;
import com.wd.patient.bean.KeLieBean;
import com.wd.patient.bean.PublishPatientBean;
import com.wd.patient.contract.WritePatientContract;
import com.wd.patient.presenter.WritePatientPresenter;
import com.wildma.pictureselector.PictureSelector;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class WritePatientActivity extends BaseAcitvity implements WritePatientContract.IView {

    @BindView(R2.id.patient_activity_switch)
    LinearLayout swtich_hint;
    @BindView(R2.id.switch_fingerline)
    Switch aswitch;
    @BindView(R2.id.aaa)
    ImageView aaa;
    @BindView(R2.id.hongdian)
    ImageView hongdian;
    @BindView(R2.id.top_one)
    RelativeLayout topOne;
    @BindView(R2.id.biaoti)
    EditText biaoti;
    @BindView(R2.id.xuan_ke)
    TextView xuanKe;
    @BindView(R2.id.tv1)
    TextView tv1;
    @BindView(R2.id.tv2)
    TextView tv2;
    @BindView(R2.id.tv3)
    TextView tv3;
    @BindView(R2.id.tv4)
    TextView tv4;
    @BindView(R2.id.patient_activity_select_office)
    ImageView patientActivitySelectOffice;
    @BindView(R2.id.xuan_bing_zheng_a)
    TextView xuanBingZhengA;
    @BindView(R2.id.patient_activity_select_illness)
    ImageView patientActivitySelectIllness;
    @BindView(R2.id.bingzheng_xiang)
    EditText bingzhengXiang;
    @BindView(R2.id.yiyuan_name)
    EditText yiyuanName;
    @BindView(R2.id.time_begin)
    TextView timeBegin;
    @BindView(R2.id.patient_activity_start_time)
    ImageView patientActivityStartTime;
    @BindView(R2.id.end_time)
    TextView endTime;
    @BindView(R2.id.patient_activity_end_time)
    ImageView patientActivityEndTime;
    @BindView(R2.id.zhiliao_guocheng)
    EditText zhiliaoGuocheng;
    @BindView(R2.id.patient_activity_write_iv_rv)
    RecyclerView patientActivityWriteIvRv;
    @BindView(R2.id.patient_activity_write_add)
    ImageView patientActivityWriteAdd;
    @BindView(R2.id.bt_fa)
    Button btFa;
    @BindView(R2.id.patient_activity_option_office)
    View patientActivityOptionOffice;
    @BindView(R2.id.patient_activity_option_illness)
    View patientActivityOptionIllness;
    private View popView;
    private PopupWindow popupWindow;
    private View popView_bingZheng;
    private PopupWindow popupWindow_bingZheng;
    private RecyclerView re_bingzheng;
    private RecyclerView re;
    private BasePresenter presenter;
    private Adapter_Ke adapter_ke;
    private IllnessAdapter illnessAdapter;
    private Boolean b = false;
    private int departmentId = 0;
    @BindView(R2.id.patient_activity_amount)
    EditText amount;
    private Boolean publishCheck = false;
    private PicAdapter picAdapter;

    @Override
    protected BasePresenter initPresenter() {
        return new WritePatientPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_write_patient;
    }

    @Override
    protected void initView() {
        //实现 popwindow
        initTopPop();
        initTopPopBingZheng();
        presenter = getPresenter();
        if (presenter instanceof WritePatientPresenter) {
            ((WritePatientPresenter)presenter).showDepartment();
        }
        //图片
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, true);
        patientActivityWriteIvRv.setLayoutManager(linearLayoutManager);
        picAdapter = new PicAdapter(this);
        patientActivityWriteIvRv.setAdapter(picAdapter);
        //科室
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
//        StaggeredGridLayoutManager staggeredGridLayoutManager1 = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.HORIZONTAL);
        re.setLayoutManager(gridLayoutManager);
        adapter_ke = new Adapter_Ke(this);
        re.setAdapter(adapter_ke);
        adapter_ke.setOnClick(new Adapter_Ke.setOnck() {
            @Override
            public void onck(int a, String name) {
                b = true;
                departmentId = a;
                xuanKe.setText(name);
                tv2.setTextColor(Color.parseColor("#ff0099cc"));
                if (presenter instanceof WritePatientPresenter) {
                    ((WritePatientPresenter)presenter).showIllness(a);
                }
                popupWindow.dismiss();
            }
        });

        //病症布局管理器
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.HORIZONTAL);
        re_bingzheng.setLayoutManager(staggeredGridLayoutManager);
        illnessAdapter = new IllnessAdapter(this);
        re_bingzheng.setAdapter(illnessAdapter);
        illnessAdapter.getNameCallBack(new IllnessAdapter.getName() {
            @Override
            public void getName(String name) {
                xuanBingZhengA.setText(name);
                popupWindow_bingZheng.dismiss();
            }
        });
    }

    @Override
    protected void initData() {
        //提升悬赏额度
        aswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                publishCheck = isChecked;
                if (isChecked) {
                    swtich_hint.setVisibility(View.VISIBLE);
                } else {
                    swtich_hint.setVisibility(View.GONE);
                }
            }
        });

        //选择科室
        patientActivitySelectOffice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.showAsDropDown(patientActivityOptionOffice);
            }
        });
        //选择病症
        patientActivitySelectIllness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (b) {
                    popupWindow_bingZheng.showAsDropDown(patientActivityOptionIllness);
                }else {
                    tv2.setTextColor(Color.RED);
                    Toast.makeText(WritePatientActivity.this, "请先选择科室", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //获取开始时间
        getData();
        patientActivityStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        timeBegin.setText(i+"-"+(i1+1)+"-"+i2);      //将选择的日期显示到TextView中,因为之前获取month直接使用，所以不需要+1，这个地方需要显示，所以+1
                    }
                };
                DatePickerDialog dialog=new DatePickerDialog(WritePatientActivity.this, DatePickerDialog.THEME_HOLO_LIGHT,listener,year,month,day);//主题在这里！后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                dialog.show();
            }
        });
        //获取结束时间
        patientActivityEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        endTime.setText(i+"-"+(i1+1)+"-"+i2);      //将选择的日期显示到TextView中,因为之前获取month直接使用，所以不需要+1，这个地方需要显示，所以+1
                    }
                };
                DatePickerDialog dialog=new DatePickerDialog(WritePatientActivity.this, DatePickerDialog.THEME_HOLO_LIGHT,listener,year,month,day);//主题在这里！后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                dialog.show();
            }
        });


        //发布
        btFa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取接口参数
//                String title = biaoti.toString();
//                String disease = xuanBingZhengA.getText().toString();
//                String detail = bingzhengXiang.getText().toString();
//                String treatmentHospital = yiyuanName.getText().toString();
//                String treatmentStartTime = timeBegin.getText().toString();
//                String treatmentEndTime = endTime.getText().toString();
//                String treatmentProcess = zhiliaoGuocheng.getText().toString();
                String am = amount.getText().toString();

                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("title",biaoti.getText().toString());
                    jsonObject.put("departmentId",departmentId);
                    jsonObject.put("disease",xuanBingZhengA.getText().toString());
                    jsonObject.put("detail",bingzhengXiang.getText().toString());
                    jsonObject.put("treatmentHospital",yiyuanName.getText().toString());
                    jsonObject.put("treatmentStartTime",timeBegin.getText().toString());
                    jsonObject.put("treatmentEndTime",endTime.getText().toString());
                    jsonObject.put("treatmentProcess",zhiliaoGuocheng.getText().toString());
                    if (!publishCheck) {
                        jsonObject.put("amount",0);
                    }else {
                        jsonObject.put("amount",Integer.valueOf(am));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //保护
                String s = jsonObject.toString();
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),s);

                if (presenter instanceof WritePatientPresenter) {
                    ((WritePatientPresenter)presenter).publishPatient(requestBody);
                }
            }
        });

        //获取相册图片
        patientActivityWriteAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PictureSelector.create(WritePatientActivity.this, PictureSelector.SELECT_REQUEST_CODE).selectPicture(true, 72, 72, 1, 1);
            }
        });


    }

    //获取当前日期
    private Calendar cal;
    private int year,month,day;
    private void getData() {
        cal= Calendar.getInstance();
        year=cal.get(Calendar.YEAR);       //获取年月日时分秒
        month=cal.get(Calendar.MONTH);   //获取到的月份是从0开始计数
        day=cal.get(Calendar.DAY_OF_MONTH);
    }


    private void initTopPopBingZheng() {
        popView_bingZheng = LayoutInflater.from(this).inflate(R.layout.pop_bing_zheng, null, false);
        popupWindow_bingZheng = new PopupWindow(popView_bingZheng,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        re_bingzheng = popView_bingZheng.findViewById(R.id.re_bingzheng);
        popupWindow_bingZheng.setBackgroundDrawable(new ColorDrawable(0x73888888));
        popupWindow_bingZheng.setTouchable(true);
        popupWindow_bingZheng.setOutsideTouchable(true);
    }

    private void initTopPop() {
        popView = LayoutInflater.from(this).inflate(R.layout.pop_shang_ping, null, false);
        popupWindow = new PopupWindow(popView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        re = popView.findViewById(R.id.re);
        popView.findViewById(R.id.view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
            }
        });
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x73888888));
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
    }

    private void bgAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
    }


    @Override
    public void onShowDepartmentSuccess(KeLieBean keLieBean) {
        if (keLieBean != null) {
            adapter_ke.setData(keLieBean.getResult());
        }
    }

    @Override
    public void onShowDepartmentFailure(String str) {

    }

    @Override
    public void onShowIllnessSuccess(IllnessBean illnessBean) {
        if (illnessBean != null) {
            illnessAdapter.setData(illnessBean.getResult());
        }
    }

    @Override
    public void showIllnessFailure(String str) {

    }

    @Override
    public void onPublishPatientSuccess(PublishPatientBean publishPatientBean) {
        if (publishPatientBean != null) {
            Toast.makeText(this, publishPatientBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPublishPatientFailure(String str) {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK&&requestCode==PictureSelector.SELECT_REQUEST_CODE){
            if (data != null) {
                String stringExtra = data.getStringExtra(PictureSelector.PICTURE_PATH);
                List<String> imageList = new ArrayList<>();
                imageList.add(stringExtra);
                picAdapter.setData(imageList);
                File file = new File(stringExtra);
                ArrayList<File> files = new ArrayList<>();
                files.add(file);
                HashMap<String, String> map = new HashMap<>();
                RequestBody requsetBody = HttpUtil.getInstance().getRequsetBody(files, map);
            }
        }
    }
}
