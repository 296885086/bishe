package com.ljq.bishe.mapper;

import com.ljq.bishe.pojo.Student;
import com.ljq.bishe.pojo.Teacher;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {
    public Teacher gettea(String teaname);
    public Student getstu(String stuname);
}
