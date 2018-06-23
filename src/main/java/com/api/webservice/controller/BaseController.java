package com.api.webservice.controller;


import com.api.webservice.dao.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 *
 * @author h.cai
 * @date 2018/06/20
 */
public abstract class BaseController {
    protected Logger log;
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;
    protected User tokenUser;


    public BaseController() {
        this.log = LogManager.getLogger(this.getClass().getName());
        if (RequestContextHolder.getRequestAttributes() != null) {
            response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        }
    }

    /**
     * 得到Http请求对象
     *
     * @return HttpServletRequest
     */
    private HttpServletRequest getServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 得到Http响应对象
     *
     * @return HttpServletResponse
     */
    private HttpServletResponse getServletResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    /**
     * 初始化函数
     *
     * @param request  请求对象
     * @param response 响应对象
     */
    @ModelAttribute
    public void baseInit(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.session = request.getSession();
        this.tokenUser = (User) request.getAttribute("user");
    }

    /**
     * 设置状态码
     *
     * @param code 状态码
     */
    protected void setHttpResponseStatus(Integer code) {
        response = getServletResponse();
        if (response != null) {
            response.setStatus(code);
        }
    }

    /**
     * 获取当前用户的ip
     *
     * @return ip地址
     */
    public String getIpAddr() {
        String unknown = "unknown";
        String localhost = System.getProperty("myapplication.ip");
        String local = "0:0:0:0:0:0:0:1";
        int length = 15;
        String charFlag = ",";

        request = getServletRequest();

        String ipAddress = request.getHeader("x-forwarded-for");

        if (ipAddress == null || ipAddress.length() == 0 || unknown.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }

        if (ipAddress == null || ipAddress.length() == 0 || unknown.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ipAddress == null || ipAddress.length() == 0 || unknown.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (localhost.equals(ipAddress) || local.equals(ipAddress)) {
                //根据网卡取本机配置的IP
                try {
                    ipAddress = InetAddress.getLocalHost().getHostAddress();
                } catch (UnknownHostException e) {
                    log.error(e.getMessage());
                }
            }
        }

        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        //"***.***.***.***".length() = 15
        if (ipAddress != null && ipAddress.length() > length && ipAddress.contains(charFlag)) {
            int index = ipAddress.indexOf(',');
            ipAddress = ipAddress.substring(0, index);
        }

        return ipAddress;
    }

    /**
     * 得到用户对象
     *
     * @return user
     */
    public User getUser() {
        return tokenUser;
    }
}
