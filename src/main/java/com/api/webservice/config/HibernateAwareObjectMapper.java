package com.api.webservice.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

/**
 * Hiibernate 缓存
 * @author h.cai
 * @date 2018/06/15
 */
public class HibernateAwareObjectMapper extends ObjectMapper {
    /**
     * 构造函数
     */
    public HibernateAwareObjectMapper() {
        Hibernate5Module hm = new Hibernate5Module();
        hm.configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING, false);
        hm.configure(Hibernate5Module.Feature.USE_TRANSIENT_ANNOTATION, false);
        registerModule(hm);
        setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }
}