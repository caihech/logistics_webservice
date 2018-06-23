package com.api.webservice.filter;

import com.api.webservice.dao.entity.User;
import com.api.webservice.service.LoginService;
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
log.error("ffffffffffff");
        try {

            String url = ((HttpServletRequest) request).getRequestURI();

            String authorization = ((HttpServletRequest) request).getHeader("Authorization");
            if (authorization != null) {
                //获取token
                int tokenIndex = authorization.indexOf("Token ");
                if (tokenIndex != -1) {
                    tokenIndex = tokenIndex + "Token ".length();
                    String token = authorization.substring(tokenIndex);

                    User user = userService.getEffectiveUserByToken(token);
                    if (user != null) {
                        request.setAttribute("user", user);
                    } else {
                        ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        return;
                    }
                }
            }

            chain.doFilter(request, response);

        } catch (SC_UNAUTHORIZED e) {
            ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } catch (SC_FORBIDDEN e) {
            ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_FORBIDDEN);
        } catch (CustomException e) {
            ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_FORBIDDEN);
        } catch (Exception e) {
            if (((HttpServletResponse) response).getStatus() == HttpServletResponse.SC_OK) {
                ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
            return;
        }
    }

    @Override
    public void destroy() {
        log.info("destroy");
    }
}
