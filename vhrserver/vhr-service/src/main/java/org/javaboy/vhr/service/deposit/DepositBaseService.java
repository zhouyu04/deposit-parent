package org.javaboy.vhr.service.deposit;

import org.apache.commons.lang3.StringUtils;
import org.javaboy.vhr.common.utils.DateTimeUtil;
import org.javaboy.vhr.common.utils.ExcelUtils;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.util.Date;
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

        String projectId = appointment.getProject();
        if (StringUtils.isBlank(projectId)) {
            ProjectInfo projectInfo = new ProjectInfo();
            projectInfo.setName(appointment.getProject());
            projectInfo.setCode(appointment.getProjectCode());

            depositProMapper.add(projectInfo);
            projectId = projectInfo.getId();
        }
        System.out.println("保存项目ID:" + projectId);

        appointment.setProjectId(projectId);

        depositBaseMapper.addAppointment(appointment);
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

        StringBuilder sb = new StringBuilder();

        String firstSpell = sb.append(PinYinUtil.getFirstSpell(name))
                .append("_")
                .append(DateTimeUtil.format(new Date(), DateTimeUtil.SUP_SHORT_PATTERN)).toString();


        return RespBean.ok(firstSpell);
    }

    public RespBean importExcel(MultipartFile file) {

        ExcelUtils.readExcelFileToDTO(file,)


        return null;
    }
}
