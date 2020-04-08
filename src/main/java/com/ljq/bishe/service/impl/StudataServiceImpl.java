package com.ljq.bishe.service.impl;

import com.ljq.bishe.mapper.StudataMapper;
import com.ljq.bishe.pojo.Student;
import com.ljq.bishe.service.StudataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudataServiceImpl implements StudataService {
    @Autowired
    StudataMapper sm;
    public List<Student> stulist(int teaid) {
        return sm.stulist(teaid);
    }

    @Override
    public ArrayList classlist(int teaid) {
        return sm.classlist(teaid);
    }

    @Override
    public ArrayList<Student> exportdata(String cl) {
        return sm.exportdata(cl);
    }

    @Override
    public void importdata(Student student) {
        sm.importdata(student);
    }

    @Override
    public List<Student> findStuInfo(String conditionInfo, String condition) {
        return sm.findStuInfo(conditionInfo,condition);
    }

    @Override
    public void editStuData(String initStuid,String stuid, String stuname, String stusex, String stuclass, String stuphone) {
        sm.editStuData(initStuid,stuid,stuname,stusex,stuclass,stuphone);
    }

    @Override
    public void deleteStuData(String stuid) {
        sm.deleteStuData(stuid);
    }
}
