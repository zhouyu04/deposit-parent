package org.javaboy.vhr.controller.deposit;

import com.alibaba.fastjson.JSON;
import org.javaboy.vhr.deposit.SubjectInfo;
import org.javaboy.vhr.model.RespBean;
import org.javaboy.vhr.model.RespPageBean;
import org.javaboy.vhr.service.deposit.DepositSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deposit/sub/")
public class DepositController {

    @Autowired
    DepositSubService depositSubService;

    @RequestMapping("add")
    public RespBean add(@RequestBody SubjectInfo subjectInfo) {

        return depositSubService.add(subjectInfo);
    }

    @RequestMapping("list")
    public RespPageBean list(@RequestBody SubjectInfo subjectInfo) {

        RespPageBean list = depositSubService.list(subjectInfo);
        System.out.println(JSON.toJSONString(list));
        return list;


    }


}
