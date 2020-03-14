package com.ljq.bishe.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljq.bishe.pojo.Homework;
import com.ljq.bishe.pojo.Score;
import com.ljq.bishe.service.MyDataService;
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
    @Autowired
    MyDataService myDataService;
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
        List workNameList = handInService.getWorkName(stuid);
        model.addAttribute("glc",getLearningCourse);
        model.addAttribute("hl",homeworkList);
        model.addAttribute("gwn",workNameList);
        return "student/handIn";
    }
    @PostMapping("/handInFile")
    @ResponseBody
    public String handInFile(@RequestParam("course") String course,
                             @RequestParam("courseClass") String courseClass,
                             @RequestParam("handInDate") String handInDate,
                             @RequestParam("workName") String workName,
                             @RequestParam("handInFile") MultipartFile handInFile){
        String handInPath = handInService.getHandInPath(stuId,course,courseClass,workName) ;//上交作业路径
        String uploadWorkname = handInFile.getOriginalFilename();
        int lastIndex = handInPath.lastIndexOf("\\");
        String handInPath2 = handInPath.substring(0,lastIndex) + File.separator + stuId +  File.separator + handInFile.getOriginalFilename();
        String studentFolderPath = handInPath.substring(0,lastIndex) + File.separator + stuId;
        File studentFolder = new File(studentFolderPath);
        if (!studentFolder.exists()){
            studentFolder.mkdirs();
        }
        File file = new File(handInPath2);
        try {
            if (!file.exists()){
                handInFile.transferTo(file);
            }else{
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        handInService.handIn(stuId,course,courseClass,workName,uploadWorkname);
        String msg = "上交成功！";
        return msg;
    }
    @GetMapping("/exchange")
    public String exchange(){
        return "student/exchange";
    }

    /*
    * 学生作业数据*/
    @GetMapping("/myData")
    public String myData(Model model){
        List workNameList = handInService.getWorkName(stuId);
        List getLearningCourse = handInService.getLearningCourse(stuId);//在学科目
        List getAllMyData = myDataService.allMyDdata(stuId);
        model.addAttribute("glc",getLearningCourse);
        model.addAttribute("gwn",workNameList);
        model.addAttribute("gamd",getAllMyData);
        return "student/myData";
    }

    /*
    * 查找学生作业*/
    @PostMapping("/findMyScore")
    @ResponseBody
    public List findMyScore(@RequestParam("course") String course,
                              @RequestParam("courseClass") String courseClass,
                              @RequestParam("homeworkState") String homeworkState,Model model){
        List<Score> findMyDataList = myDataService.findMyData(stuId,course,courseClass,homeworkState);
        return findMyDataList;
    }

    @PostMapping("/saveQuestion")
    @ResponseBody
    public String saveQuestion(@RequestParam("workid") String workid,
                               @RequestParam("stuid") String stuid,
                               @RequestParam("question") String question){
        myDataService.saveQuestion(stuid,workid,question);
        String msg = "success";
        return msg;
    }

    @GetMapping("/workSituation")

    public String workSituation(){
        return "student/workSituation";
    }
}

