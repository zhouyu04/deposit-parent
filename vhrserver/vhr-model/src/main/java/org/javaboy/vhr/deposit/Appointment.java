package org.javaboy.vhr.deposit;

import lombok.Data;
import org.javaboy.vhr.common.interfaces.FieldMeta;

import java.math.BigDecimal;

@Data
public class Appointment {

    private String id;

    private String subId;//科目id

    private String subjectName;

    private String empDate;//业务日期

    private String proof;//凭证

    private String reference;//参考信息

    private String projectId;

    private String project;//摘要-既项目

    private String projectCode;//项目code

    private String businessNo;//业务编号

    private String settlementType;//结算方式

    private String settlement;//结算号

    private String targetSub;//对方科目

    private String sysModule;//系统模块

    private String describe;//描述

    private BigDecimal debitMoney;//借方金额

    private BigDecimal creditMoney;//贷方金额

    private int direction;//方向 1-借 2-贷

    private BigDecimal balance;//余额
}
