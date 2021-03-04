package com.smart.project.web.home.vo;

import lombok.Data;
import oracle.sql.DATE;

import java.util.Date;

@Data
public class BoardHeadVO {
    private int num;
    private String writer;
    private String head;
    private String mdate;
    private String name;
}
