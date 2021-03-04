package com.smart.project.web.home.vo;

import lombok.Data;
import lombok.Getter;

@Data
public class BoardContentVO {
    private int num;
    private String writer;
    private String head;
    private String content;
    private String mdate;
    private String name;
}
