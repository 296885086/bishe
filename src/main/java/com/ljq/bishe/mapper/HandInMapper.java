package com.ljq.bishe.mapper;

import com.ljq.bishe.pojo.Homework;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HandInMapper {
    public List<Homework> workInfo(String stuid);
    public List getLearningCourse(String stuid);
    public void handIn(@Param("stuid") String stuid, @Param("course")String course, @Param("courseClass") String courseClass);
    public String getHandInPath(@Param("stuid") String stuid, @Param("course")String course, @Param("courseClass") String courseClass);
}
