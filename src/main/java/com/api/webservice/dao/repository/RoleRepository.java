package com.api.webservice.dao.repository;

import com.api.webservice.dao.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * 角色
 *
 * @author h.cai
 * @date 2018/06/20
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {


}
