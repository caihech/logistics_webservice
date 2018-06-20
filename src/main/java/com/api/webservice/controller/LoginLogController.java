package com.api.webservice.controller;

import com.api.webservice.annotation.UserAnnotation;
import com.api.webservice.dao.entity.LoginLog;
import com.api.webservice.dao.entity.VehicleStatus;
import com.api.webservice.service.LoginLogService;
import com.api.webservice.service.VehicleStatusService;
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
@RequestMapping("/loginlog")
public class LoginLogController extends BaseController {

    @Autowired
    private LoginLogService loginLogService;

    /**
     * 查询所有
     *
     * @return List
     * @throws Exception 401无效token,403没有权限
     */
    @UserAnnotation(roles = {EnumUtils.Role.ADMINISTRATOR})
    @RequestMapping(value = "", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.GET)
    public List<LoginLog> query() throws Exception {
        List<LoginLog> loginLogList = loginLogService.query();
        setHttpResponseStatus(HttpServletResponse.SC_OK);
        return loginLogList;
    }

    /**
     * 查询一条数据
     *
     * @param id 主键id
     * @return Empty
     * @throws Exception 401无效token,403没有权限,404不存在
     */
    @UserAnnotation(roles = {EnumUtils.Role.ADMINISTRATOR, EnumUtils.Role.USER})
    @RequestMapping(value = "/{id}", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.GET)
    public LoginLog get(@PathVariable("id") Long id) throws Exception {
        LoginLog loginLog = loginLogService.get(id);
        setHttpResponseStatus(HttpServletResponse.SC_OK);
        return loginLog;
    }

}