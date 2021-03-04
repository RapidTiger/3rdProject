package com.smart.project.web.home.vo;

import lombok.Data;

@Data
public class MemberVO {
    private String id;
    private String pw;
    private String pwCheck;
    private String name;
    private String birth;
    private String gender;
    private String tel;
}
