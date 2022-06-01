package com.example.mydigitalcampusmobile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


public class NoteFragment extends Fragment {


    RecyclerView recyclerView;

    public NoteFragment(DayCourses courses) {

    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //On recupere le layout fragment de notre viewpager2
        View root = inflater.inflate(R.layout.fragment_note, container, false);

        return root;


    }



}