package com.ljq.bishe.mapper;
import com.ljq.bishe.pojo.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;
@Mapper
public interface StudataMapper {
    public List<Student> stulist(int teaid);
    public ArrayList classlist(int teaid);
    public ArrayList<Student> exportdata(String courseclass);
    public void importdata(Student student);
}
