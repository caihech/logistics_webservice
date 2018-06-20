package com.api.webservice.dao.repository;

import com.api.webservice.dao.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * 车辆
 *
 * @author h.cai
 * @date 2018/06/20
 */
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {


}
