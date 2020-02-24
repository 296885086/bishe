package com.ljq.bishe.service;

import com.ljq.bishe.pojo.Student;
import com.ljq.bishe.pojo.Teacher;

public interface LoginService {
    public Teacher gettea(String teaname);
    public Student getstu(String stuname);
}
