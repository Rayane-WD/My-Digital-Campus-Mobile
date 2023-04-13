package com.example.mydigitalcampusmobile;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

class ScheduleAdapter extends FragmentStateAdapter {


    private ArrayList<DayCourses> all_courses;

    public ScheduleAdapter(@NonNull FragmentActivity fragmentActivity, ArrayList<DayCourses> courses) {
        super(fragmentActivity);
        this.all_courses = courses;
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        //Création d'un fragment
        return new ScheduleFragment(all_courses.get(position));
    }

    //Nombre de pages
    @Override
    public int getItemCount() {
        return all_courses.size();
    }
}
