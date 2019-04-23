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

    //region licensePlate 牌照 编号50
    private String licensePlate;

    @Column(name = "license_plate", length = 50, nullable = false)
    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
    //endregion

    //region driverName 司机姓名 15
    private String driverName;

    @Column(name = "driver_name", length = 15)
    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
    //endregion

    //region drivingLicenseNumber 驾驶证编号 25
    private String drivingLicenseNumber;

    @Column(name = "driving_license_number", length = 25)
    public String getDrivingLicenseNumber() {
        return drivingLicenseNumber;
    }

    public void setDrivingLicenseNumber(String drivingLicenseNumber) {
        this.drivingLicenseNumber = drivingLicenseNumber;
    }
    //endregion

    //region driverPhone 司机联系电话 25
    private String driverPhone;

    @Column(name = "driver_phone", length = 25)
    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }
    //endregion

    //region startDate 发车日期
    private Timestamp startDate;

    @Column(name = "start_date", columnDefinition = "TIMESTAMP null default null")
    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }
    //endregion

    //region endDate 到站日期
    private Timestamp endDate;

    @Column(name = "end_date", columnDefinition = "TIMESTAMP null default null")
    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }
    //endregion

    //region valid 托运单是否有效 0否 1是 如果为是不可删除
    private boolean valid;

    @Column(name = "valid", columnDefinition = "bit default 0 ", nullable = false)
    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
    //endregion

    //region consignmentNotes_list
    private List<ConsignmentNote> consignmentNotes;

    @JsonInclude
    @OneToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY, mappedBy = "vehicle")
    @LazyCollection(value = LazyCollectionOption.EXTRA)
    public List<ConsignmentNote> getConsignmentNotes() {
        return consignmentNotes;
    }

    public void setConsignmentNotes(List<ConsignmentNote> consignmentNotes) {
        this.consignmentNotes = consignmentNotes;
    }
    //endregion

}
