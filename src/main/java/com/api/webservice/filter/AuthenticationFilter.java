package com.api.webservice.filter;

import com.api.webservice.service.UsersService;
import com.api.webservice.utils.exception.CustomException;
import com.api.webservice.utils.exception.SC_FORBIDDEN;
import com.api.webservice.utils.exception.SC_UNAUTHORIZED;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component("authenticationFilter")
public class AuthenticationFilter implements Filter {
    protected Logger log;

    @Autowired
    private UsersService userService;

    private String apiKey;
    private String packageName;

    public AuthenticationFilter() {
        this.log = LogManager.getLogger(this.getClass().getName());
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("doFilter " + request.getRemoteHost() + " " + ((HttpServletRequest) request).getMethod());
        String url = ((HttpServletRequest) request).getRequestURI();


        String authorization = ((HttpServletRequest) request).getHeader("Authorization");
        if (null != authorization) {
            //获取token
            int tokenIndex = authorization.indexOf("Token ");
            if (-1 != tokenIndex) {
                tokenIndex = tokenIndex + "Token ".length();
                String token = authorization.substring(tokenIndex);

//                LoginRecord loginRecord = userService.verifyToken(token);
//                if (loginRecord != null) {
//                    request.setAttribute("loginRecord", loginRecord);
//
//                    User user = loginRecord.getUser();
//
//                    if (user.isValid()) {
//                        request.setAttribute("user", user);
//                    } else {
//                        ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                        return;
//                    }
//                } else {
//                    flag = url.contains("/login");
//                    if (!flag) {
//                        ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                        return;
//                    }
//                }
            }
        }

        try {
            chain.doFilter(request, response);
        } catch (SC_UNAUTHORIZED e) {
            ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } catch (SC_FORBIDDEN e) {
            ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_FORBIDDEN);
        } catch (CustomException e) {
            ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_FORBIDDEN);
        } catch (Exception e) {
            ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void destroy() {
        log.info("destroy");
    }
}
