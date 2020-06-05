package com.wd.my.bean;

/**
 * @ProjectName: WeiDuHealth
 * @Package: com.wd.minemodule.bean
 * @ClassName: AddArchivesBean
 * @Description:
 * @Author: 李泽晋
 * @CreateDate: 2020.5.22 0022 17:37
 * @UpdateUser:
 * @UpdateDate: 2020.5.22 0022 17:37
 * @UpdateRemark:
 * @Version: 1.0
 */
public class AddArchivesBean {

    /**
     * message : 档案添加成功
     * status : 0000
     */

    private String message;
    private String status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
