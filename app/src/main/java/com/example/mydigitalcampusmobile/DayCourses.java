package com.example.mydigitalcampusmobile;

import java.util.ArrayList;


public class DayCourses {


    ArrayList<Course> list_of_courses = new ArrayList<Course>();
    String day;

    //Constructors
    public DayCourses() {
        initializeList();
    }
    public DayCourses(String day_name) {
        this.day = day_name;
        initializeList();
    }

    //Methods
    private void initializeList(){
        /** Permet de ne pas laisser de case vide dans la liste des cours

         */
        for (int i=0; i<4; i++) {
            list_of_courses.add(null);
        }

    }
    public void addCourse(Course new_course, int num){
        /** Permet d'ajouter un cours
         * new_course -> cours à ajouter
         * num -> emplacement dans la journée, et donc dans la liste des cours

         */
        list_of_courses.add(num, new_course);
    }

    //Getters
    public ArrayList<Course> getCourses(){
        return list_of_courses;
    }
}
