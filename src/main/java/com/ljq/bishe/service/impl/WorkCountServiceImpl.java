package com.ljq.bishe.service.impl;

import com.ljq.bishe.mapper.WorkCountMapper;
import com.ljq.bishe.pojo.Course;
import com.ljq.bishe.pojo.ScoreList;
import com.ljq.bishe.service.WorkCounstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class WorkCountServiceImpl implements WorkCounstService {
    @Autowired
    WorkCountMapper wc;
    public ArrayList courseList(String teaid) {
        return wc.courseList(teaid);
    }

    @Override
    public ArrayList courseList1(String teaid, String coursename) {
        return wc.courseList1(teaid,coursename);
    }

    public ArrayList<Course> courseClassList(String teaid) {
        return wc.courseClassList(teaid);
    }

    @Override
    public int columnCount() {
        return wc.columnCount();
    }

    @Override
    public List<ScoreList> scoreList(String teaid,String course,String courseClass) {
        return wc.scoreList(teaid,course,courseClass);
    }

    @Override
    public List allWorkData(String teaid) {
        return wc.allWorkData(teaid);
    }
}
