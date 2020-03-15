package com.ljq.bishe.service;

import com.ljq.bishe.pojo.Message;
import com.ljq.bishe.pojo.Reply;

import java.util.List;

public interface ExchangeService {
    public List<Message> messageList();
    public List<Reply> replyList();
    public void sendMessage( String leavename,String leaveid,String messagebody,String messagetype,String courseid,String leavedate);
    public void replyMessage(String messageid,String replybody,String replydate,String stuid);
    public List<Message> findMessage(String coursename,String courseclass, String stuid);
}
