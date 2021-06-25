package org.javaboy.vhr.deposit;

import lombok.Data;
import org.javaboy.vhr.model.PageInfo;

@Data
public class AppQuery extends PageInfo {


    private String skey;

    private String subId;

}
