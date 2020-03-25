package com.ljq.bishe.service;

import com.ljq.bishe.pojo.Homework;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HandInService {
    public List<Homework> workInfo(String stuid);
    public List<Homework> findHomeworkList(String stuid,String course, String courseClass, String homeworkStatus);
    public List getLearningCourse(String stuid);
    public List getCourseClass(String stuid);
    public void handIn(String stuid, String course, String courseClass, String workname,String uploadWorkName);
    public String getHandInPath(String stuid, String course, String courseClass,String workName);
    public List getWorkName(String stuid);
    public String getStuname(String stuid);
}
