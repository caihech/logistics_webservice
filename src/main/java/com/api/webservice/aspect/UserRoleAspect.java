package com.api.webservice.aspect;

import com.api.webservice.annotation.UserAnnotation;
import com.api.webservice.config.TomcatConfig;
import com.api.webservice.utils.EnumUtils;
import com.api.webservice.utils.exception.CustomException;
import com.api.webservice.utils.exception.CustomExceptionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author zuofei
 */
@Aspect
@Component
public class UserRoleAspect {

    @Autowired
    private TomcatConfig systemConfig;

    protected Logger logger = LogManager.getLogger(UserRoleAspect.class);

    @Before("execution(* com.api.webservice.controller..*(..)) && @annotation(userAnnotation)")
    public void logBeforeAllMethods(JoinPoint joinPoint, UserAnnotation userAnnotation) throws CustomException {
        logger.info("****UserRoleAspect.logBeforeAllMethods() : " + joinPoint.getSignature().getName());
        logger.info("userAnnotation " + userAnnotation.roles().length + " " + userAnnotation.roles()[0].name());

        EnumUtils.Role[] roles = userAnnotation.roles();

        if (roles.length > 0) {
            boolean validate = false;
//            LoginRecord loginRecord = ((BaseController) joinPoint.getThis()).getLoginRecord();
//            User user = ((BaseController) joinPoint.getThis()).getUser();
//
//            if (user != null) {
//                for (EnumUtils.Role r : roles) {
//                    if (user.getRole().getId() == r.key) {
//                        validate = true;
//                        break;
//                    }
//                }
//            } else {
//                HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
//                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                throw new SC_UNAUTHORIZED();
//            }
//
//            if (!validate) {
//                HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
//                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//                throw new SC_FORBIDDEN();
//            }

//            extendUserExpired(loginRecord);
        }
    }

//    private void extendUserExpired(LoginRecord loginRecord) {
//        boolean flag = (loginRecord.getTokenExpired().getTime() - System.currentTimeMillis()) / (1000 * 60) >= 0 && (loginRecord.getTokenExpired().getTime() - System.currentTimeMillis()) / (1000 * 60) <= 30;
//        if (flag) {
//            Timestamp newTimestamp = new Timestamp(System.currentTimeMillis() + systemConfig.getTokenExpried());
//            loginRecord.setTokenExpired(newTimestamp);
//            loginRecordRepository.saveAndFlush(loginRecord);
//        }
//    }

    @Pointcut(value = "execution(* com.api.webservice.controller..*(..))")
    public void getPointCut() {
        logger.info("start");
    }

    @AfterThrowing(value = "getPointCut()", throwing = "e")
    public void catchExceptionByAspect(JoinPoint joinPoint, Exception e) {
        try {
            Object[] args = joinPoint.getArgs();
            for (Object object : args) {
                for (Field field : object.getClass().getDeclaredFields()) {
                    if (field.getName().toLowerCase().contains("base64")) {
                        PropertyDescriptor pd = new PropertyDescriptor(field.getName(), object.getClass());
                        Method wM = pd.getWriteMethod();
                        wM.invoke(object, "");
                    }
                }

                ObjectMapper mapper = new ObjectMapper();
                String json = mapper.writeValueAsString(object);
                logger.info("入参: " + json);
            }

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
