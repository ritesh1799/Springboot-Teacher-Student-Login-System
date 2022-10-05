package com.DRCProject.TeacherRegistration.model;

import javax.persistence.*;

@Entity
@Table(name="students")
public class Student {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="Name",nullable = false)
    private String name;
    @Column(name="RollNo")
    private Long rollNo;

    @Column(name="Department")
    private String department;

    @Column(name="Standard")
    private String standard;
    @Column(name="Gender")
    private String gender;
    @Column(name="Age")
    private Long age;

    public Student(){

    }

    public Student(String name, Long rollNo, String department, String standard, String gender, Long age) {
        this.name = name;
        this.rollNo = rollNo;
        this.department = department;
        this.standard = standard;
        this.gender = gender;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getRollNo() {
        return rollNo;
    }

    public void setRollNo(Long rollNo) {
        this.rollNo = rollNo;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }
}
