package com.api.webservice.controller;

import com.api.webservice.annotation.UserAnnotation;
import com.api.webservice.dao.entity.Vehicle;
import com.api.webservice.dao.entity.VehicleStatus;
import com.api.webservice.service.VehicleService;
import com.api.webservice.service.VehicleStatusService;
import com.api.webservice.utils.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * 车辆_状态
 *
 * @author h.cai
 * @date 2018/06/20
 */
@CrossOrigin
@RestController
@RequestMapping("/vehicle")
public class VehicleController extends BaseController {

    @Autowired
    private VehicleService vehicleService;

    /**
     * 查询所有
     *
     * @return List
     * @throws Exception 401无效token,403没有权限
     */
    @UserAnnotation(roles = {EnumUtils.Role.ADMINISTRATOR})
    @RequestMapping(value = "", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.GET)
    public List<Vehicle> query() throws Exception {
        List<Vehicle> vehicleStatusList = vehicleService.query();
        setHttpResponseStatus(HttpServletResponse.SC_OK);
        return vehicleStatusList;
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
    public Vehicle get(@PathVariable("id") Long id) throws Exception {
        Vehicle vehicle = vehicleService.get(id);
        setHttpResponseStatus(HttpServletResponse.SC_OK);
        return vehicle;
    }

}