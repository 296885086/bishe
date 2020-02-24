package com.ljq.bishe.mapper;

import com.ljq.bishe.pojo.Homework;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HandInMapper {
    public List<Homework> workInfo(String stuid);
    public List getLearningCourse(String stuid);
}
