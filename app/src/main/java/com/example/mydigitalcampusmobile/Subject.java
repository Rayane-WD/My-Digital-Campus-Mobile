package com.example.mydigitalcampusmobile;
import android.util.Log;
public class Subject {
    private String name = "N/C";
    private String note_name_un, note_name_deux;
    private double note_un = -1;
    private double note_deux = -1;
    public Subject(String name){
        this.name = name;
    }
    public void addNote (String name, double note){
        /** permet d'ajouter une note
         *
         */
        if(note_un==-1){
            this.note_name_un=name;
            this.note_un=note;
        }
        else{
            this.note_name_deux=name;
            this.note_deux=note;
        }
    }
    public String getString_un(){
        if (this.note_un==-1){return " ";}
        return this.note_name_un+" : "+this.note_un;
    }
    public String getString_deux(){
        if (this.note_deux==-1){return " ";}
        return this.note_name_deux+" : "+this.note_deux;
    }
    //Getters
    public String getName(){return this.name;}
    public double getNote_un(){return this.note_un;}
    public double getNote_deux(){return this.note_deux;}
}