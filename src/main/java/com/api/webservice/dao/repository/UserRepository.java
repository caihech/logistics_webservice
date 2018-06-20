package com.api.webservice.dao.repository;

import com.api.webservice.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 用户
 *
 * @author h.cai
 * @date 2018/06/20
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByUsernameAndPasswordAndValid(String username, String password, boolean valid);
}
