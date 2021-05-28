package org.javaboy.vhr.controller.deposit;

import org.javaboy.vhr.model.PageInfo;
import org.javaboy.vhr.model.RespPageBean;
import org.javaboy.vhr.service.deposit.DepositProService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deposit/pro/")
public class DepositProController {

    @Autowired
    DepositProService depositProService;

    @RequestMapping("/list")
    public RespPageBean list(PageInfo info){

        return depositProService.list(info);

    }



}
