package com.mbyte.easy.admin.entity;

import java.util.ArrayList;
import java.util.List;

public class BlogAnswers {

    private String title;//问题
    private List<String> answers= new ArrayList<String>();//所有回答

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public void addAnswer (String answer)
    {
        answers.add(answer);
    }
}
