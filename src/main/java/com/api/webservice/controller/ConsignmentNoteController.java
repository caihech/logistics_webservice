package com.api.webservice.controller;

import com.api.webservice.annotation.UserAnnotation;
import com.api.webservice.dao.entity.ConsignmentNote;
import com.api.webservice.service.ConsignmentNoteService;
import com.api.webservice.utils.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * 委托单
 *
 * @author h.cai
 * @date 2018/06/20
 */
@CrossOrigin
@RestController
@RequestMapping("/consignmentnote")
public class ConsignmentNoteController extends BaseController {

    @Autowired
    private ConsignmentNoteService consignmentNoteService;

    /**
     * 查询所有
     *
     * @return List
     * @throws Exception 401无效token,403没有权限
     */
    @UserAnnotation(Roles = {EnumUtils.Role.ADMINISTRATOR})
    @RequestMapping(value = "", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.GET)
    public List<ConsignmentNote> query() throws Exception {
        List<ConsignmentNote> userList = consignmentNoteService.query();
        setHttpResponseStatus(HttpServletResponse.SC_OK);
        return userList;
    }

    /**
     * 查询一条数据
     *
     * @param id 主键id
     * @return Empty
     * @throws Exception 401无效token,403没有权限,404不存在
     */
    @UserAnnotation(Roles = {EnumUtils.Role.ADMINISTRATOR, EnumUtils.Role.USER})
    @RequestMapping(value = "/{id}", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.GET)
    public ConsignmentNote get(@PathVariable("id") Long id) throws Exception {
        ConsignmentNote consignmentNote = consignmentNoteService.get(id);
        setHttpResponseStatus(HttpServletResponse.SC_OK);
        return consignmentNote;
    }


    /**
     * 添加
     *
     * @param consignmentNote
     * @return
     * @throws Exception 401无效token,400 入参数据异常 500 保存异常
     */
    @UserAnnotation(Roles = {EnumUtils.Role.ADMINISTRATOR, EnumUtils.Role.USER})
    @RequestMapping(value = "", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.POST)
    public ConsignmentNote post(@RequestBody ConsignmentNote consignmentNote) throws Exception {
        ConsignmentNote consignmentNoteRet = consignmentNoteService.post(super.getUser(), consignmentNote);
        setHttpResponseStatus(HttpServletResponse.SC_CREATED);
        return consignmentNoteRet;
    }


    /**
     * 修改
     *
     * @param id 主键id
     * @return User
     * @throws Exception 400参数错误,401无效token,403没有权限
     */
    @UserAnnotation(Roles = {EnumUtils.Role.ADMINISTRATOR, EnumUtils.Role.USER})
    @RequestMapping(value = "/{id}", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.PUT)
    public ConsignmentNote put(@PathVariable("id") Long id, @RequestBody ConsignmentNote consignmentNote) throws Exception {
        ConsignmentNote consignmentNotetRet = null;

        if (id == consignmentNote.getId()) {
            consignmentNotetRet = consignmentNoteService.put(tokenUser, consignmentNote);
            setHttpResponseStatus(HttpServletResponse.SC_OK);
        } else {
            setHttpResponseStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        return consignmentNotetRet;
    }

}