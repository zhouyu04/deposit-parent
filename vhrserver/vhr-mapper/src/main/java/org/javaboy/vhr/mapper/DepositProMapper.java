package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.javaboy.vhr.deposit.ProjectInfo;
import org.javaboy.vhr.model.PageInfo;

import java.util.List;
import java.util.Set;

@Mapper
public interface DepositProMapper {

    long add(ProjectInfo projectInfo);

    List<ProjectInfo> list(ProjectInfo info);

    long count(ProjectInfo info);

    List<ProjectInfo> listByName(@Param("list") Set<String> list);
}
