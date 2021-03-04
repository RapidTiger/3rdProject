package com.smart.project.web.home.vo;

import lombok.Data;

import java.util.Date;

@Data
public class MessageHeadVO {
    private int num;
    private String send;
    private String received;
    private String content;
    private String mdate;
    private String checkMessage;
}
