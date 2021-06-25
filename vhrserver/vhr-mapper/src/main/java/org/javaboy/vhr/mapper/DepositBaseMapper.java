package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.javaboy.vhr.deposit.AppQuery;
import org.javaboy.vhr.deposit.Appointment;
import org.javaboy.vhr.deposit.AppointmentPro;
import org.javaboy.vhr.model.PageInfo;

import java.util.List;

@Mapper
public interface DepositBaseMapper {
    List<Appointment> list(AppQuery info);

    void addAppointment(Appointment appointment);

    void batchInsert(List<Appointment> list);

    Appointment findById(String id);

    void edit(Appointment appointment);

    void batchInsertReleated(List<AppointmentPro> list);

    long count(PageInfo info);
}
