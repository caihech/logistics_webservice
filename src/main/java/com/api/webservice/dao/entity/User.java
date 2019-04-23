package com.api.webservice.dao.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * 用户表
 *
 * @author h.cai
 * @date 2018/06/20
 */
@Component
@Entity
@Table(name = "user")
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class User extends BaseEntity {

    //region username  用户名 不可变更 16
    private String username;

    @Column(name = "username", length = 16, nullable = false, unique = true, updatable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    //endregion

    //region password 密码采用SHA256单向加密 64
    private String password;

    @Column(name = "password", length = 64, nullable = false)
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty("password")
    public void setPassword(String password) {
        this.password = password;
    }
    //endregion

    //region mobilephone 移动电话 todo 后期可以作为登陆  32
    private String mobilephone;

    @Column(name = "mobilephone", length = 32)
    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }
    //endregion

    //region email  电子邮件 todo 后期可以作为登陆 64
    private String email;

    @Column(name = "email", length = 64)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    //endregion

    //region fullname 真名 32
    private String fullname;

    @Column(name = "fullname", length = 32)
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    //endregion

    //region sex 性别 0保密 1男 2女
    private int sex;

    @Column(name = "sex", columnDefinition = "int default 0 ", nullable = false)
    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
    //endregion

    //region postalCode 邮编 16
    private String postalCode;

    @Column(name = "postal_code", length = 16)
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    //endregion

    //region address 通讯地址 256
    private String address;

    @Column(name = "address", length = 256)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    //endregion

    //region fax 传真 32
    private String fax;

    @Column(name = "fax", length = 32)
    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }
    //endregion

    //region telephone 座机号码 32
    private String telephone;

    @Column(name = "telephone", length = 32)
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    //endregion

    //region wechat 微信 64
    private String wechat;

    @Column(name = "wechat", length = 64)
    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }
    //endregion

    //region weibo 微博 128
    private String weibo;

    @Column(name = "weibo", length = 128)
    public String getWeibo() {
        return weibo;
    }

    public void setWeibo(String weibo) {
        this.weibo = weibo;
    }
    //endregion

    //region birthday 生日
    private Timestamp birthday;

    @Column(name = "birthday", columnDefinition = "TIMESTAMP null default null")
    public Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }
    //endregion

    //region valid 是否有效 0否 1是
    private boolean valid;

    @Column(name = "valid", columnDefinition = "bit default 0 ", nullable = false)
    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
    //endregion

    //region remark 备注 256
    private String remark;

    @Column(name = "remark", length = 256)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    //endregion

    //region companyName 公司名称 120
    private String companyName;

    @Column(name = "company_name", length = 120)
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    //endregion

    //region role
    private Role role;

    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    @LazyToOne(value = LazyToOneOption.FALSE)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    //endregion

    //region consignmentNotes_list
    private List<ConsignmentNote> consignmentNotes;

    @JsonIgnore
    @OneToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY, mappedBy = "user")
    @LazyCollection(value = LazyCollectionOption.EXTRA)
    public List<ConsignmentNote> getConsignmentNotes() {
        return consignmentNotes;
    }

    public void setConsignmentNotes(List<ConsignmentNote> consignmentNotes) {
        this.consignmentNotes = consignmentNotes;
    }
    //endregion

}
