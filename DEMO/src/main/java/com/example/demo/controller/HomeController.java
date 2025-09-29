package com.example.demo.controller;


import com.example.demo.model.Student;
import com.example.demo.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    IStudentService studentService;

    @GetMapping("/home")
    public String index(Model model) {
        model.addAttribute("today", LocalDate.now());
        model.addAttribute("numberOne", 10);
        model.addAttribute("numberTwo", 20);

        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "home";
    }

    @GetMapping("/about")
    public ModelAndView about() {
        ModelAndView modelAndView = new ModelAndView("about");
        return modelAndView;
    }
}
