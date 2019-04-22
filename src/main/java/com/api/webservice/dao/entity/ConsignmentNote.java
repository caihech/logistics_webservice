package com.api.webservice.dao.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String articleNumber;
    private Timestamp consignmentDate;
    private String goodsAddress;
    private String shippersName;
    private String shippersPhone;
    private String consigneeName;
    private String consigneePhone;
    private String goodsName;
    private String packaging;
    private BigDecimal weight;
    private BigDecimal volume;
    private Integer number;
    private BigDecimal insurance;
    private BigDecimal premium;
    private BigDecimal monthlyStatement;
    private BigDecimal receiptPayment;
    private BigDecimal cashPayment;
    private BigDecimal extractPayment;
    private BigDecimal shortHaulFreight;
    private BigDecimal amount;
    private BigDecimal collectionOnDelivery;
    private String deliveryAddress;
    private String remark;
    private int printCount;


    private String consignee;
    private String idCard;
    private String consignor;


    private User user;
    private Vehicle vehicle;


    private boolean valid;


    private Integer vehicleId;


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
     * 到货地址 必填
     */
    @Column(name = "goods_address", length = 40, nullable = false)
    public String getGoodsAddress() {
        return goodsAddress;
    }

    public void setGoodsAddress(String goodsAddress) {
        this.goodsAddress = goodsAddress;
    }

    /**
     * 托运日期 必填
     */
    @Column(name = "consignment_date", columnDefinition = "TIMESTAMP null default null")
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

    public void setPremium(BigDecimal premium) {
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
     * 提货人
     */
    @Column(name = "consignee", length = 10)
    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    /**
     * 身份证
     */
    @Column(name = "id_card", length = 20)
    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }


    /**
     * 发货人
     */
    @Column(name = "consignor", length = 10)
    public String getConsignor() {
        return consignor;
    }

    public void setConsignor(String consignor) {
        this.consignor = consignor;
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
     * 托运单是否有效 0否 1是 如果为是不可删除
     *
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
     * fk_vehicle_id
     *
     * @return
     */
    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    @LazyToOne(value = LazyToOneOption.FALSE)
    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }


    @Transient
    public Integer getVehicleId() {
        Integer result = 0;
        if (this.vehicle != null) {
            result = (int) this.vehicle.getId();
        }
        return result;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }
}
