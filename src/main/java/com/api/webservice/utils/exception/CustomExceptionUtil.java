package com.api.webservice.utils.exception;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by h.cai on 2017/4/13.
 */
public class CustomExceptionUtil {
    public static int exceptionToResponseStatusCode(CustomException customException) {

        /**
         * 200 GET 请求成功
         */
        if (customException instanceof SC_OK) {
            return HttpServletResponse.SC_OK;
        }

        /**
         * 201  POST 成功
         */
        if (customException instanceof SC_CREATED) {
            return HttpServletResponse.SC_CREATED;
        }

        /**
         * 204 DELETE 成功
         */
        if (customException instanceof SC_NO_CONTENT) {
            return HttpServletResponse.SC_NO_CONTENT;
        }


        /**
         * 400 入参数据错误
         */
        if (customException instanceof SC_BAD_REQUEST) {
            return HttpServletResponse.SC_BAD_REQUEST;
        }

        /**
         * 401 token过期
         */
        if (customException instanceof SC_UNAUTHORIZED) {
            return HttpServletResponse.SC_UNAUTHORIZED;
        }


        /**
         * 403 无权限
         */
        if (customException instanceof SC_FORBIDDEN) {
            return HttpServletResponse.SC_FORBIDDEN;
        }

        /**
         * 404 没有找到
         */
        if (customException instanceof SC_NOT_FOUND) {
            return HttpServletResponse.SC_NOT_FOUND;
        }


        /**
         * 409 一切正常但是不能执行
         */
        if (customException instanceof SC_CONFLICT) {
            return HttpServletResponse.SC_CONFLICT;
        }


        /**
         * 450 验证码过期  目前定义5分钟
         */
        if (customException instanceof SC_VERIFICATION_CODE_EXPIRED) {
            return 450;
        }

        /**
         * 451 验证码错误
         */
        if (customException instanceof SC_VERIFICATION_CODE_ERROR) {
            return 451;
        }

        /**
         * 452 密码错误
         */
        if (customException instanceof SC_PASSWORD_ERROR) {
            return 452;
        }

        /**
         * 453 用户被锁定
         */
        if (customException instanceof SC_USER_LOCKED) {
            return 453;
        }

        /**
         * 454 用户无效
         */
        if (customException instanceof SC_USER_INVALID) {
            return 454;
        }


        /**
         * 500  服务器内部错误
         */
        if (customException instanceof SC_INTERNAL_SERVER_ERROR) {
            return HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
        }


        return -1;
    }
}
