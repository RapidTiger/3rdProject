package com.smart.project.web.home.biz;

import com.smart.project.common.vo.MenuVO;
import com.smart.project.proc.Test;
import com.smart.project.web.home.vo.DeptVO;
import com.smart.project.web.home.vo.MembersVO;
import com.smart.project.web.home.vo.SchVO;
import lombok.extern.slf4j.Slf4j;
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

    public Map<String, Object> Test(){
        Map<String, Object> ret = new HashMap<>();
        SchVO sch = new SchVO();
        sch.setDEPTNO(10);
        List<DeptVO> ddd2 = test.sqlMenu4(sch);
        log.warn("ddd2===>{}", ddd2);
        List<DeptVO> ddd = test.sqlMenu3(10);
        log.error("ddd===>{}", ddd);
        return null;
    }

    public Map<String, Object> Test(SchVO sch){
        Map<String, Object> ret = new HashMap<>();
        if(sch.getDEPTNO() != 0){
            List<DeptVO> ddd2 = test.sqlMenu4(sch);
            ret.put("ddd", ddd2);
        }
        return ret;
    }

    public Map<String, Object> member(){
        Map<String, Object> ret = new HashMap<>();
        List<DeptVO> member = test.membersAll();
        ret.put("member", member);
        return ret;
    }
}
