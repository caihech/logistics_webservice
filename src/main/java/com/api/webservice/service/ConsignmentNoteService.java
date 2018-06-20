package com.api.webservice.service;


import com.api.webservice.dao.entity.ConsignmentNote;
import com.api.webservice.dao.entity.ConsignmentNoteStatus;
import com.api.webservice.dao.repository.ConsignmentNoteRepository;
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

}