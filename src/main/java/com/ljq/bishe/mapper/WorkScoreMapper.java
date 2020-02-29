package com.ljq.bishe.mapper;

import com.ljq.bishe.pojo.Score;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WorkScoreMapper {
    public List<Score> findStudentScore(@Param("course")String course,
                                        @Param("courseClass")String courseClass,
                                        @Param("teaid") String teaid,
                                        @Param("state")String state);
}
