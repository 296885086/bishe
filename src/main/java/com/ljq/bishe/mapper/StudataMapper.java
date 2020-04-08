package com.ljq.bishe.mapper;
import com.ljq.bishe.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;
@Mapper
public interface StudataMapper {
    public List<Student> stulist(int teaid);
    public ArrayList classlist(int teaid);
    public ArrayList<Student> exportdata(String courseclass);
    public void importdata(Student student);
    public List<Student> findStuInfo(@Param("conditionInfo") String conditionInfo,@Param("condition")String condition);
    public void editStuData(@Param("initStuid") String initStuid,
                            @Param("stuid") String stuid,
                            @Param("stuname")String stuname,
                            @Param("stusex") String stusex,
                            @Param("stuclass")String stuclass,
                            @Param("stuphone") String stuphone);
}
