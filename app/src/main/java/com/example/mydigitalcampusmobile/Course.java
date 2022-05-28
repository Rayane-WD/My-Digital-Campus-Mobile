package com.example.mydigitalcampusmobile;

import java.util.ArrayList;
import java.util.List;

public class Course {


    private String type_course;
    String course_name;
    String teacher_name;
    String room_id;

    public Course(String type, String course, String teacher, String room) {
        this.type_course = type;
        this.course_name = course;
        this.teacher_name = teacher;
        this.room_id = room;
    }

    //Getters
    public String getType_course() {return type_course;}
    public String getCourse_name() {return course_name;}
    public String getTeacher_name() {return teacher_name;}
    public String getRoom_id() {return room_id;}
}

