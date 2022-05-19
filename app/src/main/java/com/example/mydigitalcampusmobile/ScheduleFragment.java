package com.example.mydigitalcampusmobile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class ScheduleFragment extends Fragment {


    public ScheduleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);


        //On crée la journée de cours
        DayCourses monday_courses = new DayCourses("Lundi");
        monday_courses.addCourse(new Course("8h30", "10h30", "Anglais", "MUNN", "B06A"), 1);
        monday_courses.addCourse(new Course("10h30", "12h30", "Programation web", "BENABOU", "B14"), 2);

        //On récupère la view
        ListView listView = view.findViewById(R.id.schedule_listview_id);

        //
//        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
//                getActivity(),
//                android.R.layout.simple_list_item_1,
//
//        );


        return view;
    }
}