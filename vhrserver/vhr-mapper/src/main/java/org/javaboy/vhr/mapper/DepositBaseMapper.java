package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.javaboy.vhr.deposit.Appointment;
import org.javaboy.vhr.model.PageInfo;

import java.util.List;

@Mapper
public interface DepositBaseMapper {
    List<Appointment> list(PageInfo info);
}
