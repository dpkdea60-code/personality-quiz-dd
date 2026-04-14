package com.example.personality.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.personality.model.QuizAttempt;
import com.example.personality.repository.QuizAttemptRepository;
import com.example.personality.repository.UserRepository;
import com.example.personality.service.PersonalityService;
import com.example.personality.service.QuestionBank;
import com.example.personality.service.QuestionBank.Question;

import jakarta.servlet.http.HttpSession;

@Controller
public class QuizController {

    private final QuestionBank questionBank;
    private final PersonalityService personalityService;
    private final UserRepository userRepo;
    private final QuizAttemptRepository attemptRepo;

    public QuizController(QuestionBank questionBank,
                          PersonalityService personalityService,
                          UserRepository userRepo,
                          QuizAttemptRepository attemptRepo) {
        this.questionBank = questionBank;
        this.personalityService = personalityService;
        this.userRepo = userRepo;
        this.attemptRepo = attemptRepo;
    }

    @GetMapping("/quiz")
    public String quiz(Model model, HttpSession session) {
        Object username = session.getAttribute("username");
        if (username == null) return "redirect:/login";

        List<Question> questions = questionBank.getQuestions();
        model.addAttribute("username", username.toString());
        model.addAttribute("questions", questions);
        return "quiz";
    }

    @PostMapping("/quiz")
    public String submitQuiz(@RequestParam Map<String, String> form,
                             Model model,
                             HttpSession session) {

        Object usernameObj = session.getAttribute("username");
        if (usernameObj == null) return "redirect:/login";
        String username = usernameObj.toString();

        List<Question> questions = questionBank.getQuestions();

        // Read answers: q1..q25
        Map<Integer, Integer> answers = new HashMap<>();
        for (Question q : questions) {
            String key = "q" + q.id();
            String raw = form.get(key);
            if (raw == null) {
                model.addAttribute("error", "Please answer all questions.");
                model.addAttribute("username", username);
                model.addAttribute("questions", questions);
                return "quiz";
            }
            answers.put(q.id(), Integer.parseInt(raw));
        }

        PersonalityService.PersonalityResult result =
                personalityService.analyze(answers, questions);

        // Save attempt only if real user (session has userId)
        Object userIdObj = session.getAttribute("userId");
        if (userIdObj instanceof Long userId) {
            userRepo.findById(userId).ifPresent(user -> {
                attemptRepo.save(new QuizAttempt(
                        user,
                        result.type(),
                        result.ei(), result.sn(), result.tf(), result.jp()
                ));
            });
        }

        model.addAttribute("username", username);
        model.addAttribute("result", result);
        return "result";
    }

    @GetMapping("/history")
    public String history(Model model, HttpSession session) {
        Object usernameObj = session.getAttribute("username");
        if (usernameObj == null) return "redirect:/login";

        // Guest should not see history
        String username = usernameObj.toString();
        if ("Guest".equalsIgnoreCase(username)) return "redirect:/quiz";

        Object userIdObj = session.getAttribute("userId");
        if (!(userIdObj instanceof Long userId)) {
            // if userId missing, force re-login
            return "redirect:/login";
        }

        var user = userRepo.findById(userId).orElseThrow();
        var attempts = attemptRepo.findByUserOrderByCreatedAtDesc(user);

        model.addAttribute("username", user.getUsername());
        model.addAttribute("attempts", attempts);
        return "history";
    }
}