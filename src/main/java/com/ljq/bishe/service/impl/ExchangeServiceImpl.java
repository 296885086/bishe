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
    public List<Message> messageList() {
        return exchangeMapper.messageList();
    }

    @Override
    public List<Reply> replyList() {
        return exchangeMapper.replyList();
    }

    @Override
    public void sendMessage(String leavename,String leaveid,String messagebody,String messagetype,String courseid,String leavedate) {
        exchangeMapper.sendMessage(leavename,leaveid,messagebody,messagetype,courseid,leavedate);
    }

    @Override
    public void replyMessage(String messageid, String replybody, String replydate,String stuid) {
        exchangeMapper.replyMessage(messageid,replybody,replydate,stuid);
    }

    @Override
    public List<Message> findMessage(String coursename, String courseclass, String stuid) {
       return exchangeMapper.findMessage(coursename,courseclass,stuid);
    }
}
