package com.api.webservice.service;


import com.api.webservice.config.TomcatConfig;
import com.api.webservice.dao.entity.Login;
import com.api.webservice.dao.entity.Role;
import com.api.webservice.dao.entity.User;
import com.api.webservice.dao.model.UserPasswordModel;
import com.api.webservice.dao.repository.LoginRepository;
import com.api.webservice.dao.repository.UserRepository;
import com.api.webservice.utils.CommonUtils;
import com.api.webservice.utils.EnumUtils;
import com.api.webservice.utils.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户
 *
 * @author h.cai
 * @date 2018/06/20
 */
@Service
public class UsersService extends BaseService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoginRepository loginRepository;
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
     * 获取一条记录
     */
    public User get(User tokenUser, Long id) {

        User userRet = userRepository.findOne(id);
        if (userRet == null) {
            log.error("404 get user is not find.");
            throw new SC_NOT_FOUND();
        }

        if (userRet.getId() != tokenUser.getId()) {
            if (tokenUser.getRole().getId() != EnumUtils.Role.ADMINISTRATOR.key) {
                log.error("403 user not permissions.");
                throw new SC_FORBIDDEN();
            }
        }

        return userRet;
    }

    /**
     * 添加
     *
     * @param user User对象
     * @return User对象
     */
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    public User post(User user) {

        if (user == null || user.getUsername() == null ||
                user.getUsername().length() <= 4 ||
                user.getPassword() == null ||
                user.getPassword().length() < 6 ||
                user.getRole() == null ||
                user.getRole().getId() > 2) {
            log.error("400 post user param is null.");
            throw new SC_BAD_REQUEST();
        }

        User userRet = null;

        try {
            userRet = new User();
            userRet.setUsername(user.getUsername());
            userRet.setPassword(CommonUtils.getSha256(user.getPassword()));
            userRet.setMobilephone(user.getMobilephone());
            userRet.setEmail(user.getEmail());
            userRet.setFullname(user.getFullname());
            userRet.setSex(user.getSex());
            userRet.setPostalCode(user.getPostalCode());
            userRet.setAddress(user.getAddress());
            userRet.setFax(user.getFax());
            userRet.setTelephone(user.getTelephone());
            userRet.setWechat(user.getWechat());
            userRet.setWeibo(user.getWeibo());
            userRet.setBirthday(user.getBirthday());
            userRet.setValid(true);
            userRet.setCompanyName(user.getCompanyName());
            userRet.setRemark(user.getRemark());
            Role role = new Role();
            role.setId(user.getRole().getId());
            userRet.setRole(role);

            userRet = userRepository.saveAndFlush(userRet);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new SC_INTERNAL_SERVER_ERROR();
        }
        return userRet;
    }

    /**
     * 修改
     *
     * @param user User对象
     * @return User对象
     */
    public User put(User tokenUser, User user) {

        if (tokenUser == null || tokenUser.getRole() == null || user == null) {
            log.error("400  put user param is null.");
            throw new SC_BAD_REQUEST();
        }
        User userRet = userRepository.findOne(user.getId());

        if (userRet == null) {
            log.error("404  put user not find.");
            throw new SC_BAD_REQUEST();
        }

        if (user.getId() != tokenUser.getId()) {
            if (tokenUser.getRole().getId() != EnumUtils.Role.ADMINISTRATOR.key) {
                log.error("403  put user role not permissions.");
                throw new SC_BAD_REQUEST();
            }
        }

        userRet.setFullname(user.getFullname());
        userRet.setMobilephone(user.getMobilephone());
        userRet.setBirthday(user.getBirthday());
        userRet.setSex(user.getSex());
        userRet.setEmail(user.getEmail());
        userRet.setPostalCode(user.getPostalCode());
        userRet.setFax(user.getFax());
        userRet.setTelephone(user.getTelephone());
        userRet.setWechat(user.getWechat());
        userRet.setWeibo(user.getWeibo());
        userRet.setAddress(user.getAddress());
        userRet.setRemark(user.getRemark());
        userRet.setCompanyName(user.getCompanyName());
        userRet = userRepository.saveAndFlush(userRet);

        if (userRet == null) {
            throw new SC_INTERNAL_SERVER_ERROR();
        }

        return userRet;
    }


    public boolean delete(long id) {

        User userRet = userRepository.findOne(id);
        if (userRet == null) {
            log.error("404 get user is not find.");
            throw new SC_NOT_FOUND();
        }
        userRet.setValid(false);
        userRepository.save(userRet);
        return true;
    }


    /**
     * 通过有效Token查询用户
     *
     * @param token
     * @return
     */
    public User getEffectiveUserByToken(String token) {

        Login login = loginRepository.findByValidToken(token);
        if (login == null) {
            log.error("401 Token " + token + " 无效");
            throw new SC_UNAUTHORIZED();
        }

        User user = userRepository.findByUsername(login.getUsername());
        if (user == null) {
            log.error("401 token find user is not find.");
            throw new SC_UNAUTHORIZED();
        }

        if (user.isValid() == false) {
            log.error("454  查询无效用过");
            throw new SC_USER_INVALID();
        }

        return user;
    }


    /**
     * 修改密码 只能修改自己的密码
     *
     * @param tokenUser
     * @param userPasswordModel
     * @return
     */
    public User changePassword(User tokenUser, UserPasswordModel userPasswordModel) {

        if (tokenUser == null | userPasswordModel == null || userPasswordModel.getCurrentPassword() == null || userPasswordModel.getNewPassword() == null ||
                userPasswordModel.getCurrentPassword().trim().equals("") || userPasswordModel.getNewPassword().trim().equals("")) {
            log.error("400 params is null.");
            throw new SC_BAD_REQUEST();
        }

        if (userPasswordModel.getCurrentPassword().trim().equals(userPasswordModel.getNewPassword().trim())) {
            log.error("452 Password inconsistency.");
            throw new SC_USER_PASSWORD_ERROR();
        }

        User user = userRepository.findOne(tokenUser.getId());

        if (user == null) {
            log.error("404 user is not find.");
            throw new SC_NOT_FOUND();
        }

        String currentPassword = CommonUtils.getSha256(userPasswordModel.getCurrentPassword());

        //判断旧密码是否一致
        if (!user.getPassword().trim().equals(currentPassword.trim())) {
            log.error("409 Password check failed.");
            throw new SC_CONFLICT();
        }

        //赋值
        user.setPassword(CommonUtils.getSha256(userPasswordModel.getNewPassword()));

        user = userRepository.saveAndFlush(user);

        if (user == null) {
            log.error("500 user save sql is error.");
            throw new SC_INTERNAL_SERVER_ERROR();
        }

        return user;
    }


}