package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.javaboy.vhr.deposit.ProjectInfo;

@Mapper
public interface DepositProMapper {

    long add(ProjectInfo projectInfo);


}
