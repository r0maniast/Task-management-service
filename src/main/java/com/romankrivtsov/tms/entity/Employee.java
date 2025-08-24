package com.romankrivtsov.tms.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employees_id_seq")
    @SequenceGenerator(name = "employees_id_seq", allocationSize = 1)
    @Column(name = "id")
    int id;

    @Column(name = "name")
    String name;

    @Column(name = "patronymic")
    String patronymic;

    @Column(name = "surname")
    String surname;

    @Column(name = "position")
    String position;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")
    Department department;

    @ManyToMany(mappedBy = "performers")
    private Set<Task> tasks;

    public Employee() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task){
        this.getTasks().add(task);
        task.getPerformers().add(this);
    }

    public void removeTask(Task task){
        this.getTasks().remove(task);
        task.getPerformers().remove(this);
    }
}
