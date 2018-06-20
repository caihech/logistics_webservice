package com.api.webservice.service;


import com.api.webservice.dao.entity.VehicleStatus;
import com.api.webservice.dao.repository.VehicleStatusRepository;
import com.api.webservice.utils.exception.SC_NOT_FOUND;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 车辆_状态
 *
 * @author h.cai
 * @date 2018/06/20
 */
@Service
public class VehicleStatusService extends BaseService {

    @Autowired
    private VehicleStatusRepository vehicleStatusRepository;


    /**
     * 查询所有
     *
     * @return list
     */
    public List<VehicleStatus> query() {
        return vehicleStatusRepository.findAll();
    }


    /**
     * 查询一条数据
     *
     * @param id 主键
     * @return empty
     */
    public VehicleStatus get(Long id) {

        VehicleStatus vehicleStatus = vehicleStatusRepository.findOne(id);

        if (vehicleStatus == null) {
            log.error("vehicleStatus is not find.");
            throw new SC_NOT_FOUND();
        }

        return vehicleStatus;
    }


}