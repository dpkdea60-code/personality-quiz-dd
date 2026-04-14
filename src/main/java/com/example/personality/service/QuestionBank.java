package com.example.personality.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.personality.service.QuestionBank.Question;

@Service
public class QuestionBank {

    public enum Dimension { EI, SN, TF, JP }

    public record Question(int id, String text, Dimension dimension, int direction) {}

    public List<Question> getQuestions() {
    return List.of(
        // EI (6)
        new Question(1,  "I feel energized when I’m around people.", Dimension.EI, +1),
        new Question(2,  "I prefer quiet time alone to recharge.", Dimension.EI, -1),
        new Question(3,  "I enjoy meeting new people.", Dimension.EI, +1),
        new Question(4,  "I usually keep my thoughts to myself.", Dimension.EI, -1),
        new Question(5,  "I like being involved in group activities.", Dimension.EI, +1),
        new Question(6,  "I avoid large social gatherings.", Dimension.EI, -1),

        // SN (6)
        new Question(7,  "I focus more on facts than theories.", Dimension.SN, +1),
        new Question(8,  "I enjoy thinking about future possibilities.", Dimension.SN, -1),
        new Question(9,  "I prefer practical solutions.", Dimension.SN, +1),
        new Question(10, "I notice patterns and hidden meanings easily.", Dimension.SN, -1),
        new Question(11, "I trust real-world experience over imagination.", Dimension.SN, +1),
        new Question(12, "I like abstract ideas and concepts.", Dimension.SN, -1),

        // TF (6)
        new Question(13, "I make decisions mainly using logic.", Dimension.TF, +1),
        new Question(14, "I consider people’s feelings when deciding.", Dimension.TF, -1),
        new Question(15, "I value fairness over harmony.", Dimension.TF, +1),
        new Question(16, "I avoid conflict to keep peace.", Dimension.TF, -1),
        new Question(17, "I enjoy debates and critical discussion.", Dimension.TF, +1),
        new Question(18, "I empathize easily with others.", Dimension.TF, -1),

        // JP (7) => total 25
        new Question(19, "I like planning ahead.", Dimension.JP, +1),
        new Question(20, "I prefer to stay flexible and spontaneous.", Dimension.JP, -1),
        new Question(21, "I feel better when things are organized.", Dimension.JP, +1),
        new Question(22, "I often change plans at the last minute.", Dimension.JP, -1),
        new Question(23, "I like finishing tasks before relaxing.", Dimension.JP, +1),
        new Question(24, "I keep my options open as long as possible.", Dimension.JP, -1),
        new Question(25, "I like schedules and clear deadlines.", Dimension.JP, +1)
    );
}
}