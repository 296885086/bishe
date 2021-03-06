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
    public List getLearningCourse(String stuid) {
        return hip.getLearningCourse(stuid);
    }
}
