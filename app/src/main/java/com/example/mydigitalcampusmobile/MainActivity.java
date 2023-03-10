package com.example.mydigitalcampusmobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<DayCourses> all_courses = new ArrayList<DayCourses>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //On crée des journées de cours
        DayCourses monday_courses = new DayCourses("Lundi");
        monday_courses.addCourse(new Course("TD", "Anglais", "MUNN", "B06A"), 1);
        monday_courses.addCourse(new Course("TP", "Programation weeeeeeeeeeb", "BENABOU", "B14"), 2);
        monday_courses.addCourse(new Course("TG", "maaaaaaaaaaaaaaaaaaaaaaaaaaaaaaths", "ugkjhdcjjjjjjjfxxxxxxxxxxxxxxx", "B14"), 4);
        this.all_courses.add(monday_courses);

        DayCourses thuesday_courses = new DayCourses("Mardi");
        thuesday_courses.addCourse(new Course("TD", "Anglais", "MUNN", "B06A"), 1);
        thuesday_courses.addCourse(new Course("TP", "Programation web", "BENABOU", "B14"), 4);
        this.all_courses.add(thuesday_courses);


        //On lie notre viewpager2 à son adapter
        ViewPager2 viewPager2 = findViewById(R.id.schedule_viewpager);
        viewPager2.setAdapter(
                new ScheduleAdapter(this, this.all_courses)
        );

        //Implémentation d'un table layout
        TabLayout tabLayout = findViewById(R.id.tablayout_id); // On récupère notre table layout depuis la fragment schedule
        new TabLayoutMediator(
                tabLayout,
                viewPager2,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText(all_courses.get(position).getDay());
                    }
                }
        ).attach();

    }
}