package com.example.app_.entity;

import java.util.List;

public class percent_list {
    public String exam;
    public List<percent_id> lesson;

    public String getExam() {
        return exam;
    }

    public void setExam(String exam) {
        this.exam = exam;
    }

    public List<percent_id> getLesson() {
        return lesson;
    }

    public void setLesson(List<percent_id> lesson) {
        this.lesson = lesson;
    }
}
