package com.ljq.bishe.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljq.bishe.pojo.*;
import com.ljq.bishe.service.*;
import net.sf.json.JSONArray;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@RequestMapping("/teacher")
@Controller
public class TeacherController {
    @Autowired
    ReleaseService rs;
    @Autowired
    WorkCounstService wc;
    @Autowired
    StudataService ss;
    @Autowired
    WorkScoreService ws;
    @Autowired
    CourseManagerService cms;
    @Autowired
    ExchangeService exchangeService;
    @Autowired
    AuditService auditService;

    String teacherId = null;
    String teacherName = null;
    /*
    * 作业列表*/
    @GetMapping("/fragment/{teaid}")
    public String listCategory(Model m, @PathVariable("teaid") String teaid,
                               @RequestParam(value = "start", defaultValue = "0") int start,
                               @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
        teacherId = teaid;
        teacherName = rs.getTeaName(teaid);
        Homework homework = new Homework();
        ArrayList course = wc.courseList(teaid);
        List<Course> courseClass = wc.courseClassList(teaid);
        PageHelper.startPage(start, size, "workid desc");
        List<Homework> worklist = rs.worklist(teaid);
        PageInfo<Homework> page = new PageInfo<>(worklist);
        m.addAttribute("homework", homework);
        m.addAttribute("page", page);
        m.addAttribute("course", course);//科目
        m.addAttribute("courseClass", courseClass);//班级
        return "teacher/uploadHomework";
    }

