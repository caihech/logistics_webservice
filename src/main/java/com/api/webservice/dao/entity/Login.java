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

    //region code 6
    private String code;

    @Column(name = "code", length = 6)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    //endregion

    //region ip 18
    private String ip;

    @Column(name = "ip", length = 18)
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    //endregion

    //region username 50
    private String username;

    @Column(name = "username", length = 50)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    //endregion

    //region address 100
    private String address;

    @Column(name = "address", length = 100)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    //endregion

    //region client 300
    private String client;

    @Column(name = "client", length = 300)
    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }
    //endregion

    //region token 36
    private String token;

    @Column(name = "token", length = 36, nullable = false, unique = true, updatable = false)
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    //endregion

    //region tokenExpired
    private Timestamp tokenExpired;

    @Column(name = "token_expired", columnDefinition = "TIMESTAMP null default null")
    public Timestamp getTokenExpired() {
        return tokenExpired;
    }

    public void setTokenExpired(Timestamp tokenExpired) {
        this.tokenExpired = tokenExpired;
    }
    //endregion

    //region status 登录状态码  0 = 未使用， 1=成功 ， 2=失败 ， 3=锁定
    private Integer status;

    @Column(name = "status", columnDefinition = "int default 0", nullable = false)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    //endregion

    //region password 扩展不保存数据库
    private String password;

    @Transient
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    //endregion

}
