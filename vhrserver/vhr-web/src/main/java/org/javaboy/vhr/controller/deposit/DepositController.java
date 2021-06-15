package org.javaboy.vhr.controller.deposit;


import org.javaboy.vhr.deposit.Appointment;
import org.javaboy.vhr.model.PageInfo;
import org.javaboy.vhr.model.RespBean;
import org.javaboy.vhr.model.RespPageBean;
import org.javaboy.vhr.service.deposit.DepositBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/deposit/base/")
public class DepositController {

    @Autowired
    DepositBaseService depositBaseService;

    @RequestMapping("add")
    public RespBean add(@RequestBody Appointment appointment) {

        depositBaseService.add(appointment);
        return RespBean.ok("保存成功");
    }

    @RequestMapping("list")
    public RespPageBean list(@RequestBody PageInfo info) {

        return depositBaseService.list(info);
    }


    @RequestMapping("createCode")
    public RespBean createCode(@RequestParam("name") String name) {
        return depositBaseService.createCode(name);
    }

    @RequestMapping("import")
    public RespBean importExcel(MultipartFile file){

        return depositBaseService.importExcel(file);
    }

}
