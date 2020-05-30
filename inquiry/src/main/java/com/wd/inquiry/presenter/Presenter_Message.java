package com.wd.inquiry.presenter;

import android.util.Log;

import com.wd.common.base.util.Base.BasePresenter;
import com.wd.common.base.util.Base.IBaseView;
import com.wd.inquiry.bean.CurrentInquiryRecordBean;
import com.wd.inquiry.bean.InquiryRecordListBean;
import com.wd.inquiry.bean.PushMessageBean;
import com.wd.inquiry.icoolor.ICoolor_Message;
import com.wd.inquiry.model.Model_Message;

public class Presenter_Message extends BasePresenter implements ICoolor_Message.IPresenter {

    private Model_Message model;

    public Presenter_Message(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {
        model = new Model_Message();
    }

    @Override
    public void getPushMessage(int inquiryId, String content, int type, int doctorId) {

        model.getPushMessage(inquiryId, content, type, doctorId, new ICoolor_Message.PushMessageCallback() {
            @Override
            public void getSuccess(PushMessageBean pushMessageBean) {
                IBaseView view = getView();
                if (view != null) {
                    ((ICoolor_Message.IView)view).getPushMessageSuccess(pushMessageBean);
                }
            }
        });

    }

    @Override
    public void getInquiryRecordList(int inquiryId, int page, int count) {
        Log.i("ppp","getInquiryRecordList");

        model.getInquiryRecordList(inquiryId, page, count, new ICoolor_Message.InquiryRecordListCallback() {
            @Override
            public void getSuccess(InquiryRecordListBean inquiryRecordListBean) {
                IBaseView view = getView();
                if (view != null) {
                    ((ICoolor_Message.IView)view).getInquiryRecordListSuccess(inquiryRecordListBean);
                }
            }
        });
    }

    @Override
    public void getCurrentInquiryRecord() {
        model.getCurrentInquiryRecord(new ICoolor_Message.CurrentInquiryRecorCallback() {
            @Override
            public void getSuccess(CurrentInquiryRecordBean currentInquiryRecordBean) {
                IBaseView view = getView();
                if (view != null) {
                    ((ICoolor_Message.IView)view).getCurrentInquiryRecordSuccess(currentInquiryRecordBean);
                }
            }
        });
    }
}
