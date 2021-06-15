package org.javaboy.vhr.service.deposit;

import org.javaboy.vhr.deposit.ProjectInfo;
import org.javaboy.vhr.mapper.DepositProMapper;
import org.javaboy.vhr.model.RespPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepositProService {

    @Autowired
    DepositProMapper depositProMapper;

    public RespPageBean list(ProjectInfo info) {

        info.setPage((info.getPage() - 1) * info.getSize());
        List<ProjectInfo> projectInfoList = depositProMapper.list(info);
        long count = depositProMapper.count(info);

        RespPageBean res = new RespPageBean();
        res.setData(projectInfoList);
        res.setTotal(count);
        return res;
    }


}
