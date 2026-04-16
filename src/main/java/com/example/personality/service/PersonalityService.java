package com.example.personality.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.personality.service.QuestionBank.Dimension;
import com.example.personality.service.QuestionBank.Question;

@Service
public class PersonalityService {

    public record PersonalityResult(
            String type,
            String fullForm,
            List<String> pros,
            List<String> cons,
            List<String> improvements,
            int ei, int sn, int tf, int jp
    ) {}

    private record TraitSet(
            List<String> pros,
            List<String> cons,
            List<String> improvements
    ) {}

    private record Profile(
            String fullForm,
            TraitSet traits
    ) {}

    // All 16 MBTI types: full form + pros/cons/improvements
    private static final Map<String, Profile> PROFILES = Map.ofEntries(

            Map.entry("INTJ", new Profile(
                    "Introverted \u2022 Intuitive \u2022 Thinking \u2022 Judging",
                    new TraitSet(
                            List.of("Strategic and long-term thinker", "Independent and self-motivated", "Analytical problem-solver", "High standards and goal-focused"),
                            List.of("Can seem blunt or emotionally distant", "Overly critical/perfectionistic", "Impatient with inefficiency", "May overthink and isolate"),
                            List.of("Practice empathy and clearer emotional communication", "Accept 'good enough' sometimes to ship faster", "Invite feedback early and collaborate more")
                    )
            )),

            Map.entry("INTP", new Profile(
                    "Introverted \u2022 Intuitive \u2022 Thinking \u2022 Perceiving",
                    new TraitSet(
                            List.of("Logical and objective", "Very curious and innovative", "Good at understanding complex systems", "Independent thinker"),
                            List.of("May appear detached", "Can procrastinate on execution", "Overanalyzes decisions", "May neglect social follow-ups"),
                            List.of("Use deadlines/checklists to finish tasks", "Communicate ideas simply for others", "Schedule follow-ups and maintain relationships")
                    )
            )),

            Map.entry("ENTJ", new Profile(
                    "Extraverted \u2022 Intuitive \u2022 Thinking \u2022 Judging",
                    new TraitSet(
                            List.of("Decisive and confident leader", "Strategic and organized", "Efficient and results-driven", "Comfortable making tough decisions"),
                            List.of("Can be domineering", "Impatient with slow progress", "Work-life imbalance risk", "May sound too blunt"),
                            List.of("Practice active listening before deciding", "Balance goals with people's needs", "Soften tone; be direct but respectful")
                    )
            )),

            Map.entry("ENTP", new Profile(
                    "Extraverted \u2022 Intuitive \u2022 Thinking \u2022 Perceiving",
                    new TraitSet(
                            List.of("Creative and inventive", "Quick-thinking and adaptable", "Persuasive communicator", "Enjoys solving new problems"),
                            List.of("Can be argumentative", "Gets bored with routine", "Starts more than finishes", "May overlook feelings"),
                            List.of("Finish one priority before starting another", "Practice listening to understand (not to debate)", "Build consistency with simple routines")
                    )
            )),

            Map.entry("INFJ", new Profile(
                    "Introverted \u2022 Intuitive \u2022 Feeling \u2022 Judging",
                    new TraitSet(
                            List.of("Insightful and intuitive", "Empathetic and supportive", "Strong values and purpose-driven", "Good at guiding others"),
                            List.of("Sensitive to criticism", "Can burn out from over-giving", "Very private/guarded", "Perfectionistic ideals"),
                            List.of("Set boundaries and protect your time", "Share feelings earlier instead of bottling up", "Lower perfection; focus on progress")
                    )
            )),

            Map.entry("INFP", new Profile(
                    "Introverted \u2022 Intuitive \u2022 Feeling \u2022 Perceiving",
                    new TraitSet(
                            List.of("Idealistic and values-driven", "Deep empathy and kindness", "Creative and imaginative", "Strong sense of meaning"),
                            List.of("Can be overly sensitive", "May procrastinate", "Avoids conflict", "Sometimes unrealistic expectations"),
                            List.of("Add structure: small daily goals", "Practice respectful conflict and assertiveness", "Use evidence + values when deciding")
                    )
            )),

            Map.entry("ENFJ", new Profile(
                    "Extraverted \u2022 Intuitive \u2022 Feeling \u2022 Judging",
                    new TraitSet(
                            List.of("Supportive and motivating", "Strong communicator", "People-focused leadership", "Builds harmony in groups"),
                            List.of("People-pleasing tendencies", "Over-commits and burns out", "Sensitive to conflict", "May become controlling when stressed"),
                            List.of("Set boundaries and learn to say no", "Delegate instead of carrying everything", "Accept disagreement without taking it personally")
                    )
            )),

            // FIXED: ENFP ends with Perceiving (P), not Judging (J)
            Map.entry("ENFP", new Profile(
                    "Extraverted \u2022 Intuitive \u2022 Feeling \u2022 Perceiving",
                    new TraitSet(
                            List.of("Enthusiastic and inspiring", "Creative and big-picture thinker", "Warm and empathetic", "Adapts quickly to change"),
                            List.of("Easily distracted", "Can be disorganized", "Over-commits to too many ideas", "Emotion-driven decisions at times"),
                            List.of("Pick 1–2 priorities and finish them", "Use routines (calendar + weekly plan)", "Pause before decisions; check long-term impact")
                    )
            )),

            Map.entry("ISTJ", new Profile(
                    "Introverted \u2022 Sensing \u2022 Thinking \u2022 Judging",
                    new TraitSet(
                            List.of("Reliable and responsible", "Excellent organization and planning", "Detail-oriented and consistent", "Strong work ethic"),
                            List.of("Can be rigid with rules", "Resistant to change", "May seem overly critical", "Sometimes struggles to express emotions"),
                            List.of("Practice flexibility: try new methods gradually", "Ask before judging; seek context", "Show appreciation and share feelings more")
                    )
            )),

            Map.entry("ISFJ", new Profile(
                    "Introverted \u2022 Sensing \u2022 Feeling \u2022 Judging",
                    new TraitSet(
                            List.of("Loyal and dependable", "Supportive and caring", "Strong attention to detail", "Good at maintaining stability"),
                            List.of("Avoids change", "Takes on too much", "Hesitant to speak up", "May self-sacrifice too often"),
                            List.of("Practice assertiveness and saying no", "Prioritize self-care and rest", "Embrace small changes to build confidence")
                    )
            )),

            Map.entry("ESTJ", new Profile(
                    "Extraverted \u2022 Sensing \u2022 Thinking \u2022 Judging",
                    new TraitSet(
                            List.of("Strong organizer and planner", "Direct and dependable leader", "Practical and efficient", "Good at enforcing standards"),
                            List.of("Can be inflexible", "May sound harsh or judgmental", "Impatient with inefficiency", "May neglect emotions"),
                            List.of("Soften communication; ask then instruct", "Be open to alternative approaches", "Check team morale, not only results")
                    )
            )),

            Map.entry("ESFJ", new Profile(
                    "Extraverted \u2022 Sensing \u2022 Feeling \u2022 Judging",
                    new TraitSet(
                            List.of("Caring and helpful", "Great at teamwork and support", "Organized and responsible", "Community-focused"),
                            List.of("Seeks approval too much", "Sensitive to criticism", "May avoid necessary conflict", "Can struggle with change"),
                            List.of("Set boundaries; don't over-give", "Treat criticism as data, not personal", "Practice adapting and trying new approaches")
                    )
            )),

            Map.entry("ISTP", new Profile(
                    "Introverted \u2022 Sensing \u2022 Thinking \u2022 Perceiving",
                    new TraitSet(
                            List.of("Calm under pressure", "Hands-on problem solver", "Analytical and practical", "Independent and adaptable"),
                            List.of("Emotionally reserved", "Can be risk-prone", "Dislikes long commitments", "May ignore long-term planning"),
                            List.of("Communicate needs clearly (even briefly)", "Add basic long-term planning", "Follow through on important relationships")
                    )
            )),

            Map.entry("ISFP", new Profile(
                    "Introverted \u2022 Sensing \u2022 Feeling \u2022 Perceiving",
                    new TraitSet(
                            List.of("Gentle and caring", "Creative/artistic sense", "Flexible and easygoing", "Observant and present-focused"),
                            List.of("Avoids conflict", "Can be indecisive", "Sensitive to criticism", "May lack structure"),
                            List.of("Set small goals + deadlines", "Practice speaking up respectfully", "Build routine for consistency")
                    )
            )),

            Map.entry("ESTP", new Profile(
                    "Extraverted \u2022 Sensing \u2022 Thinking \u2022 Perceiving",
                    new TraitSet(
                            List.of("Action-oriented and bold", "Practical and realistic", "Great in fast-changing situations", "Confident and persuasive"),
                            List.of("Impulsive/risk-taking", "Gets bored easily", "Dislikes routine", "May miss long-term consequences"),
                            List.of("Pause before acting; consider outcomes", "Use short planning cycles (weekly goals)", "Practice patience and listening")
                    )
            )),

            Map.entry("ESFP", new Profile(
                    "Extraverted \u2022 Sensing \u2022 Feeling \u2022 Perceiving",
                    new TraitSet(
                            List.of("Sociable and energetic", "Practical and adaptable", "Brings positivity to teams", "Learns quickly by doing"),
                            List.of("Impulsive choices", "Avoids long-term planning", "Sensitive to criticism", "Easily distracted"),
                            List.of("Add budgeting + planning habits", "Build resilience to feedback", "Create simple routines to stay focused")
                    )
            ))
    );

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

