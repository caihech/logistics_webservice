package com.api.webservice.service;


import com.api.webservice.dao.entity.ConsignmentNote;
import com.api.webservice.dao.entity.User;
import com.api.webservice.dao.repository.ConsignmentNoteRepository;
import com.api.webservice.utils.exception.SC_BAD_REQUEST;
import com.api.webservice.utils.exception.SC_INTERNAL_SERVER_ERROR;
import com.api.webservice.utils.exception.SC_NOT_FOUND;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    public ConsignmentNote post(User user, ConsignmentNote consignmentNote) {
        if (user == null || consignmentNote == null) {
            log.error("400,param is null.");
            throw new SC_BAD_REQUEST();
        }

        try {

//            consignmentNote.setOrderNumber();
            consignmentNote.setUser(user);
            consignmentNote.setVehicle(null);
            consignmentNote.setCheck(0);
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

        if (consignmentNoteRet.getCheck() == 1) {
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

}