package com.api.webservice.dao.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;


/**
 * 登录记录表

 * @author h.cai
 * @date 2018/06/20
 */
@Component
@Entity
@Table(name = "login_log")
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class LoginLog extends BaseEntity {

    private String code;
    private String ip;
    private String username;
    private Integer state;
    private String address;
    private String client;
    private String token;
    private Timestamp tokenExpired;
   // private User user;

    @Column(name = "code", length = 6)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "ip", length = 18)
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Column(name = "username", length = 50)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 登录状态码 NULL 无 0 失败 1成功 2锁定
     */
    @Column(name = "state", nullable = true)
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Column(name = "address", length = 100)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "client", length = 300)
    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    @Column(name = "token", length = 36, nullable = true)
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Column(name = "token_expired", columnDefinition = "TIMESTAMP null default null")
    public Timestamp getTokenExpired() {
        return tokenExpired;
    }

    public void setTokenExpired(Timestamp tokenExpired) {
        this.tokenExpired = tokenExpired;
    }

//    /**
//     * FK_User
//     */
//    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = true)
//    @LazyToOne(value = LazyToOneOption.FALSE)
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }


}
