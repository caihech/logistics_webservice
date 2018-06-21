package com.api.webservice.service;


import com.api.webservice.dao.entity.Login;
import com.api.webservice.dao.entity.User;
import com.api.webservice.dao.repository.LoginLogRepository;
import com.api.webservice.utils.RandomUtil;
import com.api.webservice.utils.exception.SC_BAD_REQUEST;
import com.api.webservice.utils.exception.SC_INTERNAL_SERVER_ERROR;
import com.api.webservice.utils.exception.SC_NOT_FOUND;
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
    private LoginLogRepository loginLogRepository;


    /**
     * 查询所有
     *
     * @return list
     */
    public List<Login> query() {
        return loginLogRepository.findAll();
    }


    /**
     * 查询一条数据
     *
     * @param id 主键
     * @return empty
     */
    public Login get(Long id) {

        Login login = loginLogRepository.findOne(id);

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
            login.setState(1);
            login.setToken(UUID.randomUUID().toString());
            //TODO 有效时间参数化
            Timestamp timestamp = new Timestamp(System.currentTimeMillis() + 100000);
            login.setTokenExpired(timestamp);

            login = loginLogRepository.saveAndFlush(login);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new SC_INTERNAL_SERVER_ERROR();
        }

        return login;

    }


    public User getEffectiveUserByToken(String token) {

        Login login = loginLogRepository.findByToken(token);
        //if(loginLog==null||)
        return null;
    }

}