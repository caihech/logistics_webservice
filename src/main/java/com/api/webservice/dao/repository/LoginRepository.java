package com.api.webservice.dao.repository;

import com.api.webservice.dao.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * 登录日志
 *
 * @author h.cai
 * @date 2018/06/20
 */
@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {

    /**
     * 通过Token查询数据
     *
     * @param token
     * @return
     */
    Login findByToken(String token);

}
