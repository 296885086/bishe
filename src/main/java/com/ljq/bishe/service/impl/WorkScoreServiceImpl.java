package com.ljq.bishe.service.impl;

import com.ljq.bishe.mapper.WorkScoreMapper;
import com.ljq.bishe.pojo.Score;
import com.ljq.bishe.service.WorkScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkScoreServiceImpl implements WorkScoreService{
    @Autowired
    WorkScoreMapper ws;
    @Override
    public List<Score> findStudentScore(String course, String courseClass,String state,String teaid) {
        return ws.findStudentScore(course,courseClass,state,teaid);
    }

    @Override
    public List<Score> findAllScore(String teaid) {
        return ws.findAllScore(teaid);
    }

    @Override
    public List findWorkname(String teaid) {
        return ws.findWorkname(teaid);
    }
}
