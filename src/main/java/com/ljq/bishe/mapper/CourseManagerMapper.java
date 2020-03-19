package com.ljq.bishe.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CourseManagerMapper {
    public List teaCourseManger(String teaid);
    public List stuCourseManger(String teaid);
    public void writeAddCourse(@Param("courseClass") String courseClass,
                               @Param("courseName")String courseName,
                               @Param("teaid")String teaid);
    public List totalStudent(String teaid);
    public void writeAddStuCourse(@Param("courseClass") String courseClass,
                                  @Param("courseName")String courseName,
                                  @Param("teaid")String teaid,
                                  @Param("stuId") String stuId);
    public void deleteCourse( String courseid);
    public void deleteStuCourse( String selectcourseid);
}
