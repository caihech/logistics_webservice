package com.api.webservice.dao.repository;

import com.api.webservice.dao.entity.Vehicle;
import com.api.webservice.dao.entity.VehicleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * 车辆_状态
 *
 * @author h.cai
 * @date 2018/06/20
 */
@Repository
public interface VehicleStatusRepository extends JpaRepository<VehicleStatus, Long> {


}
