package com.api.webservice.utils;

import org.apache.commons.codec.binary.Hex;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 常用工具类
 *
 * @author
 */
public class CommonUtils {
    /**
     * 构造函数私有化
     */
    private CommonUtils() {
        throw new IllegalStateException("CommonUtils class");
    }

    /**
     * 获取客户端IP
     *
     * @param request 请求对象
     * @return ip字符串
     */
    public static String getIp(HttpServletRequest request) {
        String unknown = "unknown";
        String local = "0:0:0:0:0:0:0:1";
        String ip = request.getHeader("x-forwarded-for");

        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (local.equals(ip)) {
            ip = "127.0.0.1";
        }
        return ip;
    }

    /**
     * SHA 256 加密
     *
     * @param values 原始字符串
     * @return 加密后字符串
     */
    public static String getSha256(String values) {
        if (values == null || values.isEmpty()) {
            return "";
        }

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(values.getBytes("UTF-8"));
            return Hex.encodeHexString(hash);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            return "";
        }
    }

    /**
     * 判断编码后的字节长度
     *
     * @param str    字符串
     * @param index  编码索引
     * @param maxLen 最大长度
     * @return 真假
     */
    public static Boolean legalLength(String str, int index, int maxLen) {
        boolean result = false;

        try {
            String[] charsetName = {"GB18030", "UTF-8"};

            int len = str.getBytes(charsetName[index]).length;

            if (len <= maxLen) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
