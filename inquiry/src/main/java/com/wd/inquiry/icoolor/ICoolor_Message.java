package com.wd.inquiry.icoolor;

import com.wd.common.base.util.Base.IBaseView;
import com.wd.inquiry.bean.ConsultDoctorBean;
import com.wd.inquiry.bean.CurrentInquiryRecordBean;
import com.wd.inquiry.bean.InquiryRecordListBean;
import com.wd.inquiry.bean.PushMessageBean;

public interface ICoolor_Message {
    interface IView extends IBaseView{
        //发送
        void getPushMessageSuccess(PushMessageBean pushMessageBean);
        //历史
        void getInquiryRecordListSuccess(InquiryRecordListBean inquiryRecordListBean);
        //当前问诊医生
        void  getCurrentInquiryRecordSuccess(CurrentInquiryRecordBean currentInquiryRecordBean);
    }
    interface IPresenter{
        void getPushMessage(int inquiryId, String content,int type,int doctorId);
        void getInquiryRecordList(int inquiryId, int page,int count);
        void getCurrentInquiryRecord();
    }
    interface IModel{
        void getPushMessage(int inquiryId, String content,int type,int doctorId, PushMessageCallback pushMessageCallback);
        void getInquiryRecordList(int inquiryId, int page,int count, InquiryRecordListCallback inquiryRecordListCallback);
        void getCurrentInquiryRecord(CurrentInquiryRecorCallback currentInquiryRecorCallback);
    }
    interface PushMessageCallback{
        void getSuccess(PushMessageBean pushMessageBean);
    }
    interface InquiryRecordListCallback{
        void getSuccess(InquiryRecordListBean inquiryRecordListBean);
    }
    interface CurrentInquiryRecorCallback{
        void getSuccess(CurrentInquiryRecordBean currentInquiryRecordBean);
    }
}
