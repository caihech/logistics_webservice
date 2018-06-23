package com.api.webservice.service;


import com.api.webservice.dao.entity.Login;
import com.api.webservice.dao.entity.User;
import com.api.webservice.dao.repository.LoginRepository;
import com.api.webservice.utils.RandomUtil;
import com.api.webservice.utils.exception.SC_BAD_REQUEST;
import com.api.webservice.utils.exception.SC_INTERNAL_SERVER_ERROR;
import com.api.webservice.utils.exception.SC_NOT_FOUND;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

/**
 * 登录日志
 *
 * @author h.cai
 * @date 2018/06/20
 */
@Service
public class LoginService extends BaseService {

    @Autowired
    private LoginRepository loginRepository;


    /**
     * 查询所有
     *
     * @return list
     */
    public List<Login> query() {
        return loginRepository.findAll();
    }


    /**
     * 查询一条数据
     *
     * @param id 主键
     * @return empty
     */
    public Login get(Long id) {

        Login login = loginRepository.findOne(id);

        if (login == null) {
            log.error("loginLog is not find.");
            throw new SC_NOT_FOUND();
        }

        return login;
    }

    /**
     * 获取登录验证码
     *
     * @param ip
     * @param client
     * @return
     */
    public Login getCode(String ip, String client) {
        if (ip == null || ip.isEmpty()) {
            log.error("400 ip is null.");
            throw new SC_BAD_REQUEST();
        }
        Login login = null;
        try {
            RandomUtil randomUtil = RandomUtil.getInstance();
            login = new Login();
            login.setIp(ip);
            login.setCode(randomUtil.getCapitalOrNumber(4));
            login.setClient(client);
            login.setStatus(null);
            login.setToken(UUID.randomUUID().toString());
            //TODO 有效时间参数化
            Timestamp timestamp = new Timestamp(System.currentTimeMillis() + 100000);
            login.setTokenExpired(timestamp);

            login = loginRepository.saveAndFlush(login);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new SC_INTERNAL_SERVER_ERROR();
        }

        login.setToken(null);
        login.setTokenExpired(null);
        return login;

    }

    /**
     * 登录
     *
     * @param _login
     * @param _ip
     * @return
     */
    public Login post(Login _login, String _ip) {
        // 1.检查入参数据结
        if (_login == null || _login.getUsername() == null || _login.getPassword() == null || _login.getCode() == null || _ip == null || "".equals(_ip)) {
            log.error("入参数据异常.");
            throw new SC_BAD_REQUEST();
        }

        //获取登录信息
        Login loginSql = loginRepository.findOne(_login.getId());
        if (loginSql == null || loginSql.getStatus() != null ||
                System.currentTimeMillis() > loginSql.getTokenExpired().getTime()
                || _login.getCode().equals(loginSql.getCode()) == false) {

            log.error("验证码异常.");
            throw new SC_BAD_REQUEST();
        }

//        //查询4次 循环4次是否0 系统时间减去最后一次的时间 和 锁定时间 对比 大于 进行下去 小于 退出 409
//        int failNumber = disableLogin(loginModel.getUsername());
//        if (failNumber < 0) {
//            throw new SC_CONFLICT();
//        }
//
        //4.通过账号密码查询有效用户
 //       User user = userRepository.findByUsernameAndPasswordAndValid(loginModel.getUsername(), CommonUtils.getSha256(loginModel.getPassword()), true);

//        //5.记录登录信息
//        if (user == null) {
//            loginRecord.setState(EnumUtils.LoginState.FAILURE.key);
//            loginRecord.setUsername(loginModel.getUsername());
//            loginRecord.setTimes(failNumber + 1);
//
//            loginRecordRepository.saveAndFlush(loginRecord);
//        } else {
//            loginRecord.setState(EnumUtils.LoginState.SUCCESS.key);
//            loginRecord.setUsername(loginModel.getUsername());
//            loginRecord.setTimes(failNumber);
//
//            if (loginModel.getUdid() != null) {
//                loginRecord.setUdid(loginModel.getUdid());
//                Timestamp timestamp = new Timestamp(System.currentTimeMillis() + systemConfig.getTokenExpried() * 1000);
//                loginRecord.setTokenExpired(timestamp);
//            }
//
//            loginRecord.setUser(user);
//
//            loginRecordRepository.saveAndFlush(loginRecord);
//
//            ableLogin(loginModel.getUsername());
//        }
//
//        //删除数据
//        loginRecordRepository.deleteStateAndUsernameIsNull(ip);
//
        return null;
    }


    public User getEffectiveUserByToken(String token) {

        Login login = loginRepository.findByToken(token);
        //if(loginLog==null||)
        return null;
    }

}