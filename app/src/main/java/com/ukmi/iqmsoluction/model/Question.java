package com.ukmi.iqmsoluction.model;

public class Question {
    private int idQuestion;
    private boolean black_list;
    private String statement;
    private String answer;
    private String topic;
    private String tip;
    private String options;
    private String subject_id;

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) throws Exception{
        if(statement.length() > 5){
            this.statement = statement;
        }
        else{
            throw new Exception("Statement too short");
        }

    }
9
    public boolean isBlack_list() {
        return black_list;
    }

    public void setBlack_list(boolean black_list) {
        this.black_list = black_list;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }
}
