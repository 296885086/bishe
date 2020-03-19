package com.ljq.bishe.service.impl;

import com.ljq.bishe.mapper.CourseManagerMapper;
import com.ljq.bishe.service.CourseManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseManagerServiceImpl implements CourseManagerService {
    @Autowired
    CourseManagerMapper courseManagerMapper;
    @Override
    public List teaCourseManger(String teaid) {
        return courseManagerMapper.teaCourseManger(teaid);
    }

    @Override
    public List stuCourseManger(String teaid) {
        return courseManagerMapper.stuCourseManger(teaid);
    }

    @Override
    public void writeAddCourse(String courseClass, String courseName,String teaid) {
        courseManagerMapper.writeAddCourse(courseClass,courseName,teaid);
    }

    @Override
    public List totalStudent(String teaid) {
        return courseManagerMapper.totalStudent(teaid);
    }

    @Override
    public void writeAddStuCourse(String courseClass, String courseName, String teaid, String stuId) {
        courseManagerMapper.writeAddStuCourse(courseClass,courseName,teaid,stuId);
    }

    @Override
    public void deleteCourse(String courseid) {
        courseManagerMapper.deleteCourse(courseid);
    }

    @Override
    public void deleteStuCourse(String selectcourseid) {
        courseManagerMapper.deleteStuCourse(selectcourseid);
    }
}
