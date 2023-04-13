package com.example.mydigitalcampusmobile;

import java.util.ArrayList;


public class DayCourses {


    private ArrayList<Course> list_of_courses = new ArrayList<Course>();
    private String day;

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
        for (int i=0; i<=5; i++) {
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

    public String getString(int num){
        /** Permet d'avoir la string de présentation d'un cours
         * num -> emplacement du cours dans la journée
         */
        Course c = list_of_courses.get(num);

        //Si il n'y a pas de cours, des sauts de lignes sont affichés
        if (c == null){
            return "\n\n\n";
        }

        return "Cours : " + c.getType_course() + " - " + c.getCourse_name()+"\n"
                +"Salle : " + c.getRoom_id()+"\n"
                +"Enseignant : " + c.getTeacher_name();
    }

    //Getters
    public ArrayList<Course> getCourses(){
        return list_of_courses;
    }
    public Course getCourse(int n){return list_of_courses.get(n);}
    public String getDay() {return day;}
}
