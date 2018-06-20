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
 * 委托单_状态表
 * 状态:
 * 1 无   创建人 和 管理可以改动、删除；
 * 2 收货 只能管理员修改1=2  ==2的时候后页面信息不可更改
 * 3 拒收 只能管理员修改1=3  ==3的时候后页面信息不可更改
 * 4 已删除，只能管理员 有2=4 ，前提，没有加入到批次里的数据；
 *
 * @author h.cai
 * @date 2018/06/20
 */
@Component
@Entity
@Table(name = "consignment_note_status")
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class ConsignmentNoteStatus extends BaseEntity {


    private String name;
    private String remark;

    private List<ConsignmentNote> consignmentNotes;


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
    @OneToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY, mappedBy = "consignmentNoteStatus")
    @LazyCollection(value = LazyCollectionOption.EXTRA)
    public List<ConsignmentNote> getConsignmentNotes() {
        return consignmentNotes;
    }

    public void setConsignmentNotes(List<ConsignmentNote> consignmentNotes) {
        this.consignmentNotes = consignmentNotes;
    }
}
