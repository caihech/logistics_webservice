package com.api.webservice.service;


import com.api.webservice.dao.entity.Login;
import com.api.webservice.dao.entity.User;
import com.api.webservice.dao.repository.LoginRepository;
import com.api.webservice.dao.repository.UserRepository;
import com.api.webservice.utils.CommonUtils;
import com.api.webservice.utils.EnumUtils;
import com.api.webservice.utils.RandomUtil;
import com.api.webservice.utils.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

/**
 * 登录日志
 *
 * @author h.cai
 * @date 2018/06/20
 */
@Service
public class LoginService extends BaseService {

    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private UserRepository userRepository;

    //验证码过期 5分钟
    private long verificationCodeExpiredTime = 300000;
    //Token 过期时间为 4小时
    private long tokenExpiredTime = 14400000;

    /**
     * 查询所有
     *
     * @return list
     */
    public List<Login> query() {
        return loginRepository.findAll();
    }


    /**
     * 查询一条数据
     *
     * @param id 主键
     * @return empty
     */
    public Login get(Long id) {

        Login login = loginRepository.findOne(id);

        if (login == null) {
            log.error("loginLog is not find.");
            throw new SC_NOT_FOUND();
        }

        return login;
    }

    /**
     * 获取登录验证码
     *
     * @param ip
     * @param client
     * @return
     */
    public Login getCode(String ip, String client) {
        if (ip == null || ip.isEmpty()) {
            log.error("400 ip is null.");
            throw new SC_BAD_REQUEST();
        }
        Login login = null;
        try {
            RandomUtil randomUtil = RandomUtil.getInstance();
            login = new Login();
            login.setIp(ip);
            login.setCode(randomUtil.getCapitalOrNumber(4));
            login.setClient(client);
            login.setStatus(0);
            login.setToken(UUID.randomUUID().toString());
            login.setTokenExpired(new Timestamp(System.currentTimeMillis()));
            login = loginRepository.saveAndFlush(login);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new SC_INTERNAL_SERVER_ERROR();
        }

        login.setToken(null);
        login.setTokenExpired(null);
        return login;

    }

    /**
     * 用户登录
     *
     * @param login
     * @param ip
     * @return
     */
    public Login post(Login login, String ip) {

        //检查入参数据结
        if (login == null || login.getUsername() == null || login.getPassword() == null || login.getCode() == null || "".equals(ip)) {
            log.error("400 入参数据异常.");
            throw new SC_BAD_REQUEST();
        }

        //获取登录记录
        Login loginRet = loginRepository.findOne(login.getId());

        if (loginRet == null || loginRet.getStatus() != 0 || login.getCode().equals(loginRet.getCode()) == false) {
            log.error("451 验证码错误.");
            throw new SC_VERIFICATION_CODE_ERROR();
        }

        if (System.currentTimeMillis() > (loginRet.getCreateTime().getTime() + verificationCodeExpiredTime)) {
            log.error("450 验证码已过期.");
            throw new SC_VERIFICATION_CODE_EXPIRED();
        }

        //TODO 连续登录错误锁定用户

        String pa=CommonUtils.getSha256(login.getPassword());

        //查询用户
        User user = userRepository.findByUsernameAndPasswordAndValid(
                login.getUsername(),
                CommonUtils.getSha256(login.getPassword()), true);

        if (user == null) {
            //登录失败
            loginRet.setUsername(login.getUsername());
            loginRet.setStatus(EnumUtils.LoginStatus.FAILURE.key);
            loginRepository.save(loginRet);
            log.error("452 用户密码错误.");
            throw new SC_USER_PASSWORD_ERROR();
        }

        //登录成功
        loginRet.setUsername(login.getUsername());
        loginRet.setStatus(EnumUtils.LoginStatus.SUCCESS.key);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis() + tokenExpiredTime);
        loginRet.setTokenExpired(timestamp);
        loginRet = loginRepository.save(loginRet);
        return loginRet;
    }

}