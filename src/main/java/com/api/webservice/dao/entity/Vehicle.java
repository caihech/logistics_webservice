package com.api.webservice.dao.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * 车辆表
 *
 * @author h.cai
 * @date 2018/06/20
 */
@Component
@Entity
@Table(name = "vehicle")
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class Vehicle extends BaseEntity {

    private String licensePlate;
    private String driverName;
    private String drivingLicenseNumber;
    private String driverPhone;
    private Timestamp startDate;
    private Timestamp endDate;

    private boolean valid;
    private List<ConsignmentNote> consignmentNotes;


    /**
     * 牌照
     */
    @Column(name = "license_plate", length = 50, nullable = false)
    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    /**
     * 司机姓名
     */
    @Column(name = "driver_name", length = 15)
    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }


    /**
     * 驾驶证编号
     */
    @Column(name = "driving_license_number", length = 25)
    public String getDrivingLicenseNumber() {
        return drivingLicenseNumber;
    }

    public void setDrivingLicenseNumber(String drivingLicenseNumber) {
        this.drivingLicenseNumber = drivingLicenseNumber;
    }


    /**
     * 司机联系电话
     */
    @Column(name = "driver_phone", length = 25)
    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    /**
     * 发货日期 变更状态是修改
     */
    @Column(name = "start_date", columnDefinition = "TIMESTAMP null default null")
    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    /**
     * 收货日期 变更状态修改
     */
    @Column(name = "end_date", columnDefinition = "TIMESTAMP null default null")
    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }


    /**
     * 托运单是否有效 0否 1是 如果为是不可删除
     * @return
     */
    @Column(name = "valid", columnDefinition = "bit default 0 ", nullable = false)
    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
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
