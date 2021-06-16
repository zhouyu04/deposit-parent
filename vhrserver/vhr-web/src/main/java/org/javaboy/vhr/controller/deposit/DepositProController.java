package org.javaboy.vhr.controller.deposit;

import org.javaboy.vhr.deposit.ProjectInfo;
import org.javaboy.vhr.model.PageInfo;
import org.javaboy.vhr.model.RespBean;
import org.javaboy.vhr.model.RespPageBean;
import org.javaboy.vhr.service.deposit.DepositProService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deposit/pro/")
public class DepositProController {

    @Autowired
    DepositProService depositProService;

    @RequestMapping("/list")
    public RespPageBean list(@RequestBody ProjectInfo info){

        return depositProService.list(info);

    }

    @RequestMapping("createCode")
    public RespBean createCode(@RequestParam("name") String name) {
        return RespBean.ok(depositProService.createCode(name));
    }



}
