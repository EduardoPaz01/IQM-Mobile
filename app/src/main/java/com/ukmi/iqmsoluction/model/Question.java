package com.ukmi.iqmsoluction.model;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class Question {
    private int idQuestion;
    private int black_list;
    private String question;
    private String answer;
    private String topic;
    private String tip;
    private String options;
    private int subject_id;
    
    public Question(){
        this.idQuestion = 0;
        this.black_list = 0;
        this.question = "";
        this.answer = "";
        this.topic = "";
        this.tip = "";
        this.options = "";
        this.subject_id = -1;
    }
    
    public Question(JSONObject jp){
        try {
            this.setIdQuestion(jp.getInt("idquestion"));
            this.setStatement(jp.getString("nStatement"));
            this.setAnswer(jp.getString("nAnswer"));
            this.setTopic(jp.getString("nTopic"));
            this.setTip(jp.getString("nTip"));
            this.setOptions(jp.getString("nOptions"));
            this.setBlack_list(jp.getInt("nBlackliist"));
            this.setSubject_id(jp.getInt("idsubject"));
        } catch (Exception e){
            Log.e("error:", Objects.requireNonNull(e.getMessage()));
        }
    }

    public JSONObject toJSONObject() {
        JSONObject json = new JSONObject();
        try {
            json.put("idquestion", this.getIdQuestion());
            json.put("nStatement", this.getStatement());
            json.put("nAnswer", this.getAnswer());
            json.put("nTopic", this.getTopic());
            json.put("nTip", this.getTip());
            json.put("nOptions", this.getOptions());
            json.put("nBlackliist", this.isBlack_list());
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
        return question;
    }

    public void setStatement(String question) throws Exception{
        if(question.length() > 5){
            this.question = question;
        }
        else{
            throw new Exception("Statement too short");
        }

    }

    public int isBlack_list() {
        return black_list;
    }

    public void setBlack_list(int black_list) {
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

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }
}
