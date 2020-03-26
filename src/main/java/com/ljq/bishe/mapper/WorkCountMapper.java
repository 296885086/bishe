package com.ljq.bishe.mapper;

import com.ljq.bishe.pojo.Course;
import com.ljq.bishe.pojo.ScoreList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper
public interface WorkCountMapper {
    public ArrayList courseList(String teaid);
    public ArrayList courseList1(@Param("teaid")String teaid, @Param("coursename")String coursename);
    public ArrayList<Course> courseClassList(String teaid);
    public int columnCount();
    public List<ScoreList> scoreList(@Param("teaid")String teaid, @Param("coursename")String coursename,@Param("courseclass") String courseclass);
    public List allWorkData(String teaid);
}
