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

}