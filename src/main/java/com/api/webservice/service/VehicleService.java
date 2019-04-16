package com.api.webservice.service;


import com.api.webservice.dao.entity.ConsignmentNote;
import com.api.webservice.dao.entity.User;
import com.api.webservice.dao.entity.Vehicle;
import com.api.webservice.dao.repository.VehicleRepository;
import com.api.webservice.utils.exception.SC_BAD_REQUEST;
import com.api.webservice.utils.exception.SC_INTERNAL_SERVER_ERROR;
import com.api.webservice.utils.exception.SC_NOT_FOUND;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 车辆
 *
 * @author h.cai
 * @date 2018/06/20
 */
@Service
public class VehicleService extends BaseService {

    @Autowired
    private VehicleRepository vehicleRepository;


    /**
     * 查询所有
     *
     * @return list
     */
    public List<Vehicle> query() {
        return vehicleRepository.findAll();
    }


    /**
     * 查询一条数据
     *
     * @param id 主键
     * @return empty
     */
    public Vehicle get(Long id) {

        Vehicle vehicle = vehicleRepository.findOne(id);

        if (vehicle == null) {
            log.error("vehicle is not find.");
            throw new SC_NOT_FOUND();
        }

        return vehicle;
    }


    public Vehicle post(User tokenUser, Vehicle vehicle) {

        if (tokenUser == null || vehicle == null) {
            log.error("400,param is null.");
            throw new SC_BAD_REQUEST();
        }

        try {
            vehicle.setId(0);
            vehicle.setValid(false);
            vehicle = vehicleRepository.saveAndFlush(vehicle);
        } catch (Exception ex) {
            log.error("500," + ex.getMessage());
            throw new SC_INTERNAL_SERVER_ERROR();
        }
        return vehicle;
    }

    public Vehicle put(User tokenUser, Vehicle vehicle) {


        if (tokenUser == null || tokenUser.getRole() == null || vehicle == null) {
            log.error("400  put user param is null.");
            throw new SC_BAD_REQUEST();
        }
        Vehicle vehicleRet = vehicleRepository.findOne(vehicle.getId());

        if (vehicleRet == null) {
            log.error("404  put user not find.");
            throw new SC_BAD_REQUEST();
        }

        if (vehicleRet.isValid()) {
            log.error("403  is Valid  not permissions.");
            throw new SC_BAD_REQUEST();
        }

        //修改能修改的属性
        vehicleRet.setLicensePlate(vehicle.getLicensePlate());
        vehicleRet.setDriverName(vehicle.getDriverName());
        vehicleRet.setDriverPhone(vehicle.getDriverPhone());
        vehicleRet.setStartDate(vehicle.getStartDate());
        vehicleRet.setEndDate(vehicle.getEndDate());

        //TODO 暂时不支持更新属性
//        vehicleRet.setValid();
//        vehicleRet.setConsignmentNotes();

        vehicleRet = vehicleRepository.saveAndFlush(vehicleRet);

        if (vehicleRet == null) {
            throw new SC_INTERNAL_SERVER_ERROR();
        }

        return vehicleRet;
    }
}