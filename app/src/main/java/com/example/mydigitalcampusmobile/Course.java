package com.example.mydigitalcampusmobile;

import java.util.ArrayList;
import java.util.List;

public class Course {


    String start_hour;
    String end_hour;
    String course_name;
    String teacher_name;
    String room_num;

    public Course(String start, String end, String course, String teacher, String room) {
        this.start_hour = start;
        this.end_hour = end;
        this.course_name = course;
        this.teacher_name = teacher;
        this.room_num = room;
    }

    //Getters
    public String getStart_hour() {return start_hour;}
    public String getEnd_hour() {return end_hour;}
    public String getCourse_name() {return course_name;}
    public String getTeacher_name() {return teacher_name;}
    public String getRoom_num() {return room_num;}
}

