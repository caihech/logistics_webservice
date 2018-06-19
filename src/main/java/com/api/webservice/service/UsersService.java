package com.api.webservice.service;


import com.api.webservice.config.TomcatConfig;
import com.api.webservice.dao.entity.User;
import com.api.webservice.dao.repository.UserRepository;
import com.api.webservice.utils.exception.SC_FORBIDDEN;
import com.api.webservice.utils.exception.SC_NOT_FOUND;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * 用户 user
 *
 * @author zuofei
 */
@Service
public class UsersService extends BaseService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TomcatConfig systemConfig;

    /**
     * 查询所有
     *
     * @return User集合
     */
    public List<User> query() {
        return userRepository.findAll();
    }

    /**
     * 查询一条数据
     *
     * @param user User对象
     * @param id   主键id
     * @return User对象
     */
    public User get(User user, Long id) {
//        User userRet = userRepository.findOne(id);
//
//        if (userRet == null) {
//            throw new SC_NOT_FOUND();
//        }
//
//        if (user.getId() == userRet.getId() || user.getRole().getId() == EnumUtils.Role.ADMINISTRATOR.key) {
//            return userRet;
//        } else {
//            throw new SC_FORBIDDEN();
//        }
        return null;
    }

    /**
     * 添加
     *
     * @param adminUser User对象
     * @param user      User对象
     * @return User对象
     */
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    public User post(User adminUser, User user) {
        return null;
//        //如果公司不存在
//        if (user.getCompanyUsers() == null || user.getCompanyUsers().size() == 0) {
//            throw new SC_BAD_REQUEST();
//        }
//
//        //用户已存在
//        User userExist = userRepository.findByUsername(user.getUsername());
//        if (userExist != null) {
//            throw new SC_CONFLICT();
//        }
//
//        //权限验证
//        Company company = user.getCompanyUsers().get(0).getCompany();
//        if (company == null || company.getId() <= 0) {
//            throw new SC_BAD_REQUEST();
//        }
//
//        boolean bValidCompany = false;
//
//        for (CompanyUser companyUser : adminUser.getCompanyUsers()) {
//            if (companyUser.getAdminPersion().equals(EnumUtils.CompanyUserRole.ADMIN.key) && companyUser.getCompany().getId() == company.getId()) {
//                bValidCompany = true;
//                break;
//            }
//        }
//
//        if (adminUser.getRole().getId() != EnumUtils.Role.ADMINISTRATOR.key && !bValidCompany) {
//            throw new SC_FORBIDDEN();
//        }
//
//        User userRet = new User();
//
//        //事务 创建用户与公司关系
//        try {
//            userRet.setUsername(user.getUsername());
//            userRet.setPassword(CommonUtils.getSha256(user.getPassword()));
//            userRet.setRole(user.getRole());
//            userRet.setFullname(user.getFullname());
//            userRet.setRemark(user.getRemark());
//            userRet.setValid(true);
//
//            userRet = userRepository.saveAndFlush(userRet);
//
//            if (userRet == null) {
//                throw new SC_INTERNAL_SERVER_ERROR();
//            }
//
//            List<CompanyUser> companyUsers = user.getCompanyUsers();
//
//            CompanyUser companyUser = new CompanyUser();
//            companyUser.setCompany(companyUsers.get(0).getCompany());
//            companyUser.setUser(userRet);
//            companyUser.setAdminPersion(EnumUtils.CompanyUserRole.EMPLOYEES.key);
//            companyUser.setContactPersion(0);
//
//            companyUserRepository.saveAndFlush(companyUser);
//        } catch (Exception e) {
//            throw new SC_INTERNAL_SERVER_ERROR();
//        }
//
//        return userRet;
    }

    /**
     * 修改
     *
     * @param user User对象
     * @return User对象
     */
    public User put(User user) {
        return null;
//        User userRet = userRepository.findOne(user.getId());
//
//        userRet.setFullname(user.getFullname());
//        userRet.setMobilephone(user.getMobilephone());
//        userRet.setBirthday(user.getBirthday());
//        userRet.setSex(user.getSex());
//        userRet.setEmail(user.getEmail());
//        userRet.setPostalCode(user.getPostalCode());
//        userRet.setFax(user.getFax());
//        userRet.setTelephone(user.getTelephone());
//        userRet.setWechat(user.getWechat());
//        userRet.setWeibo(user.getWeibo());
//        userRet.setAddress(user.getAddress());
//        userRet.setRemark(user.getRemark());
//
//        userRet = userRepository.saveAndFlush(userRet);
//
//        if (userRet == null) {
//            throw new SC_INTERNAL_SERVER_ERROR();
//        }
//
//        return userRet;
    }


    public boolean delete(long id) {
        return true;
    }


}