package com.example.demo.user.group.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.user.group.service.groupMemberService;

@Controller
@RequestMapping("/user/group/manage")
public class groupManageController {
    @Autowired
    groupMemberService memberService;

    @ResponseBody
    @RequestMapping("/{gid}/member")
    String getMember(@PathVariable("gid")String gid){
        return memberService.getMembers(gid).toString();
    }

    @ResponseBody
    @RequestMapping("/{gid}/add/{uid}")
    String addMember(@PathVariable("gid")String gid,@PathVariable("uid")String uid){
        if(memberService.addMember(uid, gid)==-1) return "failed";
        return "添加用户["+uid+"]到group["+gid+"]成功";
    }
}