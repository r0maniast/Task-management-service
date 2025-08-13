package com.romankrivtsov.tms.entity;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tasks_id_seq")
    @SequenceGenerator(name = "tasks_id_seq", allocationSize = 1)
    @Column(name = "id")
    int id;

    @Column(name = "title")
    String title;

    @Column(name = "description")
    String description;


    @Column(name = "status")
    String status;

    @ManyToMany(mappedBy = "tasks")
    private List<Employee> performers;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Employee> getPerformers() {
        return performers;
    }

    public void setPerformers(List<Employee> performers) {
        this.performers = performers;
    }
}
