package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.IStudentService;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired //injection
    IStudentService studentService;

    @GetMapping("")
    public ModelAndView listStudents(
            @RequestParam(name = "q", defaultValue = "") String q,
            @RequestParam(name = "sort", defaultValue = "id") String sort,
            @RequestParam(name = "dir", defaultValue = "asc") String dir,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "5") int size) {
        ModelAndView modelAndView = new ModelAndView("students");
        List<Student> students = studentService.getStudents(q, sort, dir, page, size);
        modelAndView.addObject("students", students);
        modelAndView.addObject("q", q);
        modelAndView.addObject("sort", sort);
        modelAndView.addObject("dir", dir);
        modelAndView.addObject("page", page);
        modelAndView.addObject("size", size);
        return modelAndView;
    }


    @GetMapping("/create")
    public String getCreateStudent() {
        return "students-create";
    }

    @PostMapping("/create")
    public String createStudent(Student student, RedirectAttributes redirectAttributes) {
        studentService.addStudent(student);
        redirectAttributes.addFlashAttribute("message", "Student created successfully");
        return "redirect:/students"; //Chuyển hướng về trang danh sách
    }

    @GetMapping("/{id}")
    public String detailStudent(@PathVariable("id") String id, Model model) {
        Student student = studentService.findStudentById(id);
        model.addAttribute("student", student);
        return "student-detail";
    }

    @GetMapping("/{id}/edit")
    public String showEditStudent(@PathVariable("id") String id, Model model) {
        Student student = studentService.findStudentById(id);
        model.addAttribute("studentEdit", student);
        return "students-edit";
    }

    @PostMapping("/{id}/edit")
    public String editStudent(@ModelAttribute("studentEdit") Student studentEdit, RedirectAttributes redirectAttributes) {
        studentService.updateStudent(studentEdit);
        redirectAttributes.addFlashAttribute("message", "Student updated successfully");
        return "redirect:/students";
    }

    @PostMapping("/{id}/delete")
    public String deleteStudent(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
        studentService.deleteStudent(id);
        redirectAttributes.addFlashAttribute("message", "Student deleted successfully");
        return "redirect:/students";
    }
}
