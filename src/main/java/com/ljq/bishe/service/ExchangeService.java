package com.ljq.bishe.service;

import com.ljq.bishe.pojo.Message;
import com.ljq.bishe.pojo.Reply;

import java.util.List;

public interface ExchangeService {
    public List<Message> messageList(String stuid);
    public List<Reply> replyList();
    public List<Message> teaMessageList(String teaid);
    public List<Reply> teaReplyList();
    public void teaSendMessage( String leavename,String leaveid,String messagebody,String messagetype,String courseid,String leavedate);
    public void sendMessage( String leavename,String leaveid,String messagebody,String messagetype,String courseid,String leavedate);
    public void teaReplyMessage(String messageid,String replybody,String replydate,String teaid);
    public void replyMessage(String messageid,String replybody,String replydate,String stuid);
    public List<Message> teaFindMessage(String coursename,String courseclass, String teaid);
    public List<Message> findMessage(String coursename,String courseclass, String stuid);
    public List<Message> teaMyMessage(String teaid);
    public List<Message> myMessage(String stuid);
}
