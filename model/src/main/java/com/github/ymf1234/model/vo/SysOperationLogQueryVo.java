package com.github.ymf1234.model.vo;

import lombok.Data;

@Data
public class SysOperationLogQueryVo {

    private String title;

    private String operationName;

    private String createTimeBegin;
    private String createTimeEnd;
}
