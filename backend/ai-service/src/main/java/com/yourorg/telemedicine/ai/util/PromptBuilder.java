package com.yourorg.telemedicine.ai.util;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.yourorg.telemedicine.ai.entity.ChatMessage;
import com.yourorg.telemedicine.ai.entity.VitalReading;

@Component
public class PromptBuilder {

    public String buildPrompt(
            VitalReading vitals,
            List<ChatMessage> history,
            String userMessage) {

        String intent = detectIntent(userMessage);

        String historyText = history.stream()
                .limit(4)
                .map(m -> m.getRole() + ": " + m.getMessage())
                .collect(Collectors.joining("\n"));

        // =========================
        // 1️⃣ LIFESTYLE QUESTIONS
        // =========================
        if ("LIFESTYLE".equals(intent)) {
            return """
            You are MediFusion AI, a friendly health assistant.

            RULES:
            - Answer ONLY the user question
            - Do NOT mention vitals, status, or warnings
            - Give practical lifestyle advice
            - Use bullet points
            - Be positive and supportive
            - Add ONE short disclaimer line

            RESPONSE FORMAT:
            • Tip 1
            • Tip 2
            • Tip 3

            ℹ️ Note:
            (one short line)

            User Question:
            %s
            """.formatted(userMessage);
        }

        // =========================
        // 2️⃣ CONSULTATION QUESTIONS
        // =========================
        if ("CONSULTATION".equals(intent)) {
            return """
            You are MediFusion AI, a healthcare assistant.

            RULES:
            - Explain HOW to consult a doctor
            - Do NOT mention vitals, status, BP, SpO₂, or warnings
            - Give step-by-step guidance
            - Use bullet points
            - Keep it simple and reassuring

            RESPONSE FORMAT:

            👨‍⚕️ How to Consult a Doctor:
            • Step 1
            • Step 2
            • Step 3

            ℹ️ Note:
            (one short line)

            User Question:
            %s
            """.formatted(userMessage);
        }

        // =========================
        // 3️⃣ GENERAL QUESTIONS
        // =========================
        if ("GENERAL".equals(intent)) {
            return """
            You are MediFusion AI.

            RULES:
            - Answer the question clearly
            - Be concise
            - Use bullet points if helpful

            User Question:
            %s
            """.formatted(userMessage);
        }

        // =========================
        // 4️⃣ VITAL ANALYSIS QUESTIONS ONLY
        // =========================
        String vitalsText = (vitals == null)
                ? "No recent vitals available."
                : """
                  • Heart Rate: %d bpm
                  • SpO₂: %d %%
                  • Blood Pressure: %d / %d mmHg
                  """.formatted(
                        vitals.getHeartRate(),
                        vitals.getSpo2(),
                        vitals.getSystolicBP(),
                        vitals.getDiastolicBP()
                );

        return """
        You are MediFusion AI, a healthcare assistant.

        STRICT RULES:
        - Use vitals ONLY to assess health
        - Do NOT diagnose diseases
        - Classify vitals as Normal / Low / High
        - Use bullet points and new lines
        - Give clear next steps
        - Add ONE disclaimer line only

        RESPONSE FORMAT:

        🩺 Health Summary:
        (1–2 lines)

        📊 Vitals:
        (classified values)

        🚨 Status:
        (Normal / Warning / Critical)

        ✅ Recommended Actions:
        (max 3 bullets)

        ℹ️ Disclaimer:
        (single line)

        Patient Vitals:
        %s

        Recent Conversation:
        %s

        User Question:
        %s
        """.formatted(
                vitalsText,
                historyText,
                userMessage
        );
    }

    // =========================
    // INTENT DETECTION (STRICT)
    // =========================
    private String detectIntent(String message) {
        String q = message.toLowerCase();

        if (q.matches(".*\\b(sleep|diet|food|exercise|stress)\\b.*")) {
            return "LIFESTYLE";
        }

        if (q.matches(".*\\b(consult|doctor|appointment|book)\\b.*")) {
            return "CONSULTATION";
        }

        if (q.matches(".*\\b(health|vitals|bp|spo2|heart|condition)\\b.*")) {
            return "VITAL_ANALYSIS";
        }

        return "GENERAL";
    }
}
