package com.ljq.bishe.service;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseManagerService {
    public List teaCourseManger(String teaid);
    public List stuCourseManger(String teaid);
    public void writeAddCourse(String courseClass,String courseName,String teaid);
    public List totalStudent(String teaid);
    public void writeAddStuCourse(String courseClass,String courseName,String teaid,String stuId);
}
