package org.javaboy.vhr.service.deposit;

import org.apache.commons.lang3.StringUtils;
import org.javaboy.vhr.common.utils.PinYinUtil;
import org.javaboy.vhr.config.BizCustomException;
import org.javaboy.vhr.deposit.Appointment;
import org.javaboy.vhr.deposit.ProjectInfo;
import org.javaboy.vhr.mapper.DepositBaseMapper;
import org.javaboy.vhr.mapper.DepositProMapper;
import org.javaboy.vhr.model.PageInfo;
import org.javaboy.vhr.model.RespBean;
import org.javaboy.vhr.model.RespPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepositBaseService {


    @Autowired
    DepositBaseMapper depositBaseMapper;

    @Autowired
    DepositProMapper depositProMapper;

    @Transactional
    public void add(Appointment appointment) {

        checkParams(appointment);

        ProjectInfo projectInfo = new ProjectInfo();
        projectInfo.setName(appointment.getProject());
        projectInfo.setCode(appointment.getProjectCode());

        long proId = depositProMapper.add(projectInfo);
        System.out.println("保存项目ID:" + proId);

        appointment.setProjectId(proId + "");

    }

    private void checkParams(Appointment appointment) {

        if (StringUtils.isBlank(appointment.getProject())) {
            throw new BizCustomException(20001, "摘要不能空为");
        }

        if (StringUtils.isBlank(appointment.getProjectCode())) {
            throw new BizCustomException(20002, "摘要CODE不能为空");
        }
    }

    public RespPageBean list(PageInfo info) {

        int page = (info.getPage() - 1) * info.getSize();
        info.setPage(page);

        RespPageBean respPageBean = new RespPageBean();

        List<Appointment> appointmentList = depositBaseMapper.list(info);
        respPageBean.setData(appointmentList);
        respPageBean.setTotal(100L);

        return respPageBean;
    }

    public RespBean createCode(String name) {

        if (StringUtils.isBlank(name)) {
            return RespBean.ok("");
        }

        String firstSpell = PinYinUtil.getFirstSpell(name);

        return RespBean.ok(firstSpell);
    }
}
