package com.ljq.bishe.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljq.bishe.pojo.Course;
import com.ljq.bishe.pojo.Homework;
import com.ljq.bishe.pojo.ScoreList;
import com.ljq.bishe.pojo.Student;
import com.ljq.bishe.service.ReleaseService;
import com.ljq.bishe.service.StudataService;
import com.ljq.bishe.service.WorkCounstService;
import com.ljq.bishe.service.WorkScoreService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    String teacherId = null;
    /*
    * 作业列表*/
    @GetMapping("/fragment/{teaid}")
    public String listCategory(Model m, @PathVariable("teaid") String teaid,
                               @RequestParam(value = "start", defaultValue = "0") int start,
                               @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
        teacherId=teaid;
        Homework homework = new Homework();
        ArrayList course = wc.courseList(teaid);
        List<Course> courseClass = wc.courseClassList(teaid);
        PageHelper.startPage(start, size, "workid desc");
        List<Homework> worklist = rs.worklist(teaid);
        PageInfo<Homework> page = new PageInfo<>(worklist);
        m.addAttribute("homework", homework);
        m.addAttribute("page", page);
        m.addAttribute("course",course);//科目
        m.addAttribute("courseClass",courseClass);//班级
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

        //获取项目路径
        String projectpath = Class.class.getClass().getResource("/").getPath();
        String projectpath1 = projectpath.substring(1, projectpath.indexOf("/target"));
        String destFileName = projectpath1 + "/src/main/resources/static/homework/" + teacherId + File.separator + homework.getCourse() + File.separator +homework.getUploadclass()+ File.separator + fileName;
        String fileFolderPath = projectpath1 + "/src/main/resources/static/homework/" + teacherId + File.separator + homework.getCourse() + File.separator +homework.getUploadclass();
        String teaFolderPath = projectpath1 + "/src/main/resources/static/homework/" + teacherId;

        homework.setFilepath(destFileName);
        File teaFolder = new File(teaFolderPath);
        if (!teaFolder.exists()){
            teaFolder.mkdirs();
        }
        File fileFolder = new File(fileFolderPath);//文件夹
        if (!fileFolder.exists()){
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
        List gus = rs.getUploadStudent(course,uploadClass);
        for (int i = 0; i < gus.size();i++){
             rs.addWork(String.valueOf(gus.get(i)),course,destFileName,uploadClass);//给学生添加作业信息
        }
        /*rs.addWork(destFileName);*///给学生添加作业信息
        return new ModelAndView("redirect:/teacher/fragment/"+ teacherId);
    }
    /*
    * 删除作业*/
    @GetMapping("/workdelete")
    public String workdelete(Homework homework) {
        File file = new File(homework.getFilepath());
        File folder = new File(file.getParent());
        File mainFolder = new File(folder.getParent());
        file.delete();
        File[] fileList = folder.listFiles();//文件夹为空删除
        if (fileList.length == 0){
            folder.delete();//删除文件夹
        }
        File[] fileList1 = mainFolder.listFiles();
        if (fileList1.length == 0){
            mainFolder.delete();//删除文件
        }
        List gus = rs.getUploadStudent(homework.getCourse(),homework.getUploadclass());
        for (int i = 0; i < gus.size();i++){
            //删除学生作业信息
            rs.deleteUploadStudent(String.valueOf(gus.get(i)),homework.getCourse(),homework.getUploadclass());
        }
        rs.workdelete(String.valueOf(homework.getWorkid()));//删除作业信息
        return "redirect:/teacher/fragment/" + teacherId;
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

    /*
    * 师生交流*/
    @GetMapping("/teachertalk")
    public String teachertalk() {
        return "teacher/teachertalk";
    }

    /*
    * 作业情况统计*/
    @GetMapping("/workcount/{teaid}")
    public String workcount(@PathVariable("teaid") String teaid, Model model) {
        ArrayList course = wc.courseList(teaid);
        List<Course> courseClass = wc.courseClassList(teaid);
        model.addAttribute("course",course);//科目
        model.addAttribute("courseClass",courseClass);//班级
        model.addAttribute("teaid",teaid);
        return "teacher/workcount";
    }
    /**
     *学生作业审核
     */
    @GetMapping("/workaudit")
    public String workaudit(){
        return "teacher/workaudit";
    }
    /**
     *学生作业评分
     */
    @GetMapping("/workscore")
    public String workscore(Model m){
        Homework homework = new Homework();
        m.addAttribute("homework", homework);
        ArrayList course = wc.courseList(teacherId);
        List<Course> courseClass = wc.courseClassList(teacherId);
        m.addAttribute("course",course);//科目
        m.addAttribute("courseClass",courseClass);//班级
        m.addAttribute("homework", homework);
        return "teacher/workscore";
    }

    /**
     * 查找学生作业*/
    @PostMapping("/findWorkScore")
    @ResponseBody
    public String findWorkScore(@RequestParam("course") String courseName,
                                @RequestParam("courseClass") String courseClass,
                                @RequestParam("homeworkState") String state){
        String msg = "查找成功！";
        List workScoreList = ws.findStudentScore(courseName,courseClass,state,teacherId);
        return msg;
    }
    /*
    * 学生作业情况统计
    * */
    @GetMapping("/selectclass")
    @ResponseBody
    public List selectclass(@RequestParam String teaid,
                            @RequestParam String course,
                            @RequestParam String courseClass) throws Exception {
        String all = "all";
        ArrayList scoreArr = new ArrayList();
        List<ScoreList> scoreList = wc.scoreList(teaid,course,courseClass);
        ArrayList arrayList = new ArrayList();
        arrayList.add(scoreList);
        arrayList.add(course);
        if (courseClass.equals(all)){
            List<Course> courseClass1 = wc.courseList1(teaid,course);
            arrayList.add(courseClass1);
        }
        return arrayList;
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
            if (testFile.exists()){
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
                row.createCell(7).setCellValue(student.getCourseclass());
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
            student.setStuid(row.getCell(0).getStringCellValue());
            student.setStuname(row.getCell(1).getStringCellValue());
            student.setStusex(row.getCell(2).getStringCellValue());
            student.setStuclass(row.getCell(3).getStringCellValue());
            student.setStuphone(row.getCell(4).getStringCellValue());
            student.setCourseclass(row.getCell(6).getStringCellValue());
            student.setStupassword(row.getCell(7).getStringCellValue());
            studentList.add(student);
            ss.importdata(student);
        }
        readBook.close();
        ins.close();
        response.getWriter().write("导入成功！");
    }

}

