package com.ljq.bishe.mapper;

import com.ljq.bishe.pojo.Student;
import com.ljq.bishe.pojo.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoginMapper {
    public Teacher gettea(String teaname);
    public Student getstu(String stuname);
    public void register(@Param("teaid") String teaid,
                            @Param("truename") String truename,
                            @Param("password") String password,
                            @Param("sex") String sex,
                            @Param("teaphone") String teaphone);
}
