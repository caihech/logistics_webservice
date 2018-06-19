package com.api.webservice.dao.entity;

import org.hibernate.annotations.Generated;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 数据库Entity虚拟基础类，其他所有Entities都继承它。
 *
 * @author h.cai
 * @date 2018/06/15
 */
@MappedSuperclass
public abstract class BaseEntity {
    /**
     * 表格主键Id
     */
    private long id;

    /**
     * 数据第一次创建时间，自动生成，无需传入
     */
    private Timestamp createTime;
    /**
     * 每次更新此条数据时的记录时间（上一次更新时间），无需传入
     */
    private Timestamp updateTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Generated(org.hibernate.annotations.GenerationTime.ALWAYS)
    @Column(name = "create_time", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Generated(org.hibernate.annotations.GenerationTime.ALWAYS)
    @Column(name = "update_time", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", insertable = false, updatable = false)
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
