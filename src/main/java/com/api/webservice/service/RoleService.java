package com.api.webservice.service;


import com.api.webservice.dao.entity.Role;
import com.api.webservice.dao.repository.RoleRepository;
import com.api.webservice.utils.exception.SC_NOT_FOUND;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色
 *
 * @author h.cai
 * @date 2018/06/20
 */
@Service
public class RoleService extends BaseService {

    @Autowired
    private RoleRepository roleRepository;


    /**
     * 查询所有
     *
     * @return list
     */
    public List<Role> query() {
        return roleRepository.findAll();
    }


    /**
     * 查询一条数据
     *
     * @param id 主键
     * @return empty
     */
    public Role get(Long id) {

        Role role = roleRepository.findOne(id);

        if (role == null) {
            log.error("404 role is not find.");
            throw new SC_NOT_FOUND();
        }

        return role;
    }


}