package com.example.springsaveusername.controller;


import com.example.springsaveusername.model.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("user")
public class LoginController {

    @ModelAttribute("user")
    public User setupUser() {
        return new User();
    }

    @GetMapping("/login")
    public String showForm(@CookieValue(value = "rememberEmail", defaultValue = "") String rememberEmail, Model model) {
        Cookie cookie = new Cookie("rememberEmail", rememberEmail);
        model.addAttribute("emailRemember", cookie.getValue());
        return "login";
    }

    @PostMapping("/doLogin")
    public String doLogin(@ModelAttribute("user") User user, Model model, HttpServletResponse response) {
        if("admin@gmail.com".equals(user.getEmail()) && "admin".equals(user.getPassword())) {
            Cookie cookie = new Cookie("rememberEmail", user.getEmail());
            cookie.setMaxAge(24*60*60);
            response.addCookie(cookie);

            model.addAttribute("message", "Đăng nhập thành công!");
        }else{
            model.addAttribute("message", "Lỗi! Thử lại!");
            user.setEmail("");
        }
        return "login";
    }
}
