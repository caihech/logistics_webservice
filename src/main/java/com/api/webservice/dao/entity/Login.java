package com.api.webservice.dao.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.sql.Timestamp;


/**
 * 登录记录表
 *
 * @author h.cai
 * @date 2018/06/20
 */
@Component
@Entity
@Table(name = "login")
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class Login extends BaseEntity {

    private String code;
    private String ip;
    private String username;
    private String address;
    private String client;
    private String token;
    private Timestamp tokenExpired;
    private String password;
    private Integer status;

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
     * 登录状态码  null = 未使用， 1==成功 ， 0==失败 ， 2==锁定
     */
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    @Column(name = "token", length = 36, nullable = false, unique = true, updatable = false)
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

    @Transient
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
