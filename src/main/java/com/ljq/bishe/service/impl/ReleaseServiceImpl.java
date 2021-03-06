package com.ljq.bishe.service.impl;

import com.ljq.bishe.mapper.ReleaseMapper;
import com.ljq.bishe.pojo.Homework;
import com.ljq.bishe.service.ReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReleaseServiceImpl implements ReleaseService {
    @Autowired
    ReleaseMapper releaseMapper;
    @Override
    public int workinsert(Homework homework) {
        return releaseMapper.workinsert(homework);
    }

    @Override
    public List<Homework> worklist(String teaid) {
        return releaseMapper.worklist(teaid);
    }

    @Override
    public void workdelete(String workid) {
        releaseMapper.workdelete(workid);
    }

    @Override
    public void addWork(String stuid, String course, String filePath,String uploadClass) {
            releaseMapper.addWork(stuid,course,filePath,uploadClass);
    }

    @Override
    public List getUploadStudent(String course, String courseClass) {
        return releaseMapper.getUploadStudent(course,courseClass);
    }

    @Override
    public void deleteUploadStudent(String stuid, String course, String uploadClass) {
        releaseMapper.deleteUploadStudent(stuid,course,uploadClass);
    }

}
