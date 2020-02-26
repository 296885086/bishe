package com.ljq.bishe.service;

import com.ljq.bishe.pojo.Homework;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HandInService {
    public List<Homework> workInfo(String stuid);
    public List getLearningCourse(String stuid);
    public void handIn(String stuid, String course, String courseClass);
    public String getHandInPath(String stuid, String course, String courseClass);
}
