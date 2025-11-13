package com.ukmi.iqmsoluction.model;

import androidx.annotation.NonNull;

public class Subject {
    private int idSubject;
    private String name;
    private String topic;
    private String description;

    @NonNull
    @Override
    public String toString() {return this.name;}

    public int getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(int idSubject) {
        this.idSubject = idSubject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
