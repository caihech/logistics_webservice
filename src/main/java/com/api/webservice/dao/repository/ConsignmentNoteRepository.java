package com.api.webservice.dao.repository;

import com.api.webservice.dao.entity.ConsignmentNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * 委托单
 *
 * @author h.cai
 * @date 2018/06/20
 */
@Repository
public interface ConsignmentNoteRepository extends JpaRepository<ConsignmentNote, Long> {


}
