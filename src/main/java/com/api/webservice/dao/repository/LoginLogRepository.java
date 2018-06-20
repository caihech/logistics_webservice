package com.api.webservice.dao.repository;

import com.api.webservice.dao.entity.LoginLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * 登录日志
 *
 * @author h.cai
 * @date 2018/06/20
 */
@Repository
public interface LoginLogRepository extends JpaRepository<LoginLog, Long> {


}
