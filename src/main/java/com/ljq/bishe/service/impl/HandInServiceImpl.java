package com.ljq.bishe.service.impl;

import com.ljq.bishe.mapper.HandInMapper;
import com.ljq.bishe.pojo.Homework;
import com.ljq.bishe.service.HandInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HandInServiceImpl implements HandInService{
    @Autowired
    HandInMapper hip;
    public List<Homework> workInfo(String stuid) {
        return hip.workInfo(stuid);
    }

    @Override
    public List<Homework> findHomeworkList(String stuid, String course, String courseClass, String homeworkStatus) {
        return hip.findHomeworkList(stuid,course,courseClass,homeworkStatus);
    }

    @Override
    public List getLearningCourse(String stuid) {
        return hip.getLearningCourse(stuid);
    }

    @Override
    public List getCourseClass(String stuid) {
        return hip.getCourseClass(stuid);
    }

    @Override
    public void handIn(String stuid, String course, String courseClass, String workName,String uploadWorkName) {
        hip.handIn(stuid,course,courseClass,workName,uploadWorkName);
    }

    @Override
    public String getHandInPath(String stuid, String course, String courseClass,String workName) {
        return hip.getHandInPath(stuid,course,courseClass,workName);
    }

    @Override
    public List getWorkName(String stuid) {
        return hip.getWorkName(stuid);
    }
    public String getStuname(String stuid){
        return  hip.getStuname(stuid);
    }
}
