package com.api.webservice.dao.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 委托单表
 * Update by h.cai on 2018/6/19.
 */
@Component
@Entity
@Table(name = "consignment_note")
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class ConsignmentNote extends BaseEntity {

    /**
     * 订单编号 ，8位 ， 唯一约束 ，不能为空
     */
//    private String carrierSingleNumber;
//    /**
//     * TODO
//     *
//     * ？表格？
//     */
//    private String ToCity;
//    /**
//     * 货号
//     */
//    public String hh;
//
//    /**
//     * 托运日期
//     */
//    public Timestamp data;
//
//    /**
//     * 到货地址  省市楼盘
//     */
//    public String h1h;
//
//    /**
//     * 发货地址
//     */
//    public String h3h;
//
//    /**
//     * 广州查询电话 TODO ??
//     */
//    public String 1h3h;
//
//    /**
//     * 咨询电弧  TODO ??
//     */
//    public String h3h;
//
//    /**
//     * 投诉电弧 TODO ？？
//     */
//    public String h3h;
//
//    /**
//     * 托运人姓名
//     */
//    public String h3h;
//
//    /**
//     * 托运人电电话
//     */
//    public String h3h;
//
//    /**
//     * 收货人姓名
//     */
//    public String h3h;
//
//    /**
//     * 收货人电话
//     */
//    public String h3h;
//
//
//    /**
//     * 货物名称
//     */
//    public String h3h;
//
//    /**
//     * 包装
//     */
//    public String h3h;
//
//    /**
//     * 重量
//     */
//    public String h3h;
//
//    /**
//     * 体积
//     */
//    public String h3h;
//
//    /**
//     * 件数
//     */
//    public Integer h3h;
//
//    /**
//     * 保险 钱
//     */
//    public String h3h;
//
//    /**
//     * 保费 钱
//     */
//    public float h3h;
//
//    /**
//     * 月结 钱？？？
//     */
//    public String h3h;
//
//
//    /**
//     * 回单付 钱
//     */
//    public String h3h;
//
//    /**
//     * 现金 钱
//     */
//    public String h3h;
//
//    /**
//     * 提付 钱
//     */
//    public String h3h;
//
//    /**
//     * 运费 钱
//     */
//    public String h3h;
//
//    /**
//     * 短途运费 钱
//     */
//    public String h3h;
//
//
//    /**
//     * 合计
//     */
//    public String h3h;
//
//
//    /**
//     * 代收货款 钱
//     */
//    public String h3h;
//
//    /**
//     * 送货地址
//     */
//    public String h3h;
//
//
//    /**
//     * 备注
//     */
//    public String h3h;
//
//
//    /**
//     * 提货人签字 ？？
//     */
//    public String h3h;
//
//
//    /**
//     * 身份证编号 ？？
//     */
//    public String h3h;
//
//
//    /**
//     * 发货人签字 ？？
//     */
//    public String h3h;
//
//    /**
//     * 打印次数
//     */
//    public int h3h;



    private User user;
    private ConsignmentNoteStatus consignmentNoteStatus;
    private Vehicle vehicle;


    /**
     * fk_user_id
     *
     * @return
     */
    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @LazyToOne(value = LazyToOneOption.FALSE)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * fk_consignment_note_status_id
     *
     * @return
     */
    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "consignment_note_status_id", referencedColumnName = "id", nullable = false)
    @LazyToOne(value = LazyToOneOption.FALSE)
    public ConsignmentNoteStatus getConsignmentNoteStatus() {
        return consignmentNoteStatus;
    }

    public void setConsignmentNoteStatus(ConsignmentNoteStatus consignmentNoteStatus) {
        this.consignmentNoteStatus = consignmentNoteStatus;
    }


    /**
     * fk_vehicle_id
     *
     * @return
     */
    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    @LazyToOne(value = LazyToOneOption.FALSE)
    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

}
