package com.example.mydigitalcampusmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Objects;

public class NoteActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Subject> listSubjects = new ArrayList<Subject>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);


        //On crée des matières
        listSubjects.add(new Subject("Anglais", 17, 15));
        listSubjects.add(new Subject("Parcours Innovation", 14));
        listSubjects.add(new Subject("Optimisation"));
        listSubjects.add(new Subject("Anglais", 17, 15));
        listSubjects.add(new Subject("Parcours Innovation", 14));
        listSubjects.add(new Subject("Optimisation"));
        listSubjects.add(new Subject("Anglais", 17, 15));
        listSubjects.add(new Subject("Parcours Innovation", 14));
        listSubjects.add(new Subject("Optimisation"));
        listSubjects.add(new Subject("Anglais", 17, 15));
        listSubjects.add(new Subject("Parcours Innovation", 14));
        listSubjects.add(new Subject("Optimisation"));


        //On recupere la recyclerview
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        NoteAdapter adapter = new NoteAdapter(this, listSubjects);
        recyclerView.setAdapter(adapter);


        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }
}