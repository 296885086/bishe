package com.ljq.bishe.service.impl;

import com.ljq.bishe.mapper.MyDataMapper;
import com.ljq.bishe.pojo.Score;
import com.ljq.bishe.service.MyDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyDataServiceImpl implements MyDataService {
    @Autowired
    MyDataMapper myDataMapper;
    @Override
    public List<Score> allMyDdata(String stuid) {
        return myDataMapper.allMyDdata(stuid);
    }

    @Override
    public void saveQuestion(String stuid, String workid, String question) {
        myDataMapper.saveQuestion(stuid,workid,question);
    }

    @Override
    public List<Score> findMyData(String stuid,String course, String courseClass, String homeworkState) {
        return myDataMapper.findMyData(stuid,course,courseClass,homeworkState);
    }
}
