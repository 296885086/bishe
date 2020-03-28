package com.ljq.bishe.service;

import com.ljq.bishe.pojo.Score;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MyDataService {
    public List<Score> allMyDdata(String stuid);
    public void saveQuestion(String stuid,String workid,String question,String commitDate);
    public List<Score> findMyData(String stuid,String course,String courseClass,String homeworkState);
}
