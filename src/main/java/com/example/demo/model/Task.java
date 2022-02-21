package com.example.demo.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name, description;
    private int priority;
    private LocalDate date;

    public void setId(java.lang.Long id) {
        this.id = id;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public void setDescription(java.lang.String description) {
        this.description = description;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public java.lang.Long getId() {
        return id;
    }

    public java.lang.String getName() {
        return name;
    }

    public java.lang.String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public LocalDate getDate() {
        return date;
    }

    public Task(){

    }
    public Task(String name, String description, int priority, LocalDate date){
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.date = date;
    }
}