    /*
    * 上传作业*/
    @PostMapping("/upload")
    public ModelAndView upload(@ModelAttribute Homework homework,
                               @RequestParam("file") MultipartFile file, Model model) {
        String uploaddate = homework.getUploaddate();
        String enddate = homework.getEnddate();
        String fileName = file.getOriginalFilename();
        String course = homework.getCourse();
        String uploadClass = homework.getUploadclass();
        int index = fileName.indexOf(".");
        homework.setWorkname(fileName.substring(0, index));
        homework.setTeaid(teacherId);
        //获取项目路径
        String projectpath = Class.class.getClass().getResource("/").getPath();
        String projectpath1 = projectpath.substring(1, projectpath.indexOf("/target"));
        String destFileName = projectpath1 + "/src/main/resources/static/homework/" + teacherId + File.separator + homework.getCourse() + File.separator + homework.getUploadclass() + File.separator + fileName;
        String fileFolderPath = projectpath1 + "/src/main/resources/static/homework/" + teacherId + File.separator + homework.getCourse() + File.separator + homework.getUploadclass();
        String teaFolderPath = projectpath1 + "/src/main/resources/static/homework/" + teacherId;

        homework.setFilepath(destFileName);
        File teaFolder = new File(teaFolderPath);
        if (!teaFolder.exists()) {
            teaFolder.mkdirs();
        }
        File fileFolder = new File(fileFolderPath);//文件夹
        if (!fileFolder.exists()) {
            fileFolder.mkdirs();
        }
        File destFile = new File(destFileName);
        destFile.getParentFile().mkdirs();//创建文件
        rs.workinsert(homework);
        try {
            file.transferTo(destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List gus = rs.getUploadStudent(course, uploadClass,teacherId);
        for (int i = 0; i < gus.size(); i++) {
            rs.addWork(String.valueOf(gus.get(i)), course, destFileName, uploadClass,teacherId);//给学生添加作业信息
        }
        /*rs.addWork(destFileName);*///给学生添加作业信息
        return new ModelAndView("redirect:/teacher/fragment/" + teacherId);
    }

    @PostMapping("/findWork")
    @ResponseBody
    public List findWork(@RequestParam("course") String course,
                           @RequestParam("courseClass") String courseClass){
        List<Homework> findHomeworkList = rs.findWork(course,courseClass,teacherId);
        return findHomeworkList;
    }
    /*
    * 删除作业*/
    @PostMapping("/workdelete")
    @ResponseBody
    public String workdelete(Homework homework) {
        File file = new File(homework.getFilepath());
        File folder = new File(file.getParent());
        File mainFolder = new File(folder.getParent());
        file.delete();
        File[] fileList = folder.listFiles();//文件夹为空删除
        if (fileList.length == 0) {
            folder.delete();//删除文件夹
        }
        File[] fileList1 = mainFolder.listFiles();
        if (fileList1.length == 0) {
            mainFolder.delete();//删除文件
        }
        List gus = rs.getUploadStudent(homework.getCourse(), homework.getUploadclass(),teacherId);
        for (int i = 0; i < gus.size(); i++) {
            //删除学生作业信息
            rs.deleteUploadStudent(String.valueOf(gus.get(i)), homework.getCourse(), homework.getUploadclass());
        }
        rs.workdelete(String.valueOf(homework.getWorkid()));//删除作业信息
        return "success";
    }

    /*
    * 学生数据管理*/
    @GetMapping("/studata/{teaid}")
    public String studata(@ModelAttribute Student student,
                          @PathVariable("teaid") int teaid,
                          Model m, @RequestParam(value = "start", defaultValue = "0") int start,
                          @RequestParam(value = "size", defaultValue = "9") int size) {
        PageHelper.startPage(start, size, "stuid desc");
        List<Student> worklist = ss.stulist(teaid);
        PageInfo<Student> page = new PageInfo<>(worklist);
        m.addAttribute("page", page);
        ArrayList al = ss.classlist(teaid);
        m.addAttribute("classlist", al);
        return "teacher/studata";
    }

    //修改学生信息
    @PostMapping("/editStuData")
    @ResponseBody
    public  String editStuData(@RequestParam("stuid") String initStuid,
                               @RequestParam("stuid") String stuid,
                               @RequestParam("stuname") String stuname,
                               @RequestParam("stusex") String stusex,
                               @RequestParam("stuclass") String stuclass,
                               @RequestParam("stuphone") String stuphone){
        ss.editStuData(initStuid,stuid,stuname,stusex,stuclass,stuphone);
        return "success";
    }

    //删除学生信息
    @PostMapping("/deleteStuData")
    @ResponseBody
    public String deleteStuData(@RequestParam("stuid") String stuid){
        ss.deleteStuData(stuid);
        return "success";
    }

    /*
* 删除学生信息 */
    @PostMapping("/deleteSomeStuData")
    @ResponseBody
    public String deleteSomeStuData(@RequestParam String someStuData){
        String[] someStuDataeArr = someStuData.split(",");
        for (int a = 0;a<someStuDataeArr.length;a++){
            ss.deleteStuData(someStuDataeArr[a]);
        }
        return "success";
    }
    /*
    *查找学生数据
     */
    @PostMapping("/findStuInfo")
    @ResponseBody
    public List findStuInfo(@RequestParam String conditionInfo,
                            @RequestParam String condition){
        List<Student> studentList = ss.findStuInfo(conditionInfo,condition);
        return studentList;
    }

    //师生交流
    @GetMapping("/exchange")
    public String exchange(Model model){
        List<Message> teaMessageList = exchangeService.teaMessageList(teacherId);
        List<Reply> teaReplyList = exchangeService.teaReplyList();
        ArrayList course = wc.courseList(teacherId);
        List<Course> courseClass = wc.courseClassList(teacherId);
        HashMap teaInfo  = new HashMap();
        teaInfo.put("teaid",teacherId);
        teaInfo.put("teaname",teacherName);
        model.addAttribute("course", course);//科目
        model.addAttribute("courseClass", courseClass);//班级
        model.addAttribute("teaMessageList",teaMessageList);
        model.addAttribute("teaReplyList",teaReplyList);
        model.addAttribute("teaInfo",teaInfo);
        return "teacher/exchange";
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
        exchangeService.teaSendMessage(leavename,leaveid,messagebody,"tea",leavedate,course,courseClass);
        return "success";
    }

    //回复留言
    @PostMapping("/replyMessage")
    @ResponseBody
    public String replyMessage(@RequestParam("messageid") String messageid,
                               @RequestParam("replybody") String replybody){
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
        String replydate = dateFormat.format( now );
        exchangeService.teaReplyMessage(messageid,replybody,replydate,teacherId);
        return "success";
    }

    //查找留言
    @PostMapping("/findMessage")
    @ResponseBody
    public List findMessage(@RequestParam("course") String course,
                            @RequestParam("courseClass") String courseClass){
        List<Message> findMessageList = exchangeService.teaFindMessage(course,courseClass,teacherId);
        return findMessageList;
    }

    /*
    * 我的留言*/
    @PostMapping("/myMessage")
    @ResponseBody
    public  List myMessage(){
        List<Message> myMessageList = exchangeService.teaMyMessage(teacherId);
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
    * 作业情况统计*/
    @GetMapping("/workcount/{teaid}")
    public String workcount(@PathVariable("teaid") String teaid, Model model) {
        ArrayList course = wc.courseList(teaid);
        List<Course> courseClass = wc.courseClassList(teaid);
        List<Score> allWorkData = wc.allWorkData(teaid);
        model.addAttribute("course", course);//科目
        model.addAttribute("courseClass", courseClass);//班级
        model.addAttribute("teaid", teaid);
        model.addAttribute("allWorkData", allWorkData);
        return "teacher/workcount";
    }


    /* 学生作业审核
     **/
    @GetMapping("/workaudit")
    public String workaudit(Model model) {
        List<Audit> auditList = auditService.teaAllAuditList(teacherId);
        model.addAttribute("auditList",auditList);
        return "teacher/workaudit";
    }

    //驳回
    @PostMapping("/giveBack")
    @ResponseBody
    public String giveBack(@RequestParam String auditid){
        auditService.giveBack(auditid);
        return "success";
    }
    //进行审核
    @PostMapping("/sureAudit")
    @ResponseBody
    public String sureAudit(@RequestParam String auditid,
                            @RequestParam String editScorse,
                            @RequestParam String replybody,
                            @RequestParam String stuid,
                            @RequestParam String workid){
        auditService.sureAudit(auditid,editScorse,replybody,stuid,workid);
        return "success";
    }
    /*
     * 学生作业评分
     */
    @GetMapping("/workscore")
    public String workscore(Model m) {
        ArrayList course = wc.courseList(teacherId);
        List<Course> courseClass = wc.courseClassList(teacherId);
        List allScore = ws.findAllScore(teacherId);
        List worknameList = ws.findWorkname(teacherId);
        m.addAttribute("course", course);//科目
        m.addAttribute("courseClass", courseClass);//班级
        m.addAttribute("allScore", allScore);
        m.addAttribute("worknameList", worknameList);
        return "teacher/workscore";
    }

    /*
     * 查找学生作业
     */
    @PostMapping("/findWorkScore")
    @ResponseBody
    public List findWorkScore(@RequestParam("course") String courseName,
                              @RequestParam("courseClass") String courseClass,
                              @RequestParam("homeworkState") String state,
                              @RequestParam("workName") String workName) {
        List workScoreList = ws.findStudentScore(courseName, courseClass, state, teacherId,workName);
        return workScoreList;
    }

    /*
     *修改学生作业分数
     **/
    @PostMapping("/modifyScore")
    @ResponseBody
    public String modifyScore(@RequestParam("modifyScore") String modifyScore,
                              @RequestParam("workid") String workid,
                              @RequestParam("stuid") String stuid) {
        ws.modifyScore(modifyScore, workid, stuid);
        return modifyScore;
    }

    /*
    * 学生作业情况统计
    * */
    @PostMapping("/selectclass")
    @ResponseBody
    public List selectclass(@RequestParam String teaid,
                            @RequestParam String course,
                            @RequestParam String courseClass){
        List<ScoreList> scoreList = wc.scoreList(teaid, course, courseClass);
        return scoreList;
    }

    /*
    * 导出学生数据*/
    @PostMapping("/exportdata")
    public void export(HttpServletRequest request,
                       HttpServletResponse response,
                       @RequestParam String cl,
                       Model model) throws Exception {
        JSONArray json = JSONArray.fromObject(cl);
        /*export(json);*/
        List<Student> esa = new ArrayList<Student>();
        ArrayList filepath = new ArrayList();
        String destFileName = null;
        //获取项目路径
        String projectpath = Class.class.getClass().getResource("/").getPath();
        String projectpath1 = projectpath.substring(1, projectpath.indexOf("/target"));
        for (int i = 0; i < json.size(); i++) {
            String y = String.valueOf(json.get(i));
            String filename = y + ".xlsx";
            destFileName = projectpath1 + "/src/main/resources/static/upload/" + filename;
            File testFile = new File(destFileName);
            if (testFile.exists()) {
                testFile.delete();
            }
            filepath.add(filename);
            List<Student> es = ss.exportdata(y);
            File file = new File(destFileName);
            Workbook book = new XSSFWorkbook();
            Sheet sheet = book.createSheet(y);//创建表格
            int totalRow = sheet.getPhysicalNumberOfRows();//表头
            Row total = sheet.createRow(totalRow);//表头
            total.createCell(0).setCellValue("序号");
            total.createCell(1).setCellValue("姓名");
            total.createCell(2).setCellValue("性别");
            total.createCell(3).setCellValue("班别");
            total.createCell(4).setCellValue("联系电话");
            total.createCell(7).setCellValue("密码");
            total.createCell(6).setCellValue("教学班");
            for (int j = 0; j < es.size(); j++) {
                Student student = es.get(j);
                Row row = sheet.createRow(j + 1);
                FileOutputStream fos = new FileOutputStream(file.getAbsolutePath());//开始写入
                row.createCell(0).setCellValue(student.getStuid());
                row.createCell(1).setCellValue(student.getStuname());
                row.createCell(2).setCellValue(student.getStusex());
                row.createCell(3).setCellValue(student.getStuclass());
                row.createCell(4).setCellValue(student.getStuphone());
                row.createCell(5).setCellValue(student.getStupassword());
                book.write(fos);
                fos.close();
            }
            book.close();
        }
        response.getWriter().write(filepath.toString());
    }

    /*
    * 导入学生数据*/
    @PostMapping("/importdata")
    public void importdata(@RequestParam("importfile") MultipartFile importfile,
                           HttpServletResponse response) throws Exception {
        //从excel读数据
        BufferedInputStream ins = new BufferedInputStream(importfile.getInputStream());
        Workbook readBook = WorkbookFactory.create(ins);
        Sheet sheet = readBook.getSheetAt(0);
        int rowNum = sheet.getLastRowNum();
        int columnNum = sheet.getPhysicalNumberOfRows();
        Student student = new Student();
        List<Student> studentList = new ArrayList<Student>();
        Row row = null;
        for (int i = 0; i < rowNum; i++) {
            row = sheet.getRow(i + 1);
            row.getCell(0).setCellType(CellType.STRING);
            row.getCell(4).setCellType(CellType.STRING);
            row.getCell(5).setCellType(CellType.STRING);
            student.setStuid(row.getCell(0).getStringCellValue());
            student.setStuname(row.getCell(1).getStringCellValue());
            student.setStusex(row.getCell(2).getStringCellValue());
            student.setStuclass(row.getCell(3).getStringCellValue());
            student.setStuphone(row.getCell(4).getStringCellValue());
            student.setStupassword(row.getCell(5).getStringCellValue());
            student.setTeaid(teacherId);
            studentList.add(student);
            ss.importdata(student);
        }
        readBook.close();
        ins.close();
        response.getWriter().write("导入成功！");
    }

    @GetMapping("/courseManager")
    public String courseManager() {
        return "teacher/courseManager";
    }

    /*
   * 课程信息管理页面*/
    @GetMapping("/stuCourseManager")
    public String stuCourseManager(Model model) {
        List<SelectCourse> selectCourseList = cms.stuCourseManger(teacherId);
        ArrayList course = wc.courseList(teacherId);
        List<Course> courseClass = wc.courseClassList(teacherId);
        model.addAttribute("selectCourseList", selectCourseList);
        model.addAttribute("course", course);//科目
        model.addAttribute("courseClass", courseClass);//班级
        return "teacher/stuCourseManager";
    }

    /*
    * 课程信息管理页面*/
    @GetMapping("/teaCourseManager")
    public String teaCourseManager(Model model) {
        List<Course> courseList = cms.teaCourseManger(teacherId);
        List<Course> totalStudent = cms.totalStudent(teacherId);
        for (int i = 0; i < courseList.size(); i++) {
            String courseName = courseList.get(i).getCoursename();
            String courseClass = courseList.get(i).getCourseclass();
            for (int j = 0; j < totalStudent.size(); j++) {
                String courseName1 = totalStudent.get(j).getCoursename();
                String courseClass1 = totalStudent.get(j).getCourseclass();
                if (courseName.equals(courseName1) && courseClass.equals(courseClass1))
                    courseList.get(i).setTotalstudent(totalStudent.get(j).getTotalstudent());
            }
        }

        model.addAttribute("courseList", courseList);
        return "teacher/teaCourseManager";
    }

    //导出老师教授课程信息
    @PostMapping("/exportTeacourse")
    @ResponseBody
    public String exportTeacourse() throws Exception{
        //获取项目路径
        String projectpath = Class.class.getClass().getResource("/").getPath();
        String projectpath1 = projectpath.substring(1, projectpath.indexOf("/target"));
        List<Course> courseList = cms.teaCourseManger(teacherId);
        String filename = "老师教授课程信息" + ".xlsx";
        String destFileName = projectpath1 + "/src/main/resources/static/upload/" + filename;

        //判断文件是否存在
        File testFile = new File(destFileName);
        if (testFile.exists()) {
            testFile.delete();
        }
        File file = new File(destFileName);
        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("老师教授课程信息");//创建表格
        int totalRow = sheet.getPhysicalNumberOfRows();//表头
        Row total = sheet.createRow(totalRow);//表头
        total.createCell(0).setCellValue("课程名称");
        total.createCell(1).setCellValue("教学班");
        for (int j = 0; j < courseList.size(); j++) {
            Course course = courseList.get(j);
            Row row = sheet.createRow(j + 1);
            FileOutputStream fos = new FileOutputStream(file.getAbsolutePath());//开始写入
            row.createCell(0).setCellValue(course.getCoursename());
            row.createCell(1).setCellValue(course.getCourseclass());
            book.write(fos);
            fos.close();
        }
        return filename;
    }

    //导出学生课程信息
    @PostMapping("/exportStucourse")
    @ResponseBody
    public String exportStucourse() throws Exception{
        //获取项目路径
        String projectpath = Class.class.getClass().getResource("/").getPath();
        String projectpath1 = projectpath.substring(1, projectpath.indexOf("/target"));
        List<SelectCourse> selectCourseList = cms.stuCourseManger(teacherId);
        String filename = "学生课程信息" + ".xlsx";
        String destFileName = projectpath1 + "/src/main/resources/static/upload/" + filename;

        //判断文件是否存在
        File testFile = new File(destFileName);
        if (testFile.exists()) {
            testFile.delete();
        }
        File file = new File(destFileName);
        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("学生课程信息");//创建表格
        int totalRow = sheet.getPhysicalNumberOfRows();//表头
        Row total = sheet.createRow(totalRow);//表头
        total.createCell(0).setCellValue("学号");
        total.createCell(3).setCellValue("教学班");
        total.createCell(2).setCellValue("课程名称");
        for (int j = 0; j < selectCourseList.size(); j++) {
            SelectCourse selectCourse = selectCourseList.get(j);
            Row row = sheet.createRow(j + 1);
            FileOutputStream fos = new FileOutputStream(file.getAbsolutePath());//开始写入
            row.createCell(0).setCellValue(selectCourse.getStuid());
            row.createCell(1).setCellValue(selectCourse.getCourseclass());
            row.createCell(2).setCellValue(selectCourse.getCoursename());
            book.write(fos);
            fos.close();
        }
        return filename;
    }

    /*
     *手动增加课程信息
     */
    @PostMapping("/writeAddCourse")
    @ResponseBody
    public String writeAddCourse(@RequestParam String courseClass,
                                 @RequestParam String courseName) {
        cms.writeAddCourse(courseClass, courseName, teacherId);
        String msg = "success";
        return msg;
    }

    /*
    * 增加学生课程信息*/
    @PostMapping("/writeAddStuCourse")
    @ResponseBody
    public String writeAddStuCourse(@RequestParam String courseClass,
                                    @RequestParam String courseName,
                                    @RequestParam String stuId) {
        cms.writeAddStuCourse(courseClass, courseName, teacherId, stuId);
        String msg = "success";
        return msg;
    }

    /*
    * 导入添加课程信息*/
    @PostMapping("/importAddCourse")
    @ResponseBody
    public String importAddCourse(@RequestParam("importFile") MultipartFile importFile) throws Exception {
        //从excel读数据
        BufferedInputStream ins = new BufferedInputStream(importFile.getInputStream());
        Workbook readBook = WorkbookFactory.create(ins);
        Sheet sheet = readBook.getSheetAt(0);
        int rowNum = sheet.getLastRowNum();//获取最后一行的行数
        Course course = new Course();
        Row row = null;
        for (int i = 0; i < rowNum; i++) {
            row = sheet.getRow(i + 1);//获取行
            /*row.getCell(0).setCellType(CellType.STRING);//转变行的格式*/
            course.setCoursename(row.getCell(0).getStringCellValue());//获取列的内容，并保存
            course.setCourseclass(row.getCell(1).getStringCellValue());
            course.setTeaid(teacherId);
            cms.writeAddCourse(course.getCourseclass(), course.getCoursename(), teacherId);
        }
        readBook.close();
        ins.close();
        String msg = "success";
        return msg;
    }

    /*
   * 导入添加学生课程信息*/
    @PostMapping("/importAddStuCourse")
    @ResponseBody
    public String importAddStuCourse(@RequestParam("importFile") MultipartFile importFile) throws Exception {
        //从excel读数据
        BufferedInputStream ins = new BufferedInputStream(importFile.getInputStream());
        Workbook readBook = WorkbookFactory.create(ins);
        Sheet sheet = readBook.getSheetAt(0);
        int rowNum = sheet.getLastRowNum();//获取最后一行的行数
        int columnNum = sheet.getPhysicalNumberOfRows();//获取列数
        SelectCourse selectCourse = new SelectCourse();
        Row row = null;
        for (int j = 0; j < rowNum; j++) {
            row = sheet.getRow(j + 1);//获取行
            row.getCell(0).setCellType(CellType.STRING);//转变行的格式
            selectCourse.setStuid(row.getCell(0).getStringCellValue());//获取列的内容，并保存
            selectCourse.setCourseclass(row.getCell(1).getStringCellValue());
            selectCourse.setCoursename(row.getCell(2).getStringCellValue());
            selectCourse.setTeaid(teacherId);
            cms.writeAddStuCourse(selectCourse.getCourseclass(), selectCourse.getCoursename(), selectCourse.getTeaid(), selectCourse.getStuid());
        }
        String msg = "success";
        return msg;
    }

    /*
    * 删除课程 */
    @PostMapping("/deletecourse")
    @ResponseBody
    public String deleteCourse(@RequestParam String courseid){
        cms.deleteCourse(courseid);
        return "success";
    }

    /*
    * 删除课程 */
    @PostMapping("/deleteSomeCourse")
    @ResponseBody
    public String deleteSomeCourse(@RequestParam String someCourse){
        String[] someCourseArr = someCourse.split(",");
        for (int a = 0;a<someCourseArr.length;a++){
            cms.deleteCourse(someCourseArr[a]);
        }
        return "success";
    }
    /*
    * 删除学生选课 */
    @PostMapping("/deletestucourse")
    @ResponseBody
    public String deleteStuCourse(@RequestParam String selectcourseid){
        cms.deleteStuCourse(selectcourseid);
        return "success";
    }

    /*
    * 删除学生选课 */
    @PostMapping("/deleteSomeStuCourse")
    @ResponseBody
    public String deleteSomeStuCourse(@RequestParam String someStuCourse){
        String[] someStuCourseArr = someStuCourse.split(",");
        for (int a = 0;a<someStuCourseArr.length;a++){
            cms.deleteStuCourse(someStuCourseArr[a]);
        }
        return "success";
    }

    /*
    * 修改课程信息*/
    @PostMapping("/editcourse")
    @ResponseBody
    public String editcourse(@RequestParam String coursename,
                             @RequestParam String courseclass,
                             @RequestParam String courseid){
        cms.editCourse(courseid,coursename,courseclass);
        return "success";
    }

    /*
    * 修改学生课程信息*/
    @PostMapping("/editstucourse")
    @ResponseBody
    public String editstucourse(@RequestParam String coursename,
                             @RequestParam String courseclass,
                             @RequestParam String selectcourseid){
        cms.editStuCourse(selectcourseid,coursename,courseclass);
        return "success";
    }

    /*
    * 查找学生课程信息*/
    @PostMapping("/findStuCourse")
    @ResponseBody
    public List findStuCourse(@RequestParam String course,
                              @RequestParam String courseClass){
        List<SelectCourse> selectCourseList = cms.findStuCourse(teacherId,course,courseClass);
        return selectCourseList;
    }
}

