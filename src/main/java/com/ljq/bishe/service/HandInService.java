package com.ljq.bishe.service;

import com.ljq.bishe.pojo.Homework;

import java.util.List;

public interface HandInService {
    public List<Homework> workInfo(String stuid);
    public List getLearningCourse(String stuid);
}
