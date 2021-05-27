package org.javaboy.vhr.service.deposit;

import org.javaboy.vhr.deposit.SubjectInfo;
import org.javaboy.vhr.mapper.DepositSubMapper;
import org.javaboy.vhr.model.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepositSubService {

    @Autowired
    DepositSubMapper depositSubMapper;

    public RespBean add(SubjectInfo subjectInfo) {

        depositSubMapper.add(subjectInfo);

        return RespBean.ok("添加成功");
    }

    public RespBean list(SubjectInfo subjectInfo) {

        List<SubjectInfo> infoList = depositSubMapper.list(subjectInfo);

        return RespBean.ok("查询成功", infoList);
    }
}
