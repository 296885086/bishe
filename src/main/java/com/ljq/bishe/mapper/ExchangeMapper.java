package com.ljq.bishe.mapper;

import com.ljq.bishe.pojo.Message;
import com.ljq.bishe.pojo.Reply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ExchangeMapper {
    public List<Message> messageList(String stuid);
    public List<Reply> replyList();
    public List<Message> teaMessageList(String teaid);
    public List<Reply> teaReplyList();
    public void sendMessage(@Param("leavename") String leavename,
                            @Param("leaveid")String leaveid,
                            @Param("messagebody") String messagebody,
                            @Param("messagetype") String messagetype,
                            @Param("courseid") String courseid,
                            @Param("leavedate") String leavedate);
    public void teaSendMessage(@Param("leavename") String leavename,
                            @Param("leaveid")String leaveid,
                            @Param("messagebody") String messagebody,
                            @Param("messagetype") String messagetype,
                            @Param("courseid") String courseid,
                            @Param("leavedate") String leavedate);
    public void teaReplyMessage(@Param("messageid") String messageid,
                             @Param("replybody") String replybody,
                             @Param("replydate") String replydate,
                             @Param("teaid") String teaid);
    public void replyMessage(@Param("messageid") String messageid,
                             @Param("replybody") String replybody,
                             @Param("replydate") String replydate,
                             @Param("stuid") String stuid);
    public List<Message> teaFindMessage(@Param("coursename") String coursename,
                                     @Param("courseclass") String courseclass,
                                     @Param("teaid") String teaid);
    public List<Message> findMessage(@Param("coursename") String coursename,
                             @Param("courseclass") String courseclass,
                             @Param("stuid") String stuid);
    public List<Message> teaMyMessage(String teaid);
    public List<Message> myMessage(String stuid);
}
