package com.ljq.bishe.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljq.bishe.pojo.Homework;
import com.ljq.bishe.service.impl.HandInServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/student")
@Controller
public class StudentController {
    @Autowired
    HandInServiceImpl handInService;
    String stuId;
    @GetMapping("/handIn/{stuid}")
    public String handIn(@PathVariable("stuid") String stuid,Model model,
                         @RequestParam(value = "start", defaultValue = "0") int start,
                         @RequestParam(value = "size", defaultValue = "5") int size){
        stuId = stuid;
        List getLearningCourse = handInService.getLearningCourse(stuid);
/*      PageHelper.startPage(start, size, "stuid desc");
        List<Homework> worklist = rs.worklist(teaid);*/
        /*PageInfo<Homework> page = new PageInfo<>(worklist);*/
        List homeworkList = handInService.workInfo(stuid);
        model.addAttribute("glc",getLearningCourse);
        model.addAttribute("hl",homeworkList);
        return "student/handIn";
    }
    @PostMapping("/handInFile")
    public String handInFile(@RequestParam("course") String course,
                             @RequestParam("courseClass") String courseClass,
                             @RequestParam("handInDate") String handInDate,
                             @RequestParam("handInFile") MultipartFile handInFile){
        return "123";
    }
    @GetMapping("/exchange")
    public String exchange(){
        return "student/exchange";
    }
    @GetMapping("/myData")
    public String myData(){
        return "student/myData";
    }
    @GetMapping("/workSituation")
    public String workSituation(){
        return "student/workSituation";
    }
}

