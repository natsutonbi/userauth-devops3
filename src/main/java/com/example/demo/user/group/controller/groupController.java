package com.example.demo.user.group.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.user.group.dao.entity.group;
import com.example.demo.user.group.service.groupService;
import com.example.demo.user.utils.sessionOper;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user/group")
public class groupController {
    @Autowired
    groupService gserv;

    @ResponseBody
    @RequestMapping("/create")
    String createNewgroup(HttpSession session){
        String username=sessionOper.getUsername(session);
        return gserv.newGroup(username).groupid;
    }//4234290615287808

    @ResponseBody
    @RequestMapping("/get/all")
    String getAll(){
        return gserv.getAll().toString();
    }

    @ResponseBody
    @RequestMapping("/get/{gid}")
    String getGroup(@PathVariable("gid") String groupid){
        return gserv.getByID(groupid).toString();
    }

    @ResponseBody
    @RequestMapping("/set/{gid}/{type}")
    String setGroup(
        @PathVariable("gid")String gid,
        @PathVariable("type")String type,
        @RequestParam("value")String value
    ){
        group g=gserv.getByID(gid);
        if(g==null) return "no such group";
        if("name".equals(type)) gserv.rename(gid, value);
        if("owner".equals(type)) gserv.transOwner(gid, value);
        return "success\nold info: "+g.toString()+"\nnew info: "+gserv.getByID(gid).toString();
    }

    @ResponseBody
    @RequestMapping("/del/{gid}")
    String delGroup(
        @PathVariable("gid")String gid
    ){
        group g=gserv.getByID(gid);
        if(g==null) return "no such group";
        if(gserv.delete(gid)!=-1) return "del group success";
        return "failed to del";
    }
}
