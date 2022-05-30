package com.example.mydigitalcampusmobile;

import static com.google.android.gms.tasks.Tasks.await;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private String id_eleve;
    private String classe_eleve = "";

    DayCourses monday_courses;
    DayCourses tuesday_courses;
    DayCourses wednesday_courses;
    DayCourses thursday_courses;
    DayCourses friday_courses;
    private ArrayList<DayCourses> all_courses = new ArrayList<DayCourses>();


    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //On souhaite créer des journées de cours

        //0 Initialisation des jours de la semaine
        monday_courses = new DayCourses("Lundi");
        tuesday_courses = new DayCourses("Mardi");
        wednesday_courses = new DayCourses("Mercredi");
        thursday_courses = new DayCourses("Jeudi");
        friday_courses = new DayCourses("Vendredi");
        this.all_courses.add(monday_courses);
        this.all_courses.add(tuesday_courses);
        this.all_courses.add(wednesday_courses);
        this.all_courses.add(thursday_courses);
        this.all_courses.add(friday_courses);

        //1 On retrouve l'élève
        SharedPreferences sp = getSharedPreferences("userPreferences", Context.MODE_PRIVATE);
        id_eleve = sp.getString("actual_student", "Pb patron");

        //2 On retrouve sa classe
        db = FirebaseFirestore.getInstance(); //On récupère la database
        DocumentReference docRef = db.collection("eleves").document(id_eleve);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful()) {

                    //On récupère le résultat de la query
                    DocumentSnapshot document = task.getResult();

                    if (document.exists()) {
                        classe_eleve = document.getString("classe");

                        //3 On retrouve ses cours
                        querySetCoursesOfTheStudent();
                    }
                    //Message d'erreur (mauvais document)
                    else {
                        Log.w("Erreur firebase", "Ce document n'existe pas (MainActivity) : Lors de la recherche de la classe de l'eleve");
                    }
                } else {
                    Log.w("Erreur firebase", "Pb de query lors de la recherche de la classe de l'élève (MainActivity) : " + task.getException());
                }
            }
        });



        //5 On lie notre viewpager2 à son adapter
        ViewPager2 viewPager2 = findViewById(R.id.schedule_viewpager);
        viewPager2.setAdapter(
                new ScheduleAdapter(this, this.all_courses)
        );

        //6 Implémentation d'un table layout (le truc où y a écrit les jours de la semaine)
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

    private void querySetCoursesOfTheStudent() {
        /** Fait une requete sur les cours de la classe de l'élève
         *
         */

        db.collection("classes").document(classe_eleve).collection("matieres")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull  Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    //On boucle sur chaque matière
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        //4 On mets les cours dans leur jours et heures respectives
                        setCourses(document.getString("jour"),
                                document.getString("type"),
                                document.getId(),
                                document.getString("enseignant"),
                                document.getString("salle"),
                                document.getString("emplacement"));

                    }
                } else {
                    Log.w("Erreur firebase", "Pb de query lors de la recherche des matières de l'élève (MainActivity) : "+task.getException());
                }
            }
        });
    }

    private void setCourses(String day, String type, String name, String teacher, String room, String emplacement) {
        /** Assigne chaque cours à son jour de la semaine et à son heure
         *
         */
        //On boucle sur les jours déjà crés
        for (DayCourses dc : all_courses){
            if (dc.getDay().equalsIgnoreCase(day)){ // "equalsIgnoreCase()" mais quelle beauté de fonction serieusement wow
                dc.addCourse(new Course(type, name, teacher, room), Integer.parseInt(emplacement));
            }
        }

    }
}