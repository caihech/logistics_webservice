package com.api.webservice.service;


import com.api.webservice.dao.entity.ConsignmentNoteStatus;
import com.api.webservice.dao.repository.ConsignmentNoteStatusRepository;
import com.api.webservice.utils.exception.SC_NOT_FOUND;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 委托单_状态
 *
 * @author h.cai
 * @date 2018/06/20
 */
@Service
public class ConsignmentNoteStatusService extends BaseService {

    @Autowired
    private ConsignmentNoteStatusRepository consignmentNoteStatusRepository;


    /**
     * 查询所有
     *
     * @return list
     */
    public List<ConsignmentNoteStatus> query() {
        return consignmentNoteStatusRepository.findAll();
    }


    /**
     * 查询一条数据
     *
     * @param id 主键
     * @return empty
     */
    public ConsignmentNoteStatus get(Long id) {

        ConsignmentNoteStatus consignmentNoteStatus = consignmentNoteStatusRepository.findOne(id);

        if (consignmentNoteStatus == null) {
            log.error("404 consignmentNoteStatus id not find.");
            throw new SC_NOT_FOUND();
        }

        return consignmentNoteStatus;
    }


}