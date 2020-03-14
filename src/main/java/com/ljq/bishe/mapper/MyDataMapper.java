package com.ljq.bishe.mapper;

import com.ljq.bishe.pojo.Score;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MyDataMapper {
    public List<Score> allMyDdata(String stuid);
    public void saveQuestion(@Param("stuid") String stuid,
                             @Param("workid")String workid,
                             @Param("question") String question);
    public List<Score> findMyData(@Param("stuid") String stuid,
                                  @Param("course") String course,
                                  @Param("courseClass")String courseClass,
                                  @Param("homeworkState") String homeworkState);
}
