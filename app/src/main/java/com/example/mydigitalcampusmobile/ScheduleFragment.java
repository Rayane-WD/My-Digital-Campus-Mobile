package com.example.mydigitalcampusmobile;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;


public class ScheduleFragment extends Fragment {

    private DayCourses courses;
    public ScheduleFragment(DayCourses courses) {

        this.courses = courses;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //On recupere le layout fragment de notre viewpager2
        View root = inflater.inflate(R.layout.fragment_schedule, container, false);



        //On change le texte de notre page a chaque création d'une nouvelle page
        int [] courses_id_in_xml_list = {R.id.course1, R.id.course2, R.id.course3, R.id.course4, R.id.course5}; //Recupère les id

        int n=0; //variable de suivit de la liste des cours id

        //Bouclons  sur les emplacements de cours
        for (int c_id : courses_id_in_xml_list){
            n++;
            if (n==6){break;}

            TextView tv = root.findViewById(c_id);
            tv.setText(this.courses.getString(n)); //Maj du fragment en fonction du cours

            //Maj de la couleur du cours en fonction de son type
            Course course_unit = this.courses.getCourse(n);
            if (course_unit == null){
                tv.getBackground().setColorFilter(ContextCompat.getColor(getContext(), R.color.transparent), PorterDuff.Mode.MULTIPLY);
            }
            else if (course_unit.getType_course().equals("TD")){tv.getBackground().setColorFilter(ContextCompat.getColor(getContext(), R.color.td_course), PorterDuff.Mode.MULTIPLY);}
            else if (course_unit.getType_course().equals("TP")){tv.getBackground().setColorFilter(ContextCompat.getColor(getContext(), R.color.tp_course), PorterDuff.Mode.MULTIPLY);;}
            else if (course_unit.getType_course().equals("TG")){tv.getBackground().setColorFilter(ContextCompat.getColor(getContext(), R.color.tg_course), PorterDuff.Mode.MULTIPLY);;}

        }

        return root;
    }
}