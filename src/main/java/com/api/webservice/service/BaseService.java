package com.api.webservice.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 父类-服务
 *
 * @author h.cai
 * @date 2018/06/15
 */
public abstract class BaseService {
    protected Logger log;

    /**
     * 构造函数
     */
    public BaseService() {
        this.log = LogManager.getLogger(this.getClass().getName());
    }
}
