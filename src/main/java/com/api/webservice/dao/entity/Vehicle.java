package com.api.webservice.dao.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

/**
 * 交通工具表
 * Update by h.cai on 2018/6/19.
 */
@Component
@Entity
@Table(name = "vehicle")
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class Vehicle extends BaseEntity {

    //    /**
//     * TODO 牌照
//     */
//    private String licensePlate;
//
//    /**
//     * TODO 司机姓名
//     */
//    private String licensePlate;
//
//    /**
//     * TODO 驾驶证号
//     */
//    private String licensePlate;
//
//    /**
//     * TODO 司机联系电话
//     */
//    private String licensePlate;
//
//    /**
//     * TODO 发货日期 变更状态是修改
//     */
//    private String licensePlate;
//    /**
//     * TODO 到货日期 变更状态修改
//     */
//    private String licensePlate;


    private VehicleStatus vehicleStatus;
    private List<ConsignmentNote> consignmentNotes;

//    /**
//     * 牌照
//     */
//    @Column(name = "remark", length = 50)
//    public String getLicensePlate() {
//        return licensePlate;
//    }
//
//    public void setLicensePlate(String licensePlate) {
//        this.licensePlate = licensePlate;
//    }


    /**
     * fk_vehicle_status_id
     *
     * @return
     */
    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_status_id", referencedColumnName = "id", nullable = false)
    @LazyToOne(value = LazyToOneOption.FALSE)
    public VehicleStatus getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(VehicleStatus vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }


    /**
     * consignmentNotes_list
     */
    @JsonInclude
    @OneToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY, mappedBy = "vehicle")
    @LazyCollection(value = LazyCollectionOption.EXTRA)
    public List<ConsignmentNote> getConsignmentNotes() {
        return consignmentNotes;
    }

    public void setConsignmentNotes(List<ConsignmentNote> consignmentNotes) {
        this.consignmentNotes = consignmentNotes;
    }

}
