package com.romankrivtsov.tms.entity;

import com.romankrivtsov.tms.entity.enums.TaskStatus;
import jakarta.persistence.*;

import java.util.Set;


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
    @Enumerated(EnumType.STRING)
    TaskStatus status;

    @ManyToMany(cascade = {CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    @JoinTable(name = "employee_task",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private Set<Employee> performers;

    public Task() {
    }

    public int getId() {
        return id;
    }

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

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Set<Employee> getPerformers() {
        return performers;
    }

    public void setPerformers(Set<Employee> performers) {
        this.performers = performers;
    }

    public void addPerformer(Employee employee){
        this.getPerformers().add(employee);
        employee.getTasks().add(this);
    }

    public void removePerformer(Employee employee){
        this.getPerformers().remove(employee);
        employee.getTasks().remove(this);
    }
}
