package com.ukmi.iqmsoluction.model;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class Question {
    private int idQuestion;
    private boolean black_list;
    private String statement;
    private String answer;
    private String topic;
    private String tip;
    private String options;
    private String subject_id;
    
    public Question(){
        this.idQuestion = 0;
        this.black_list = false;
        this.statement = "";
        this.answer = "";
        this.topic = "";
        this.tip = "";
        this.options = "";
        this.subject_id = "";
    }
    
    public Question(JSONObject jp){
        try {
            this.setIdQuestion(jp.getInt("idquestion"));
            this.setStatement(jp.getString("statement"));
            this.setAnswer(jp.getString("answer"));
            this.setTopic(jp.getString("topic"));
            this.setTip(jp.getString("tip"));
            this.setOptions(jp.getString("options"));
            this.setBlack_list(jp.getBoolean("black-list"));
            this.setSubject_id(jp.getString("idsubject"));
        } catch (Exception e){
            Log.e("error:", Objects.requireNonNull(e.getMessage()));
        }
    }

    public JSONObject toJSONObject() {
        JSONObject json = new JSONObject();
        try {
            json.put("idquestion", this.getIdQuestion());
            json.put("statement", this.getStatement());
            json.put("answer", this.getAnswer());
            json.put("topic", this.getTopic());
            json.put("tip", this.getTip());
            json.put("options", this.getOptions());
            json.put("black-list", this.isBlack_list());
            json.put("idsubject", this.getSubject_id());

        } catch (JSONException e) {
            Log.e("error:", Objects.requireNonNull(e.getMessage()));
        }
        return json;
    }

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
