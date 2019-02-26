package com.api.webservice.service;


import com.api.webservice.dao.entity.ConsignmentNote;
import com.api.webservice.dao.entity.User;
import com.api.webservice.dao.repository.ConsignmentNoteRepository;
import com.api.webservice.utils.EnumUtils;
import com.api.webservice.utils.exception.SC_BAD_REQUEST;
import com.api.webservice.utils.exception.SC_INTERNAL_SERVER_ERROR;
import com.api.webservice.utils.exception.SC_NOT_FOUND;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * 委托单
 *
 * @author h.cai
 * @date 2018/06/20
 */
@Service
public class ConsignmentNoteService extends BaseService {

    @Autowired
    private ConsignmentNoteRepository consignmentNoteRepository;


    /**
     * 查询所有
     *
     * @return list
     */
    public List<ConsignmentNote> query() {
        return consignmentNoteRepository.findAll();
    }

    /**
     * 查询一条数据
     *
     * @param id 主键
     * @return empty
     */
    public ConsignmentNote get(Long id) {

        ConsignmentNote consignmentNote = consignmentNoteRepository.findOne(id);

        if (consignmentNote == null) {
            log.error("404 consignmentNote id not find.");
            throw new SC_NOT_FOUND();
        }

        return consignmentNote;
    }


    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    public ConsignmentNote post(User user, ConsignmentNote consignmentNote) {
        if (user == null || consignmentNote == null) {
            log.error("400,param is null.");
            throw new SC_BAD_REQUEST();
        }

        try {
            Integer maxId = consignmentNoteRepository.findMaxId();
            if (maxId == null) {
                maxId = 0;
            }
            maxId += 1;
            consignmentNote.setOrderNumber(lifeToFill(maxId + "", 8, "0"));
            consignmentNote.setUser(user);
            consignmentNote.setVehicle(null);
            consignmentNote.setCheckStatus(0);
            consignmentNote.setCheckUsername(null);
            consignmentNote.setCheckDate(null);
            consignmentNote.setCheckMessage(null);
            consignmentNote = consignmentNoteRepository.saveAndFlush(consignmentNote);

        } catch (Exception ex) {
            log.error("500," + ex.getMessage());
            throw new SC_INTERNAL_SERVER_ERROR();
        }
        return consignmentNote;
    }

    /**
     * 左侧填充
     *
     * @param values    当前属性 如 5
     * @param length    填充长度 如 4
     * @param chatValue 填充符号  如 0
     * @return 0005
     */
    public static String lifeToFill(String values, int length, String chatValue) {
        if (values.length() >= length) {
            return values;
        }
        String strA = "";
        for (int i = 0; i < length - values.length(); i++) {
            strA += chatValue + "";
        }
        return strA + values;
    }


