package com.ljq.bishe.service;

import com.ljq.bishe.pojo.Homework;

import java.util.List;

public interface ReleaseService {
    public int workinsert(Homework homework);
    public List<Homework> worklist(String teaid);
    public void workdelete(String workid);
    public void addWork(String stuid,String course,String filePath,String uploadClass);
    public List getUploadStudent(String course, String courseClass);
    public void deleteUploadStudent(String stuid,String course,String uploadClass);
}
