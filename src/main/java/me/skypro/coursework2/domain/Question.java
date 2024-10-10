package me.skypro.coursework2.domain;

import static java.util.Objects.hash;

public class Question {
    private final String question;
    private final String answer;

    public Question(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public String toString() {
        return String.format("Вопрос: %s\nОтвет: %s", question, answer);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Question q = (Question) obj;
        return this.question.equals(q.question) && this.answer.equals(q.answer);
    }

    @Override
    public int hashCode() {
        return hash(question, answer);
    }
}
