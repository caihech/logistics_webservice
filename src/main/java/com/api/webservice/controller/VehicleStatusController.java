package com.api.webservice.controller;

import com.api.webservice.annotation.UserAnnotation;
import com.api.webservice.dao.entity.VehicleStatus;
import com.api.webservice.service.VehicleStatusService;
import com.api.webservice.utils.EnumUtils;
import com.api.webservice.utils.exception.SC_NOT_FOUND;
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
@RequestMapping("/vehiclestatus")
public class VehicleStatusController extends BaseController {

    @Autowired
    private VehicleStatusService vehicleStatusService;

    /**
     * 查询所有
     *
     * @return List
     * @throws Exception 401无效token,403没有权限
     */
    @UserAnnotation(Roles = {EnumUtils.Role.ADMINISTRATOR})
    @RequestMapping(value = "", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.GET)
    public List<VehicleStatus> query() throws Exception {
        List<VehicleStatus> vehicleStatusList = vehicleStatusService.query();
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
    @UserAnnotation(Roles = {EnumUtils.Role.ADMINISTRATOR, EnumUtils.Role.USER})
    @RequestMapping(value = "/{id}", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.GET)
    public VehicleStatus get(@PathVariable("id") Long id) throws Exception {

        throw new SC_NOT_FOUND();

//        VehicleStatus vehicleStatus = vehicleStatusService.get(id);
//        setHttpResponseStatus(HttpServletResponse.SC_OK);
//        return vehicleStatus;
    }

}