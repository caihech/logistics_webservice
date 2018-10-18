package com.api.webservice.controller;

import com.api.webservice.annotation.UserAnnotation;
import com.api.webservice.dao.entity.User;
import com.api.webservice.dao.model.UserPasswordModel;
import com.api.webservice.service.UsersService;
import com.api.webservice.utils.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * 用户
 *
 * @author h.cai
 * @date 2018/06/20
 */
@CrossOrigin
@RestController
@RequestMapping("/users")
public class UsersController extends BaseController {

    @Autowired
    private UsersService usersService;

    /**
     * 查询所有
     *
     * @return UserList
     * @throws Exception 401无效token,403没有权限
     */
    @UserAnnotation(Roles = {EnumUtils.Role.ADMINISTRATOR})
    @RequestMapping(value = "", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.GET)
    public List<User> query() throws Exception {
        List<User> userList = usersService.query();
        setHttpResponseStatus(HttpServletResponse.SC_OK);
        return userList;
    }

    /**
     * 查询一条数据
     *
     * @param id 主键id
     * @return User
     * @throws Exception 401无效token,403没有权限,404不存在
     */
    @UserAnnotation(Roles = {EnumUtils.Role.ADMINISTRATOR, EnumUtils.Role.USER})
    @RequestMapping(value = "/{id}", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.GET)
    public User get(@PathVariable("id") Long id) throws Exception {
        User user = usersService.get(this.tokenUser, id);
        setHttpResponseStatus(HttpServletResponse.SC_OK);
        return user;
    }

    /**
     * 添加
     *
     * @return User
     * @throws Exception 401无效token,403没有权限
     */
    @UserAnnotation(Roles = {EnumUtils.Role.ADMINISTRATOR})
    @RequestMapping(value = "", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.POST)
    public User post(@RequestBody User user) throws Exception {
        User userRet = usersService.post(user);
        setHttpResponseStatus(HttpServletResponse.SC_CREATED);
        return userRet;
    }

    /**
     * 修改
     *
     * @param id 主键id
     * @return User
     * @throws Exception 400参数错误,401无效token,403没有权限
     */
    @UserAnnotation(Roles = {EnumUtils.Role.ADMINISTRATOR, EnumUtils.Role.USER})
    @RequestMapping(value = "/{id}", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.PUT)
    public User put(@PathVariable("id") Long id, @RequestBody User user) throws Exception {
        User userRet = null;

        if (id == user.getId()) {
            userRet = usersService.put(tokenUser, user);
            setHttpResponseStatus(HttpServletResponse.SC_OK);
        } else {
            setHttpResponseStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        return userRet;
    }

    /**
     * 删除
     *
     * @param id 主键id
     * @throws Exception 400参数错误,401无效token,403没有权限
     */
    @UserAnnotation(Roles = {EnumUtils.Role.ADMINISTRATOR})
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) throws Exception {
        usersService.delete(id);
        setHttpResponseStatus(HttpServletResponse.SC_NO_CONTENT);
    }


    /**
     * 通过Token获取用户信息
     *
     * @return User
     * @throws Exception 401无效token,403没有权限,404不存在
     */
    @UserAnnotation(Roles = {EnumUtils.Role.ADMINISTRATOR, EnumUtils.Role.USER})
    @RequestMapping(value = "/token", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.GET)
    public User getUserByToken() throws Exception {
        setHttpResponseStatus(HttpServletResponse.SC_OK);
        return this.tokenUser;
    }


    /**
     * 修改密码 只能修改当前用户密码
     *
     * @param userPasswordModel
     * @return
     * @throws Exception 401无效token,403没有权限 400 ,404,409 ,452
     */
    @UserAnnotation(Roles = {EnumUtils.Role.ADMINISTRATOR, EnumUtils.Role.USER})
    @RequestMapping(value = "/password", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.PUT)
    public User changePassword(@RequestBody UserPasswordModel userPasswordModel) throws Exception {
        User userRet = null;
        if (super.tokenUser == null) {
            setHttpResponseStatus(HttpServletResponse.SC_FORBIDDEN);
        } else {
            userRet = usersService.changePassword(super.tokenUser, userPasswordModel);
            setHttpResponseStatus(HttpServletResponse.SC_OK);
        }
        return userRet;
    }


}