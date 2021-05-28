package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.javaboy.vhr.deposit.SubjectInfo;

import java.util.List;

@Mapper
public interface DepositSubMapper {

    void add(SubjectInfo subjectInfo);

    List<SubjectInfo> list(SubjectInfo subjectInfo);

    long listCount(SubjectInfo subjectInfo);
}
