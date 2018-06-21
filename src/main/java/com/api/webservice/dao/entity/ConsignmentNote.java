package com.api.webservice.dao.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 委托单表
 *
 * @author h.cai
 * @date 2018/06/20
 */
@Component
@Entity
@Table(name = "consignment_note")
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class ConsignmentNote extends BaseEntity {


    private String orderNumber;
    private String station;
    public String articleNumber;
    public Timestamp consignmentDate;
    public String shippersName;
    public String shippersPhone;
    public String consigneeName;
    public String consigneePhone;
    public String goodsName;
    public String packaging;
    public BigDecimal weight;
    public BigDecimal volume;
    public Integer number;
    public BigDecimal insurance;
    public BigDecimal premium;
    public BigDecimal monthlyStatement;
    public BigDecimal receiptPayment;
    public BigDecimal cashPayment;
    public BigDecimal extractPayment;
    public BigDecimal shortHaulFreight;
    public BigDecimal amount;
    public BigDecimal collectionOnDelivery;
    public String deliveryAddress;
    public String remark;
    public int printCount;

    private User user;
    private ConsignmentNoteStatus consignmentNoteStatus;
    private Vehicle vehicle;

    /**
     * 订单编号 ，8位 ，唯一约束 ，必填
     */
    @Column(name = "order_number", length = 8, nullable = false, unique = true)
    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }


    /**
     * 站点 必填
     */
    @Column(name = "station", length = 40, nullable = false)
    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    /**
     * 货号 必填
     */
    @Column(name = "article_number", length = 20, nullable = false)
    public String getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(String articleNumber) {
        this.articleNumber = articleNumber;
    }

    /**
     * 托运日期 必填
     */
    @Column(name = "consignment_date", columnDefinition = "TIMESTAMP null default null", nullable = false)
    public Timestamp getConsignmentDate() {
        return consignmentDate;
    }

    public void setConsignmentDate(Timestamp consignmentDate) {
        this.consignmentDate = consignmentDate;
    }


    /**
     * 托运人姓名 必填
     */
    @Column(name = "shippers_name", length = 10)
    public String getShippersName() {
        return shippersName;
    }

    public void setShippersName(String shippersName) {
        this.shippersName = shippersName;
    }

    /**
     * 托运人电话  必填
     */
    @Column(name = "shippers_phone", length = 15)
    public String getShippersPhone() {
        return shippersPhone;
    }

    public void setShippersPhone(String shippersPhone) {
        this.shippersPhone = shippersPhone;
    }


    /**
     * 收货人姓名 必填
     */
    @Column(name = "consignee_name", length = 10)
    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    /**
     * 收货人电话 必填
     */
    @Column(name = "consignee_phone", length = 15)
    public String getConsigneePhone() {
        return consigneePhone;
    }

    public void setConsigneePhone(String consigneePhone) {
        this.consigneePhone = consigneePhone;
    }

    /**
     * 货物名称 必填
     */
    @Column(name = "goods_name", length = 80)
    public String getGoodsName() {
        return goodsName;
    }


    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }


    /**
     * 包装
     */
    @Column(name = "packaging", length = 100)
    public String getPackaging() {
        return packaging;
    }

    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

    /**
     * 重量  必填
     */
    @Column(name = "weight", precision = 18, scale = 3, columnDefinition = "decimal(18,3) default 0.000", nullable = false)
    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }


    /**
     * 体积  必填
     */
    @Column(name = "volume", precision = 18, scale = 3, columnDefinition = "decimal(18,3) default 0.000", nullable = false)
    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    /**
     * 件数 必填
     */
    @Column(name = "number", columnDefinition = "int default 0", nullable = false)
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * 保险
     */
    @Column(name = "insurance", precision = 18, scale = 2, columnDefinition = "decimal(18,2) default 0.00", nullable = false)
    public BigDecimal getInsurance() {
        return insurance;
    }

    public void setInsurance(BigDecimal insurance) {
        this.insurance = insurance;
    }

    /**
     * 保费  = 保险 * 0.005
     */
    @Column(name = "premium", precision = 18, scale = 2, columnDefinition = "decimal(18,2) default 0.00", nullable = false)
    public BigDecimal getPremium() {
        return premium;
    }

    public void setPremium() {
        BigDecimal interest = new BigDecimal("0.005");
        this.premium = getInsurance().multiply(interest);
    }

    /**
     * 月结
     */
    @Column(name = "monthly_statement", precision = 18, scale = 2, columnDefinition = "decimal(18,2) default 0.00", nullable = false)
    public BigDecimal getMonthlyStatement() {
        return monthlyStatement;
    }

    public void setMonthlyStatement(BigDecimal monthlyStatement) {
        this.monthlyStatement = monthlyStatement;
    }

    /**
     * 回单付
     */
    @Column(name = "receipt_payment", precision = 18, scale = 2, columnDefinition = "decimal(18,2) default 0.00", nullable = false)
    public BigDecimal getReceiptPayment() {
        return receiptPayment;
    }

    public void setReceiptPayment(BigDecimal receiptPayment) {
        this.receiptPayment = receiptPayment;
    }

    /**
     * 现金付款
     */
    @Column(name = "cash_payment", precision = 18, scale = 2, columnDefinition = "decimal(18,2) default 0.00", nullable = false)
    public BigDecimal getCashPayment() {
        return cashPayment;
    }

    public void setCashPayment(BigDecimal cashPayment) {
        this.cashPayment = cashPayment;
    }

    /**
     * 提付
     */
    @Column(name = "extract_payment", precision = 18, scale = 2, columnDefinition = "decimal(18,2) default 0.00", nullable = false)
    public BigDecimal getExtractPayment() {
        return extractPayment;
    }

    public void setExtractPayment(BigDecimal extractPayment) {
        this.extractPayment = extractPayment;
    }

    /**
     * 短途运费
     */
    @Column(name = "short_haul_freight", precision = 18, scale = 2, columnDefinition = "decimal(18,2) default 0.00", nullable = false)
    public BigDecimal getShortHaulFreight() {
        return shortHaulFreight;
    }

    public void setShortHaulFreight(BigDecimal shortHaulFreight) {
        this.shortHaulFreight = shortHaulFreight;
    }

    /**
     * 合计  = 计算  保费+月结+回单付+现金+提付+短途运费+代收货款
     */
    @Column(name = "amount", precision = 18, scale = 2, columnDefinition = "decimal(18,2) default 0.00", nullable = false)
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 代收货款
     */
    @Column(name = "collection_on_delivery", precision = 18, scale = 2, columnDefinition = "decimal(18,2) default 0.00", nullable = false)
    public BigDecimal getCollectionOnDelivery() {
        return collectionOnDelivery;
    }

    public void setCollectionOnDelivery(BigDecimal collectionOnDelivery) {
        this.collectionOnDelivery = collectionOnDelivery;
    }

    /**
     * 送货地址
     */
    @Column(name = "delivery_address", length = 80)
    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    /**
     * 备注
     */
    @Column(name = "remark", length = 500)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 打印次数
     */
    @Column(name = "print_count", columnDefinition = "int default 0", nullable = false)
    public int getPrintCount() {
        return printCount;
    }

    public void setPrintCount(int printCount) {
        this.printCount = printCount;
    }


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
