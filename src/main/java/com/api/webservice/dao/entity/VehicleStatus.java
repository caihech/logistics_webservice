package com.api.webservice.dao.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;


/**
 * 车辆_状态表
 * 当前信息只能 审核员和管理员该
 * 默认 1 无状态，可以删除可以修改， 删除后 托运单关系为NULL
 * 2 发送 只能管理员修改1=2  ==2的时候后页面信息不可更改
 * 3 收货 只能管理员修改2=3  ==3的时候后页面信息不可更改 订单结束，不可以该
 *
 * @author h.cai
 * @date 2018/06/20
 */
@Component
@Entity
@Table(name = "vehicle_status")
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class VehicleStatus extends BaseEntity {

    private String name;
    private String remark;

    private List<Vehicle> vehicles;

    /**
     * 名称
     */
    @Column(name = "name", length = 80, nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 备注
     */
    @Column(name = "remark", length = 256)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    /**
     * batch list
     */
    @JsonInclude
    @OneToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY, mappedBy = "vehicleStatus")
    @LazyCollection(value = LazyCollectionOption.EXTRA)
    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

}
