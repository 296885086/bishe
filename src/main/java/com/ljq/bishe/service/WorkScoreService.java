package com.ljq.bishe.service;

import com.ljq.bishe.pojo.Score;

import java.util.List;

public interface WorkScoreService {
    public List<Score> findStudentScore(String course,String courseClass,String state,String teaid,String workName);
    public List<Score> findAllScore(String teaid);
    public List findWorkname(String teaid);
    public void modifyScore(String score,String workid,String stuid);
}
