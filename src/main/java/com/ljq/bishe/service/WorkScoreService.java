package com.ljq.bishe.service;

import com.ljq.bishe.pojo.Score;

import java.util.List;

public interface WorkScoreService {
    public List<Score> findStudentScore(String course,String courseClass,String state,String teaid);
}
