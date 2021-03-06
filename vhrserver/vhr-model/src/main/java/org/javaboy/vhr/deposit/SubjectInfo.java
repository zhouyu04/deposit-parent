package org.javaboy.vhr.deposit;

import lombok.Data;
import org.javaboy.vhr.model.PageInfo;

/**
 * 科目信息
 */
@Data
public class SubjectInfo extends PageInfo {

    private String id;

    private String subjectCode;

    private String subjectName;

    private Integer isActive;//1-可用 2-不可用

    private String skey;//模糊查询条件

}
