package org.javaboy.vhr.excel;

import lombok.Data;
import org.javaboy.vhr.common.interfaces.FieldMeta;

import java.util.Date;

@Data
public class AppointmentDto {

    @FieldMeta(fileNote = "科目代码")
    private String subjectCode;

    @FieldMeta(fileNote = "科目名称")
    private String subjectName;

    @FieldMeta(fileNote = "日期")
    private Date



}
