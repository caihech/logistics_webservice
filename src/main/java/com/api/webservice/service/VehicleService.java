package com.api.webservice.service;


import com.api.webservice.dao.entity.Vehicle;
import com.api.webservice.dao.repository.VehicleRepository;
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


}