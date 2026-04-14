package com.example.personality.service;

import com.example.personality.service.QuestionBank.Question;
import com.example.personality.service.QuestionBank.Dimension;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PersonalityService {

    public record PersonalityResult(
            String type,
            List<String> pros,
            List<String> cons,
            List<String> improvements,
            int ei, int sn, int tf, int jp
    ) {}

    public PersonalityResult analyze(Map<Integer, Integer> answers, List<Question> questions) {
        int ei = 0, sn = 0, tf = 0, jp = 0;

        for (Question q : questions) {
            int value = answers.getOrDefault(q.id(), 0);  // -2..2
            int signed = value * q.direction();

            if (q.dimension() == Dimension.EI) ei += signed;
            if (q.dimension() == Dimension.SN) sn += signed;
            if (q.dimension() == Dimension.TF) tf += signed;
            if (q.dimension() == Dimension.JP) jp += signed;
        }

        String type =
                (ei >= 0 ? "E" : "I") +
                (sn >= 0 ? "S" : "N") +
                (tf >= 0 ? "T" : "F") +
                (jp >= 0 ? "J" : "P");

        return new PersonalityResult(
                type,
                List.of("Sample pro 1", "Sample pro 2"),
                List.of("Sample con 1", "Sample con 2"),
                List.of("Improvement 1", "Improvement 2"),
                ei, sn, tf, jp
        );
    }
}