package com.api.webservice.controller;

import com.api.webservice.annotation.UserAnnotation;
import com.api.webservice.dao.entity.User;
import com.api.webservice.service.UsersService;
import com.api.webservice.utils.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * 用户控制器
 *
 * @author
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
    @UserAnnotation(roles = {EnumUtils.Role.ADMINISTRATOR})
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
    @UserAnnotation(roles = {EnumUtils.Role.ADMINISTRATOR, EnumUtils.Role.USER})
    @RequestMapping(value = "/{id}", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.GET)
    public User get(@PathVariable("id") Long id) throws Exception {
        User user = usersService.get(null, id);
        setHttpResponseStatus(HttpServletResponse.SC_OK);
        return user;
    }

    /**
     * 添加
     *
     * @return User
     * @throws Exception 401无效token,403没有权限
     */
    @UserAnnotation(roles = {EnumUtils.Role.ADMINISTRATOR})
    @RequestMapping(value = "", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.POST)
    public User post(@RequestBody User user) throws Exception {
        User userRet = usersService.post(user, user);
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
    @UserAnnotation(roles = {EnumUtils.Role.ADMINISTRATOR})
    @RequestMapping(value = "/{id}", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.PUT)
    public User put(@PathVariable("id") Long id, @RequestBody User user) throws Exception {
        User userRet = null;

        if (id == user.getId()) {
            userRet = usersService.put(user);
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
    @UserAnnotation(roles = {EnumUtils.Role.ADMINISTRATOR})
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) throws Exception {
        usersService.delete(id);
        setHttpResponseStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}