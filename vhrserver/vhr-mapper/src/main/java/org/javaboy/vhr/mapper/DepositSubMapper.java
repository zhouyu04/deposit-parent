package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.javaboy.vhr.deposit.SubjectInfo;

import java.util.List;
import java.util.Set;

@Mapper
public interface DepositSubMapper {

    void add(SubjectInfo subjectInfo);

    List<SubjectInfo> list(SubjectInfo subjectInfo);

    long listCount(SubjectInfo subjectInfo);

    List<SubjectInfo> listByCodes(@Param("subCodes") Set<String> subCodes);
}
