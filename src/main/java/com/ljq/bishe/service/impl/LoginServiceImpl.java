package com.ljq.bishe.service.impl;

import com.ljq.bishe.mapper.LoginMapper;
import com.ljq.bishe.pojo.Student;
import com.ljq.bishe.pojo.Teacher;
import com.ljq.bishe.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{
    @Autowired
    LoginMapper loginMapper;
    @Override
    public Teacher gettea(String teaname) {
        return loginMapper.gettea(teaname);
    }
    public Student getstu(String stuname) {
        return loginMapper.getstu(stuname);
    }
}
