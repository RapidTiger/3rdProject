package com.smart.project.web.home.biz;

import com.smart.project.proc.Test;
import com.smart.project.web.home.vo.*;
import lombok.extern.slf4j.Slf4j;
import oracle.net.aso.c;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class HomeService {
    @Autowired
    Test test;

    public MemberVO idSelect(String id) {
        MemberVO member = null;
        try {
            member = test.idSelect(id);
        } catch (Exception e) {
        }
        return member;
    }

    public String joinProgram(MemberVO info) {
        String member = null;
        String pro = null;
        try {
            member = test.joinProgram(info);
            member = "success";
            pro = test.joinProfile(info.getId());
            pro = "success";
        } catch (Exception e) {
            member = "fail";
        }
        return member;
    }

    public String loginProgram(MemberVO log) {
        Map<String, Object> ret = new HashMap<>();
        String member = test.loginProgram(log);
        return member;
    }

    public String memberProfile_1(MemberProfileVO profile) {
        String update = null;
        try {
            update = test.memberProfile_1(profile);
            update = "success";
        } catch (Exception e) {
            update = "fail";
        }
        return update;
    }

    public String memberProfile_2(MemberProfileVO profile) {
        String update = null;
        try {
            update = test.memberProfile_2(profile);
            update = "success";
        } catch (Exception e) {
            update = "fail";
        }
        return update;
    }

    public String memberProfile_3(MemberProfileVO profile) {
        String update = null;
        try {
            update = test.memberProfile_3(profile);
            update = "success";
        } catch (Exception e) {
            update = "fail";
        }
        return update;
    }

    public MemberProfileVO profileSelect(String id) {
        MemberProfileVO profile = test.profileSelect(id);
        return profile;
    }

    public String profileInsert(ProfileListVO profile) {
        String result = null;
        try {
            result = test.profileInsert(profile);
            result = "ture";
        } catch (Exception e) {
            result = "false";
        }
        return result;
    }

    public String profileDelete(ProfileListVO profile) {
        String result = null;
        try {
            result = test.profileDelete(profile);
            result = "ture";
        } catch (Exception e) {
            result = "false";
        }
        return result;
    }

    public List<ProfileListVO> profileListSelect() {
        List<ProfileListVO> list =test.profileListSelect();
        if (list.size() == 0) {
            list.add(new ProfileListVO());
        }
        return list;
    }

    public List<ProfileListVO> profileListSelectCtg(ProfileListVO profile) {
        List<ProfileListVO> list =test.profileListSelectCtg(profile);
        if (list.size() == 0) {
            list.add(new ProfileListVO());
        }
        return list;
    }

    public List<ProfileListVO> profileListSelectMore(CtgVo ctg){
        List<ProfileListVO> list = test.profileListSelectMore(ctg);
        return list;
    }

    public List<ProfileListVO> profileListSelectCtgMore(CtgVo ctg){
        List<ProfileListVO> list = test.profileListSelectCtgMore(ctg);
        return list;
    }

    public ProfileListVO profileUserSelect(String user) {
        ProfileListVO list =test.profileUserSelect(user);
        return list;
    }

    public List<MessageHeadVO> messageHead(String log){
        List<MessageHeadVO> head = test.messageHead(log);
        return head;
    }

    public List<MessageHeadVO> messageContents(String id, String friend){
        List<MessageHeadVO> message = test.messageContents(id, friend);
        String read = test.messageRead(id, friend);
        return message;
    }

    public String messageInsert(String send, String received, String content){
        String message = test.messageInsert(send, received, content);
        return message;
    }

    public String messageCheck(String id) {
        String check = test.messageCheck(id);
        return check;
    }

    public String markCheck(String id, String mark){
        String marker = test.markCheck(id, mark);
        return marker;
    }

    public String markInset(String id, String mark, String idName, String markName){
        String marker = test.markInsert(id, mark, idName, markName);
        return marker;
    }

    public String markDelete(String id, String mark){
        String marker = test.markDelete(id, mark);
        return marker;
    }

    public List<MarkVO> markList(String id){
        List<MarkVO> mark = test.markList(id);
        return mark;
    }
    public List<MarkVO> markMeList(String id) {
        List<MarkVO> mark = test.markMeList(id);
        return mark;
    }
}
