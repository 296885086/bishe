package com.ljq.bishe.service;

import com.ljq.bishe.pojo.Student;

import java.util.ArrayList;
import java.util.List;

public interface StudataService {
    public List<Student> stulist(int teaid);
    public ArrayList classlist(int teaid);
    public ArrayList<Student> exportdata(String cl);
    public void importdata(Student student);
    public List<Student> findStuInfo(String conditionInfo,String condition);
}
