package com.example.mydigitalcampusmobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String id_eleve;
    private String classe_eleve;
    private ArrayList<DayCourses> all_courses = new ArrayList<DayCourses>();


    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //On souhaite créer des journées de cours

        //1 On retrouve l'élève
        SharedPreferences sp = getSharedPreferences("userPreferences", Context.MODE_PRIVATE);
        id_eleve = sp.getString("actual_student", "Pb patron");

        //2 On retrouve sa classe
        db = FirebaseFirestore.getInstance(); //On récupère la database
        DocumentReference docRef = db.collection("eleves").document(id_eleve);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){

                    //On récupère le résultat de la query
                    DocumentSnapshot document = task.getResult();

                    if (document.exists()) {
                        classe_eleve = document.getString("classe");
                    }
                    //Message d'erreur (mauvais document)
                    else {
                        Log.w("Erreur firebase", "Ce document n'existe pas (MainActivity) : Lors de la recherche de la classe de l'eleve");
                    }
                }
                else {
                    Log.w("Erreur firebase", "Pb de query lors de la recherche de la classe de l'élève (MainActivity) : "+task.getException());
                }
            }
        });

        //3 On retrouve ses cours
        db.document(classe_eleve).collection("matieres").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("ddk", document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w("Erreur firebase", "Pb de query lors de la recherche des matières de l'élève (MainActivity) : "+task.getException());
                        }
                    }
                });





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