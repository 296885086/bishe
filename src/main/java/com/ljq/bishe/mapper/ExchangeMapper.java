package com.ljq.bishe.mapper;

import com.ljq.bishe.pojo.Message;
import com.ljq.bishe.pojo.Reply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ExchangeMapper {
    public List<Message> messageList();
    public List<Reply> replyList();
    public void sendMessage(@Param("leavename") String leavename,
                            @Param("leaveid")String leaveid,
                            @Param("messagebody") String messagebody,
                            @Param("messagetype") String messagetype,
                            @Param("courseid") String courseid,
                            @Param("leavedate") String leavedate);
    public void replyMessage(@Param("messageid") String messageid,
                             @Param("replybody") String replybody,
                             @Param("replydate") String replydate,
                             @Param("stuid") String stuid);
    public List<Message> findMessage(@Param("coursename") String coursename,
                             @Param("courseclass") String courseclass,
                             @Param("stuid") String stuid);
}
