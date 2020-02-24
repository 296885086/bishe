package com.ljq.bishe.service;

import com.ljq.bishe.pojo.Course;
import com.ljq.bishe.pojo.ScoreList;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

public interface WorkCounstService {
    public ArrayList courseList(String teaid);
    public ArrayList courseList1(@Param("teaid")String teaid, @Param("coursename")String coursename);
    public ArrayList<Course> courseClassList(String teaid);
    public int columnCount();
    public List<ScoreList> scoreList(String teaid,String course,String courseClass);
}
