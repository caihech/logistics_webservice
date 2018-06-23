package com.api.webservice.controller;

import com.api.webservice.annotation.UserAnnotation;
import com.api.webservice.dao.entity.Login;
import com.api.webservice.service.LoginService;
import com.api.webservice.utils.CommonUtils;
import com.api.webservice.utils.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * 登录日志
 *
 * @author h.cai
 * @date 2018/06/20
 */
@CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginController extends BaseController {

    @Autowired
    private LoginService loginService;

    /**
     * 查询所有
     *
     * @return List
     * @throws Exception 401无效token,403没有权限
     */
    @UserAnnotation(roles = {EnumUtils.ROLE.ADMINISTRATOR})
    @RequestMapping(value = "/log", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.GET)
    public List<Login> query() throws Exception {
        List<Login> loginList = loginService.query();
        setHttpResponseStatus(HttpServletResponse.SC_OK);
        return loginList;
    }

    /**
     * 查询一条数据
     *
     * @param id 主键id
     * @return Empty
     * @throws Exception 401无效token,403没有权限,404不存在
     */
    @UserAnnotation(roles = {EnumUtils.ROLE.ADMINISTRATOR})
    @RequestMapping(value = "/log/{id}", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.GET)
    public Login get(@PathVariable("id") Long id) throws Exception {
        Login login = loginService.get(id);
        setHttpResponseStatus(HttpServletResponse.SC_OK);
        return login;
    }

    /**
     * 获取登录验证码
     *
     * @return Empty
     * @throws Exception
     */
    @RequestMapping(value = "/code", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.GET)
    public Login getCode() {
        Login login = loginService.getCode(CommonUtils.getIp(request), request.getHeader("user-agent"));
        setHttpResponseStatus(HttpServletResponse.SC_OK);
        return login;
    }


    /**
     * 登录
     * @param login
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.POST)
    public Login post(@RequestBody Login login) throws Exception {
        login = loginService.post(login, CommonUtils.getIp(request));
        setHttpResponseStatus(HttpServletResponse.SC_CREATED);
        return login;
    }

}