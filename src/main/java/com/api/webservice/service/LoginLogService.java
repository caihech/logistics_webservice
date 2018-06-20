package com.api.webservice.service;


import com.api.webservice.dao.entity.LoginLog;
import com.api.webservice.dao.entity.VehicleStatus;
import com.api.webservice.dao.repository.LoginLogRepository;
import com.api.webservice.dao.repository.VehicleStatusRepository;
import com.api.webservice.utils.exception.SC_NOT_FOUND;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 登录日志
 *
 * @author h.cai
 * @date 2018/06/20
 */
@Service
public class LoginLogService extends BaseService {

    @Autowired
    private LoginLogRepository loginLogRepository;


    /**
     * 查询所有
     *
     * @return list
     */
    public List<LoginLog> query() {
        return loginLogRepository.findAll();
    }


    /**
     * 查询一条数据
     *
     * @param id 主键
     * @return empty
     */
    public LoginLog get(Long id) {

        LoginLog loginLog = loginLogRepository.findOne(id);

        if (loginLog == null) {
            log.error("loginLog is not find.");
            throw new SC_NOT_FOUND();
        }

        return loginLog;
    }


}