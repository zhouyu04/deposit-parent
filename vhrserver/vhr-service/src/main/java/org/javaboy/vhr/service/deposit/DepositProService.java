package org.javaboy.vhr.service.deposit;

import org.javaboy.vhr.mapper.DepositProMapper;
import org.javaboy.vhr.model.PageInfo;
import org.javaboy.vhr.model.RespPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepositProService {

    @Autowired
    DepositProMapper depositProMapper;

    public RespPageBean list(PageInfo info) {
        return null;
    }


}
