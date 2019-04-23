package com.api.webservice.dao.model;

/**
 * Created by caihe on 2018/10/18.
 */
public class UserPasswordModel {

    //region currentPassword 当前密码
    private String currentPassword;

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }
    //endregion

    //region newPassword 新密码
    private String newPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    //endregion
}
