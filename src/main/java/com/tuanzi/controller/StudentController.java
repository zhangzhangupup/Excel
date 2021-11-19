package com.tuanzi.controller;

import com.tuanzi.entity.Student;
import com.tuanzi.service.StudentService;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;
    @RequestMapping(value = "/UserExcelDownloads",method = RequestMethod.GET)
    public void UserExcelDownloads(HttpServletResponse response)throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("信息表");
        List<Student> UserExcelDownloads = studentService.UserExcelDownloads();
        String fileName = "students" + ".xls";
        int rowNum = 1;
        String [] headers = {"学号","姓名","身份类型","登录密码"};
        HSSFRow row = sheet.createRow(0);
        for (int i = 0;i<headers.length;i++){
            HSSFCell cell = row.createCell((short) i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        for (Student student : UserExcelDownloads){
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell((short) 0).setCellValue(new HSSFRichTextString(student.getAge()));
            row1.createCell((short) 1).setCellValue(new HSSFRichTextString(student.getName()));
            row1.createCell((short) 2).setCellValue(new HSSFRichTextString(student.getPhone()));
            row1.createCell((short) 3).setCellValue(new HSSFRichTextString(student.getAddress()));
            row1.createCell((short) 4).setCellValue(new HSSFRichTextString(student.getEmail()));
            rowNum++;

        }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }
}
