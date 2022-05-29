package com.example.mydigitalcampusmobile;

import android.util.Log;

public class Subject {


    private String name = "N/C";
    private int note_ens = -1;
    private int note_exam = -1;
    private int note_pro = -1;

    public Subject(String name, int note_ens, int note_exam){

        this.name = name;
        this.note_ens = note_ens;
        this.note_exam = note_exam;
    }

    public Subject(String name, int note_pro){

        this.name = name;
        this.note_pro = note_pro;
    }

    public Subject(String name){
        this.name = name;
    }

    public boolean isProject(){
        /** Retourne true si la matière a uniquement une note de projet
         *
         */


        return this.note_pro>=0;
    }

    public String getString_ens(){
        if (this.note_ens==-1){return "NE : N/A";}
        return "NE : "+this.note_ens;
    }
    public String getString_exam(){
        if (this.note_exam==-1){return "Examen : N/A";}
        return "Examen : "+this.note_exam;
    }
    public String getString_pro(){
        return "Projet : "+this.note_pro;
    }


    //Getters
    public String getName(){return this.name;}
    public int getNote_ens(){return this.note_ens;}
    public int getNote_exam(){return this.note_exam;}
    public int getNote_pro(){return this.note_pro;}
}
