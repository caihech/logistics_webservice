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
    private Role Role;
    private String username;
    private String password;
    private String mobilephone;
    private String email;
    private String fullname;
    private int sex;
    private String postalCode;
    private String address;
    private String fax;
    private String telephone;
    private String wechat;
    private String weibo;
    private Timestamp birthday;
    private boolean valid;
    private String remark;
    private String companyName;

    private List<ConsignmentNote> consignmentNotes;


    /**
     * fk_Role_id
     *
     * @return
     */
    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "Role_id", referencedColumnName = "id", nullable = false)
    @LazyToOne(value = LazyToOneOption.FALSE)
    public Role getRole() {
        return Role;
    }

    public void setRole(Role Role) {
        this.Role = Role;
    }


    /**
     * 用户名 不可变更
     */
    @Column(name = "username", length = 16, nullable = false, unique = true, updatable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 密码采用SHA256单向加密
     */
    @Column(name = "password", length = 64, nullable = false)
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty("password")
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 移动电话 todo 后期可以作为登陆
     */
    @Column(name = "mobilephone", length = 32)
    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    /**
     * 电子邮件 todo 后期可以作为登陆
     */
    @Column(name = "email", length = 64)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 真名
     */
    @Column(name = "fullname", length = 32)
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     * 性别 0保密 1男 2女 todo 添加约束
     */
    @Column(name = "sex", columnDefinition = "int default 0 ", nullable = false)
    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    /**
     * 邮编
     */
    @Column(name = "postal_code", length = 16)
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * 通信地址
     */
    @Column(name = "address", length = 256)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 传真
     */
    @Column(name = "fax", length = 32)
    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * 座机
     */
    @Column(name = "telephone", length = 32)
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * 微信
     */
    @Column(name = "wechat", length = 64)
    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    /**
     * 微博
     */
    @Column(name = "weibo", length = 128)
    public String getWeibo() {
        return weibo;
    }

    public void setWeibo(String weibo) {
        this.weibo = weibo;
    }


    /**
     * 生日
     */
    @Column(name = "birthday", columnDefinition = "TIMESTAMP null default null")
    public Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    /**
     * 有效用户 0否1是
     */
    @Column(name = "valid", columnDefinition = "bit default 0 ", nullable = false)
    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    /**
     * 备注
     */
    @Column(name = "remark", length = 256)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    /**
     * 公司名称
     */
    @Column(name = "company_name", length = 120)
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * ConsignmentNote list
     */
    @JsonIgnore
    @OneToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY, mappedBy = "user")
    @LazyCollection(value = LazyCollectionOption.EXTRA)
    public List<ConsignmentNote> getConsignmentNotes() {
        return consignmentNotes;
    }

    public void setConsignmentNotes(List<ConsignmentNote> consignmentNotes) {
        this.consignmentNotes = consignmentNotes;
    }

}
