package com.api.webservice.controller;

import com.api.webservice.config.SystemConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取项目版本信息
 *
 * @author h.cai
 */
@CrossOrigin
@Controller
public class Version extends BaseController {

    @Autowired
    private SystemConfig systemConfig;


    /**
     * 得到版本信息
     *
     * @return 键值对对象
     */
    @RequestMapping(value = "/version", method = RequestMethod.GET)
    @ResponseBody
    public Map get() {
        Map<String, String> versionMap = new HashMap<>(16);

        versionMap.put("name", systemConfig.getName());
        versionMap.put("version", systemConfig.getVersion());

        Calendar calendar = Calendar.getInstance();
        versionMap.put("time", String.valueOf(calendar.getTimeInMillis()));

        versionMap.put("serverIP", request.getLocalAddr());

        return versionMap;
    }
}