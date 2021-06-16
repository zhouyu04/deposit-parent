package org.javaboy.vhr.service.deposit;

import org.apache.commons.lang3.StringUtils;
import org.javaboy.vhr.common.utils.ExcelUtils;
import org.javaboy.vhr.config.BizCustomException;
import org.javaboy.vhr.deposit.Appointment;
import org.javaboy.vhr.deposit.ProjectInfo;
import org.javaboy.vhr.deposit.SubjectInfo;
import org.javaboy.vhr.excel.AppointmentImportDto;
import org.javaboy.vhr.mapper.DepositBaseMapper;
import org.javaboy.vhr.model.PageInfo;
import org.javaboy.vhr.model.RespBean;
import org.javaboy.vhr.model.RespPageBean;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepositBaseService {


    @Autowired
    DepositBaseMapper depositBaseMapper;

    @Autowired
    DepositProService depositProService;

    @Autowired
    DepositSubService depositSubService;

    @Transactional
    public void add(Appointment appointment) {

        checkParams(appointment);

        String projectId = appointment.getProjectId();
        if (StringUtils.isBlank(projectId)) {
            projectId = depositProService.addByName(appointment.getProject());
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


    public RespBean importExcel(MultipartFile file) throws IOException {

        List<AppointmentImportDto> appointments = ExcelUtils.readExcelFileToDTO(file, AppointmentImportDto.class);
        System.out.println(appointments);
        if (CollectionUtils.isEmpty(appointments)) {
            throw new BizCustomException(301, "导入数据不能为空");
        }
        //将对象中的数据一行一行处理成与数据库可行的
        //1.获取科目准备转换
        Set<String> subCodes = appointments.stream().map(AppointmentImportDto::getSubjectCode).collect(Collectors.toSet());
        List<SubjectInfo> subjectInfos = depositSubService.listByCodes(subCodes);
        Map<String, String> subMap = convertSubMap(subjectInfos);
        //2.获取摘要准备转换
        Set<String> proNames = appointments.stream().map(AppointmentImportDto::getProject).collect(Collectors.toSet());
        List<ProjectInfo> projectInfoList = depositProService.listByName(proNames);
        Map<String, String> proMap = convertProMap(projectInfoList);
        //2.将excel数据转换成
        List<Appointment> appointmentList = new ArrayList<>();
        for (AppointmentImportDto dto : appointments) {
            Appointment appointment = new Appointment();
            BeanUtils.copyProperties(dto, appointment);
            //处理科目值
            String subId = subMap.containsKey(dto.getSubjectCode()) ? subMap.get(dto.getSubjectCode()) : "";
            appointment.setSubId(subId);
            //处理借贷值
            String directionStr = dto.getDirectionStr();
            if (StringUtils.equals(directionStr, "借")) {
                appointment.setDirection(1);
            } else if (StringUtils.equals(directionStr, "贷")) {
                appointment.setDirection(2);
            } else {
                appointment.setDirection(0);
            }
            //处理摘要
            String project = dto.getProject();
            if (proMap.containsKey(project)) {
                appointment.setProjectId(proMap.get(project));
            } else {
                appointment.setProjectId(depositProService.addByName(project));
            }
            appointmentList.add(appointment);
        }

        depositBaseMapper.batchInsert(appointmentList);
        return RespBean.ok("导入完成");
    }

    private Map<String, String> convertProMap(List<ProjectInfo> projectInfoList) {

        Map<String, String> map = new HashMap<>();
        if (CollectionUtils.isEmpty(projectInfoList)) {
            return map;
        }
        map = projectInfoList.stream().collect(Collectors.toMap(ProjectInfo::getName, ProjectInfo::getId));
        return map;
    }

    private Map<String, String> convertSubMap(List<SubjectInfo> subjectInfos) {

        Map<String, String> map = new HashMap<>();
        if (CollectionUtils.isEmpty(subjectInfos)) {
            return map;
        }
        map = subjectInfos.stream().collect(Collectors.toMap(SubjectInfo::getSubjectCode, SubjectInfo::getId));
        return map;
    }
}