        Profile profile = PROFILES.get(type);

        // fallback (should not happen if all 16 exist)
        String fullForm = (profile != null) ? profile.fullForm() : expand(type);
        TraitSet traits = (profile != null) ? profile.traits() : new TraitSet(
                List.of("Balanced strengths", "Adaptable in many situations"),
                List.of("May have mixed preferences"),
                List.of("Reflect on habits and keep improving")
        );

        return new PersonalityResult(
                type,
                fullForm,
                traits.pros(),
                traits.cons(),
                traits.improvements(),
                ei, sn, tf, jp
        );
    }

    // Example: "INTJ" -> "Introverted • Intuitive • Thinking • Judging"
    private static String expand(String type) {
        return letterMeaning(type.charAt(0)) + " \u2022 " +
               letterMeaning(type.charAt(1)) + " \u2022 " +
               letterMeaning(type.charAt(2)) + " \u2022 " +
               letterMeaning(type.charAt(3));
    }

    private static String letterMeaning(char c) {
        return switch (c) {
            case 'I' -> "Introverted";
            case 'E' -> "Extraverted";
            case 'S' -> "Sensing";
            case 'N' -> "Intuitive";
            case 'T' -> "Thinking";
            case 'F' -> "Feeling";
            case 'J' -> "Judging";
            case 'P' -> "Perceiving";
            default -> String.valueOf(c);
        };
    }
}