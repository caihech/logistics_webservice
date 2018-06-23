package com.api.webservice.dao.repository;

import com.api.webservice.dao.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;


/**
 * 登录日志
 *
 * @author h.cai
 * @date 2018/06/20
 */
@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {

    @Query(value = "select * from login where token =?1 and status =1 and token_expired > now()", nativeQuery = true)
    Login findByValidToken(String token);

}
