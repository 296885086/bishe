package com.ljq.bishe.service.impl;

import com.ljq.bishe.mapper.ExchangeMapper;
import com.ljq.bishe.pojo.Message;
import com.ljq.bishe.pojo.Reply;
import com.ljq.bishe.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExchangeServiceImpl implements ExchangeService {
    @Autowired
    ExchangeMapper exchangeMapper;

    @Override
    public List<Message> messageList(String stuid) {
        return exchangeMapper.messageList(stuid);
    }

    @Override
    public List<Reply> replyList() {
        return exchangeMapper.replyList();
    }

    @Override
    public List<Message> teaMessageList(String teaid) {
        return exchangeMapper.teaMessageList(teaid);
    }

    @Override
    public List<Reply> teaReplyList() {
        return exchangeMapper.teaReplyList();
    }

    @Override
    public void teaSendMessage(String leavename, String leaveid, String messagebody, String messagetype, String courseid, String leavedate) {
        exchangeMapper.teaSendMessage(leavename,leaveid,messagebody,messagetype,courseid,leavedate);
    }

    @Override
    public void sendMessage(String leavename,String leaveid,String messagebody,String messagetype,String courseid,String leavedate) {
        exchangeMapper.sendMessage(leavename,leaveid,messagebody,messagetype,courseid,leavedate);
    }

    @Override
    public void teaReplyMessage(String messageid, String replybody, String replydate, String teaid) {
        exchangeMapper.teaReplyMessage(messageid,replybody,replydate,teaid);
    }

    @Override
    public void replyMessage(String messageid, String replybody, String replydate,String stuid) {
        exchangeMapper.replyMessage(messageid,replybody,replydate,stuid);
    }

    @Override
    public List<Message> teaFindMessage(String coursename, String courseclass, String teaid) {
        return exchangeMapper.teaFindMessage(coursename,courseclass,teaid);
    }

    @Override
    public List<Message> findMessage(String coursename, String courseclass, String stuid) {
       return exchangeMapper.findMessage(coursename,courseclass,stuid);
    }

    @Override
    public List<Message> teaMyMessage(String teaid) {
        return exchangeMapper.teaMyMessage(teaid);
    }

    @Override
    public List<Message> myMessage(String stuid) {
        return exchangeMapper.myMessage(stuid);
    }
}
