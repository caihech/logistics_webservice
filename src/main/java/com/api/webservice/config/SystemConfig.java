package com.api.webservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 项目配置文件
 *
 * @author h.cai
 * @date 2018/06/15
 */
@Component
public class SystemConfig {

    @Value("#{applicationPropertyConfigurer['version']}")
    private String version;

    @Value("#{applicationPropertyConfigurer['name']}")
    private String name;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
