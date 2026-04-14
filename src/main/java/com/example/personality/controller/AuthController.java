package com.example.personality.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.personality.model.User;
import com.example.personality.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String homePage(Model model, HttpSession session) {
        model.addAttribute("username", session.getAttribute("username"));
        return "home";
    }

    @GetMapping("/about")
    public String aboutPage(Model model, HttpSession session) {
        model.addAttribute("username", session.getAttribute("username"));
        return "about";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // LOGIN USING EMAIL (GMAIL)
    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        try {
            User u = userService.loginByEmail(email, password);

            // store user identity in session
            session.setAttribute("userId", u.getId());
            session.setAttribute("username", u.getUsername());
            session.setAttribute("email", u.getEmail());

            return "redirect:/quiz";
        } catch (IllegalArgumentException ex) {
            model.addAttribute("error", ex.getMessage());
            return "login";
        }
    }

    @PostMapping("/guest")
    public String guest(HttpSession session) {
        // ensure no userId for guest
        session.removeAttribute("userId");
        session.removeAttribute("email");
        session.setAttribute("username", "Guest");
        return "redirect:/quiz";
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@RequestParam String fullName,
                         @RequestParam String email,
                         @RequestParam String username,
                         @RequestParam String password,
                         Model model) {
        try {
            userService.signup(username, password, fullName, email);
            return "redirect:/login";
        } catch (IllegalArgumentException ex) {
            model.addAttribute("error", ex.getMessage());
            return "signup";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}