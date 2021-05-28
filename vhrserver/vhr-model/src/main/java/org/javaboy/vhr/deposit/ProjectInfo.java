package org.javaboy.vhr.deposit;

import lombok.Data;
import org.javaboy.vhr.model.PageInfo;

@Data
public class ProjectInfo extends PageInfo {

    private String id;

    private String name;

    private String code;
}
