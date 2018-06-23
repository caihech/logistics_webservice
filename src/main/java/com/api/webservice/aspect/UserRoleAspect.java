package com.api.webservice.aspect;

import com.api.webservice.annotation.UserAnnotation;
import com.api.webservice.dao.entity.User;
import com.api.webservice.utils.EnumUtils;
import com.api.webservice.utils.exception.CustomException;
import com.api.webservice.utils.exception.CustomExceptionUtil;
import com.api.webservice.utils.exception.SC_FORBIDDEN;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Aspect
@Component
public class UserRoleAspect {


    protected Logger logger = LogManager.getLogger(UserRoleAspect.class);

    @Before("execution(* com.api.webservice.controller..*(..)) && @annotation(userAnnotation)")
    public void logBeforeAllMethods(JoinPoint joinPoint, UserAnnotation userAnnotation) throws CustomException {
        logger.info("****UserRoleAspect.logBeforeAllMethods() : " + joinPoint.getSignature().getName());
        logger.info("userAnnotation " + userAnnotation.Roles().length + " " + userAnnotation.Roles()[0].name());

        EnumUtils.Role[] Roles = userAnnotation.Roles();

        if (Roles.length > 0) {

            boolean bCheckRole = false;
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            User user = (User) request.getAttribute("user");
            if (user == null) {
                logger.error("403 http servlet request user is null");
                throw new SC_FORBIDDEN();
            }
            if (user.getRole() == null) {
                logger.error("403 http servlet request Role is null");
                throw new SC_FORBIDDEN();
            }

            for (EnumUtils.Role enumRole : Roles) {
                if (enumRole.key == user.getRole().getId()) {
                    bCheckRole = true;
                    break;
                }
            }
            if (bCheckRole == false) {
                logger.error("403 check Role error.");
                throw new SC_FORBIDDEN();
            }
        }
    }


    @Pointcut(value = "execution(* com.api.webservice.controller..*(..))")
    public void getPointCut() {
        logger.info("start.");
    }

    @AfterThrowing(value = "getPointCut()", throwing = "e")
    public void catchExceptionByAspect(JoinPoint joinPoint, Exception e) {
        try {
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();

            if (e instanceof CustomException) {
                logger.error(e.getMessage());
                response.setStatus(CustomExceptionUtil.exceptionToResponseStatusCode((CustomException) e));
            } else {
                logger.error(e.getMessage());
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }

}
