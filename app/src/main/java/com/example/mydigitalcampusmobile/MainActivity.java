package com.example.mydigitalcampusmobile;

import static com.google.android.gms.tasks.Tasks.await;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {


    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle  actionBarDrawerToggle;

    private String id_eleve;
    private String classe_eleve = "";
    private String name_eleve = "";
    private String famname_eleve = "";

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

        //On recupere les info de l'élève (enregistrés depuis la login activity)
        SharedPreferences sp = getSharedPreferences("userPreferences", Context.MODE_PRIVATE);
        id_eleve = sp.getString("id_student", "Pb patron");
        classe_eleve = sp.getString("class_student", "Pb patron");
        name_eleve = sp.getString("name_student", "y a 1 pb patron");
        famname_eleve = sp.getString("famname_student", "Pb patron");



        //Implémentation du drawer layout et de sa toggle bar
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        //On ajoute la toggle bar à notre toolbar spéciale
        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setTitle("");
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //On modifie le header du drawer menu
        TextView tv = navigationView.getHeaderView(0).findViewById(R.id.text_intro_name);
        tv.setText(famname_eleve+"\n"+name_eleve);
        TextView tv2 = navigationView.getHeaderView(0).findViewById(R.id.text_intro_class);
        tv2.setText(classe_eleve);

        //Listener de notre drawer
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId()){
                    //Clic sur Emploi du temps
                    case R.id.nav_schedule:
                        sendUserToMainActivity();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    //Clic sur Notes
                    case R.id.nav_note:
                        sendUserToNoteActivity();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                }

                return true;
            }
        });



        /**On souhaite créer des journées de cours - Etapes :   **/

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

        //1 On retrouve ses cours
        //On récupère la database
        db = FirebaseFirestore.getInstance();
        querySetCoursesOfTheStudent();



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
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
                        //2 On mets les cours dans leur jours et heures respectives
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
        //3 Mise en place du fragment viewpager2
        setFragment();

    }

    private void setFragment(){
        /** Met en place le view pager avec les cours et le table layout
         *
         */

        //On lie notre viewpager2 à son adapter
        ViewPager2 viewPager2 = findViewById(R.id.schedule_viewpager);
        viewPager2.setAdapter(
                new ScheduleAdapter(this, this.all_courses)
        );

        //Implémentation d'un table layout (le truc où y a écrit les jours de la semaine)
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

    private void sendUserToNoteActivity(){
        /** Permet d'aller la page des notes
         *
         */
        //Création de l'intent
        Intent intent = new Intent(MainActivity.this, NoteActivity.class);
        startActivity(intent);
    }

    private void sendUserToMainActivity(){
        /** Permet d'aller la page de l'emploi du temps
         *
         */
        //Création de l'intent
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent);
    }
}