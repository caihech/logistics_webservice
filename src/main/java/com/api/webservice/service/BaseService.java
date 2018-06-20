package com.api.webservice.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author h.cai
 * @date 2018/06/20
 */
public abstract class BaseService {

    protected Logger log;

    public BaseService() {
        this.log = LogManager.getLogger(this.getClass().getName());
    }
}
