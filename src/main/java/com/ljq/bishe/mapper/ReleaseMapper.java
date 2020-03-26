package com.ljq.bishe.mapper;

import com.ljq.bishe.pojo.Homework;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReleaseMapper {
    public String getTeaName(String teaid);
    public int workinsert(Homework homework);
    public List<Homework> worklist(String teaid);
    public void workdelete(String workid);
    public void addWork(@Param("stuid")String stuid,@Param("course")String course,@Param("filePath")String filePath,
                        @Param("uploadClass")String uploadClass,@Param("teaid") String teaid);
    public List getUploadStudent(@Param("course")String course, @Param("courseClass")String courseClass,@Param("teaid")String teaid);
    public void deleteUploadStudent(@Param("stuid")String stuid,@Param("course")String course,@Param("uploadClass")String uploadClass);

    public List<Homework> findWork(@Param("course")String course, @Param("courseClass")String courseClass,
                                   @Param("teaid")String teaid);
}
