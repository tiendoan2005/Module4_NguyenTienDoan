package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.IStudentService;
import com.example.demo.service.StorageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired //injection
    IStudentService studentService;

    @Autowired
    StorageService storageService;

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
    public String getCreateStudent(Model model) {
        model.addAttribute("student", new Student());
        return "students-create";
    }

    @PostMapping("/create")
    public String createStudent(@Valid @ModelAttribute("student") Student student, BindingResult bindingresult, @RequestParam(value = "avatarFile", required = false) MultipartFile avatarFile, RedirectAttributes redirectAttributes) {

        if (bindingresult.hasErrors()) {
            return "students-create"; //Giữ nguyên create
        }
        if (avatarFile != null) {
            try {
                String publicUrl = storageService.saveFile(avatarFile);
                student.setAvatar(publicUrl);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
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
    public String editStudent(@Valid @ModelAttribute("studentEdit") Student studentEdit, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "students-edit";
        }
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