    /**
     * 修改
     *
     * @param tokenUser
     * @param consignmentNote
     * @return
     */
    public ConsignmentNote put(User tokenUser, ConsignmentNote consignmentNote) {

        if (tokenUser == null || tokenUser.getRole() == null || consignmentNote == null) {
            log.error("400  put user param is null.");
            throw new SC_BAD_REQUEST();
        }
        ConsignmentNote consignmentNoteRet = consignmentNoteRepository.findOne(consignmentNote.getId());

        if (consignmentNoteRet == null) {
            log.error("404  put user not find.");
            throw new SC_BAD_REQUEST();
        }

        if (consignmentNoteRet.getCheckStatus() == 1) {
            log.error("403  check == 1  not permissions.");
            throw new SC_BAD_REQUEST();
        }

        //修改能修改的属性
        consignmentNoteRet.setStation(consignmentNote.getStation());
        consignmentNoteRet.setArticleNumber(consignmentNote.getArticleNumber());
        consignmentNoteRet.setConsignmentDate(consignmentNote.getConsignmentDate());
        consignmentNoteRet.setGoodsAddress(consignmentNote.getGoodsAddress());
        consignmentNoteRet.setShippersName(consignmentNote.getShippersName());
        consignmentNoteRet.setShippersPhone(consignmentNote.getShippersPhone());
        consignmentNoteRet.setConsigneeName(consignmentNote.getConsigneeName());
        consignmentNoteRet.setConsigneePhone(consignmentNote.getConsigneePhone());
        consignmentNoteRet.setGoodsName(consignmentNote.getGoodsName());

        consignmentNoteRet.setPackaging(consignmentNote.getPackaging());
        consignmentNoteRet.setVolume(consignmentNote.getVolume());
        consignmentNoteRet.setNumber(consignmentNote.getNumber());
        consignmentNoteRet.setInsurance(consignmentNote.getInsurance());
        consignmentNoteRet.setPremium(consignmentNote.getPremium());
        consignmentNoteRet.setMonthlyStatement(consignmentNote.getMonthlyStatement());
        consignmentNoteRet.setReceiptPayment(consignmentNote.getReceiptPayment());
        consignmentNoteRet.setCashPayment(consignmentNote.getCashPayment());
        consignmentNoteRet.setExtractPayment(consignmentNote.getExtractPayment());
        consignmentNoteRet.setShortHaulFreight(consignmentNote.getShortHaulFreight());
        consignmentNoteRet.setAmount(consignmentNote.getAmount());
        consignmentNoteRet.setCollectionOnDelivery(consignmentNote.getCollectionOnDelivery());
        consignmentNoteRet.setDeliveryAddress(consignmentNote.getDeliveryAddress());
        consignmentNoteRet.setRemark(consignmentNote.getRemark());


        consignmentNoteRet.setConsignee(consignmentNote.getConsignee());
        consignmentNoteRet.setIdCard(consignmentNote.getIdCard());
        consignmentNoteRet.setConsignee(consignmentNote.getConsignee());

        //TODO 暂时不支持更新属性
        //  consignmentNoteRet.setVehicle();


        consignmentNoteRet = consignmentNoteRepository.saveAndFlush(consignmentNoteRet);

        if (consignmentNoteRet == null) {
            throw new SC_INTERNAL_SERVER_ERROR();
        }

        return consignmentNoteRet;
    }


    /**
     * put Check Status
     *
     * @param tokenUser
     * @param consignmentNote
     * @return
     */
    public ConsignmentNote putCheckStatus(User tokenUser, ConsignmentNote consignmentNote) {

        if (tokenUser == null || tokenUser.getRole() == null || consignmentNote == null) {
            log.error("400  put user param is null.");
            throw new SC_BAD_REQUEST();
        }
        ConsignmentNote consignmentNoteRet = consignmentNoteRepository.findOne(consignmentNote.getId());

        if (consignmentNoteRet == null) {
            log.error("404  put user not find.");
            throw new SC_BAD_REQUEST();
        }

        if (consignmentNoteRet.getCheckStatus() == 1) {
            log.error("403  check == 1  not permissions.");
            throw new SC_BAD_REQUEST();
        } else {

            //修改能修改的属性
            consignmentNoteRet.setCheckStatus(1);
            consignmentNoteRet.setCheckUsername(tokenUser.getUsername());
            consignmentNoteRet.setCheckDate(new Timestamp(System.currentTimeMillis()));
            consignmentNoteRet.setCheckMessage(consignmentNote.getCheckMessage());

            consignmentNoteRet = consignmentNoteRepository.saveAndFlush(consignmentNoteRet);

            if (consignmentNoteRet == null) {
                throw new SC_INTERNAL_SERVER_ERROR();
            }
        }

        return consignmentNoteRet;
    }


    public boolean delete(User tokenUser, long id) {
        if (tokenUser == null) {
            log.error("400  put user param is null.");
            throw new SC_BAD_REQUEST();
        }

        ConsignmentNote consignmentNoteRet = consignmentNoteRepository.findOne(id);
        if (consignmentNoteRet == null) {
            log.error("404 get user is not find.");
            throw new SC_NOT_FOUND();
        }

        if (consignmentNoteRet.getCheckStatus() == 1) {
            log.error("403  check is 1  permissions.");
            throw new SC_BAD_REQUEST();
        }

        if (tokenUser.getRole().getId() != EnumUtils.Role.ADMINISTRATOR.key &&
                consignmentNoteRet.getUser().getId() != tokenUser.getId()) {
            log.error("403  user is num admin and not is create user  permissions.");
            throw new SC_BAD_REQUEST();
        }

//        consignmentNoteRet.setVehicle(null);
//        consignmentNoteRet = consignmentNoteRepository.saveAndFlush(consignmentNoteRet);
        consignmentNoteRepository.delete(consignmentNoteRet);
        return true;
    }


}