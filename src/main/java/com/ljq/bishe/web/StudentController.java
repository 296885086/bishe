package com.ljq.bishe.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljq.bishe.pojo.*;
import com.ljq.bishe.service.AuditService;
import com.ljq.bishe.service.ExchangeService;
import com.ljq.bishe.service.MyDataService;
import com.ljq.bishe.service.impl.HandInServiceImpl;
import com.sun.org.apache.xerces.internal.xs.LSInputList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RequestMapping("/student")
@Controller
public class StudentController {
    @Autowired
    HandInServiceImpl handInService;
    @Autowired
    MyDataService myDataService;
    @Autowired
    ExchangeService exchangeService;
    @Autowired
    AuditService auditService;
    String stuId;
    String stuName;
    @GetMapping("/handIn/{stuid}")
    public String handIn(@PathVariable("stuid") String stuid,Model model){
        stuId = stuid;
        stuName = handInService.getStuname(stuid);
        List getLearningCourse = handInService.getLearningCourse(stuid);//在学科目
        List getCourseClass = handInService.getCourseClass(stuid);
        List homeworkList = handInService.workInfo(stuid);//作业列表
        List workNameList = handInService.getWorkName(stuid);
        model.addAttribute("glc",getLearningCourse);
        model.addAttribute("gcc",getCourseClass);
        model.addAttribute("hl",homeworkList);
        model.addAttribute("gwn",workNameList);
        return "student/handIn";
    }

    /*
* 查找需要完成的作业*/
    @PostMapping("/findHandIn")
    @ResponseBody
    public List findHandIn(@RequestParam("course") String course,
                           @RequestParam("courseClass") String courseClass,
                           @RequestParam("homeworkState") String homeworkState){
        List findHomeworkList = handInService.findHomeworkList(stuId,course,courseClass,homeworkState);
        return findHomeworkList;
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

    //师生交流
    @GetMapping("/exchange")
    public String exchange(Model model){
        List<Message> messageList = exchangeService.messageList(stuId);
        List<Reply> replyList = exchangeService.replyList();
        List getLearningCourse = handInService.getLearningCourse(stuId);//在学科目
        List getCourseClass = handInService.getCourseClass(stuId);
        HashMap stuInfo  = new HashMap();
        stuInfo.put("stuid",stuId);
        stuInfo.put("stuname",stuName);
        model.addAttribute("gcc",getCourseClass);
        model.addAttribute("glc",getLearningCourse);
        model.addAttribute("messageList",messageList);
        model.addAttribute("replyList",replyList);
        model.addAttribute("stuInfo",stuInfo);
        return "student/exchange";
    }

    //发表言论
    @PostMapping("/sendMessage")
    @ResponseBody
    public String sendMessage(@RequestParam("leavename") String leavename,
                              @RequestParam("leaveid") String leaveid,
                              @RequestParam("messagebody") String messagebody,
                              @RequestParam("course") String course,
                              @RequestParam("courseClass") String courseClass){
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
        String leavedate = dateFormat.format( now );
        exchangeService.sendMessage(leavename,leaveid,messagebody,"stu",leavedate,course,courseClass,stuId);
        return "success";
    }

    //评论
    @PostMapping("/replyMessage")
    @ResponseBody
    public String replyMessage(@RequestParam("messageid") String messageid,
                               @RequestParam("replybody") String replybody){
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
        String replydate = dateFormat.format( now );
        exchangeService.replyMessage(messageid,replybody,replydate,stuId);
        return "success";
    }

    //查找留言
    @PostMapping("/findMessage")
    @ResponseBody
    public List findMessage(@RequestParam("course") String course,
                              @RequestParam("courseClass") String courseClass,
                              Model model){
        List<Message> findMessageList = exchangeService.findMessage(course,courseClass,stuId);
        return findMessageList;
    }

    /*
    * 我的留言*/
    @PostMapping("/myMessage")
    @ResponseBody
    public  List myMessage(){
        List<Message> myMessageList = exchangeService.myMessage(stuId);
        return myMessageList;
    }

    //删除留言
    @PostMapping("/deleteMsg")
    @ResponseBody
    public String deleteMsg(@RequestParam("msgid") String msgid){
        exchangeService.deleteMsg(msgid);
        exchangeService.deleteReply(msgid);
        return "success";
    }

    /*
    * 学生作业数据*/
    @GetMapping("/myData")
    public String myData(Model model){
        List workNameList = handInService.getWorkName(stuId);
        List getLearningCourse = handInService.getLearningCourse(stuId);//在学科目
        List getAllMyData = myDataService.allMyDdata(stuId);
        List getCourseClass = handInService.getCourseClass(stuId);
        model.addAttribute("glc",getLearningCourse);
        model.addAttribute("gcc",getCourseClass);
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
                              @RequestParam("homeworkState") String homeworkState){
        List<Score> findMyDataList = myDataService.findMyData(stuId,course,courseClass,homeworkState);
        return findMyDataList;
    }


    //反馈
    @PostMapping("/saveQuestion")
    @ResponseBody
    public String saveQuestion(@RequestParam("workid") String workid,
                               @RequestParam("stuid") String stuid,
                               @RequestParam("question") String question){
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
        String commitDate = dateFormat.format( now );
        myDataService.saveQuestion(stuid,workid,question,commitDate);
        String msg = "success";
        return msg;
    }

    //删除反馈
    @PostMapping("/deleteAudit")
    @ResponseBody
    public String deleteAudit(@RequestParam("auditid") String auditid){
        auditService.deleteAudit(auditid);
        return "success";
    }
    //作业
    @GetMapping("/workSituation")
    public String workSituation(Model model){
        List<Audit> auditList = auditService.allAuditList(stuId);
        model.addAttribute("auditList",auditList);
        return "student/audit";
    }


}

