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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
        List getLearningCourse = handInService.getLearningCourse(stuid);//在学科目
/*      PageHelper.startPage(start, size, "stuid desc");
        List<Homework> worklist = rs.worklist(teaid);*/
        /*PageInfo<Homework> page = new PageInfo<>(worklist);*/
        List homeworkList = handInService.workInfo(stuid);//作业列表
        model.addAttribute("glc",getLearningCourse);
        model.addAttribute("hl",homeworkList);
        return "student/handIn";
    }
    @PostMapping("/handInFile")
    @ResponseBody
    public String handInFile(@RequestParam("course") String course,
                             @RequestParam("courseClass") String courseClass,
                             @RequestParam("handInDate") String handInDate,
                             @RequestParam("handInFile") MultipartFile handInFile){
        String handInPath = handInService.getHandInPath(stuId,course,courseClass) ;//上交作业路径
        int lastIndex = handInPath.lastIndexOf("\\");
        String handInPath2 = handInPath.substring(0,lastIndex) + File.separator +  handInFile.getOriginalFilename();
        File file = new File(handInPath2);
        try {
            handInFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        handInService.handIn(stuId,course,courseClass);
        String msg = "上交成功！";
        return msg;
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

