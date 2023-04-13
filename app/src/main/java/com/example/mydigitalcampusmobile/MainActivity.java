package com.example.mydigitalcampusmobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;

    private String id_eleve;
    private String classe_eleve;

    ArrayList<News> allNews = new ArrayList<News>();

    DayCourses monday_courses;
    DayCourses tuesday_courses;
    DayCourses wednesday_courses;
    DayCourses thursday_courses;
    DayCourses friday_courses;
    private final ArrayList<DayCourses> all_courses = new ArrayList<DayCourses>();
    private TabLayout tabLayout;

    RecyclerView recyclerView_news;
    RecyclerView recyclerView_notes;
    ViewPager2 viewPager2;
    ArrayList<Subject> listSubjects = new ArrayList<Subject>();

    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //On recupere les info de l'élève (enregistrés depuis la login activity)
        SharedPreferences sp = getSharedPreferences("userPreferences", Context.MODE_PRIVATE);
        id_eleve = sp.getString("id_student", "Pb patron");
        classe_eleve = sp.getString("class_student", "Pb patron");
        String name_eleve = sp.getString("name_student", "y a 1 pb patron");
        String famname_eleve = sp.getString("famname_student", "Pb patron");


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
        tv.setText(famname_eleve + "\n" + name_eleve);
        TextView tv2 = navigationView.getHeaderView(0).findViewById(R.id.text_intro_class);
        tv2.setText(classe_eleve);

        //Listener de notre drawer
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    //Clic sur les actualités
                    case R.id.nav_news:
                        //On met en invisble les objets que l'on ne veut pas afficher
                        tabLayout.setVisibility(View.GONE);
                        viewPager2.setVisibility(View.GONE);
                        recyclerView_notes.setVisibility(View.GONE);

                        //On affiche le/les bon(s)
                        recyclerView_news.setVisibility(View.VISIBLE);

                        //On ferme le drawer
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    //Clic sur Emploi du temps
                    case R.id.nav_schedule:
                        //On met en invisble les objets que l'on ne veut pas afficher
                        recyclerView_news.setVisibility(View.GONE);
                        recyclerView_notes.setVisibility(View.GONE);

                        //On affiche le/les bon(s)
                        tabLayout.setVisibility(View.VISIBLE);
                        viewPager2.setVisibility(View.VISIBLE);

                        //On ferme le drawer
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    //Clic sur Notes
                    case R.id.nav_note:
                        //On met en invisble les objets que l'on ne veut pas afficher
                        recyclerView_news.setVisibility(View.GONE);
                        tabLayout.setVisibility(View.GONE);
                        viewPager2.setVisibility(View.GONE);

                        //On affiche le/les bon(s)
                        recyclerView_notes.setVisibility(View.VISIBLE);

                        //On ferme le drawer
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                }

                return true;
            }
        });



        //On récupère la database
        db = FirebaseFirestore.getInstance();


        //On charge le Fragment des cours
        querySetCoursesOfTheStudent();

        //On charge le Fragment des notes
        querySetNotesOfTheStudent();

        //On charge et affiche le fil d'actualité
        description_webscrape dw = new description_webscrape();
        dw.execute();
        setNewsFragment();



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    private void setNews(){

        allNews.add(new News(R.drawable.img_1news, getString(R.string.title_news1), getString(R.string.url_news1)) );
        allNews.add(new News(R.drawable.img_2news, getString(R.string.title_news2), getString(R.string.url_news2)) );
        allNews.add(new News(R.drawable.img_3news, getString(R.string.title_news3), getString(R.string.url_news3)) );
        allNews.add(new News(R.drawable.img_4news, getString(R.string.title_news4), getString(R.string.url_news4)) );
        allNews.add(new News(R.drawable.img_5news, getString(R.string.title_news5), getString(R.string.url_news5)) );
        allNews.add(new News(R.drawable.img_6news, getString(R.string.title_news6), getString(R.string.url_news6)) );
    }

    private void setNewsFragment() {
        /** Permet d'aller la page des actualités
         *
         */

        //On recupère les news (locales pour à avoir gérer trop de réseau dans ce petit projet haha)
        setNews();

        //On recupere la recyclerview
        recyclerView_news = findViewById(R.id.recycler_news);
        recyclerView_news.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        NewsAdapter adapter = new NewsAdapter(MainActivity.this, allNews);
        recyclerView_news.setAdapter(adapter);

    }




    private void setDayCourses(){
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

    }

    private void setCourses(String day, String type, String name, String teacher, String room, String emplacement) {
        /** Assigne chaque cours à son jour de la semaine et à son heure
         *
         */
        //On boucle sur les jours déjà crés
        for (DayCourses dc : all_courses) {
            if (dc.getDay().equalsIgnoreCase(day)) { // "equalsIgnoreCase()" mais quelle beauté cette fonction serieusement wow
                dc.addCourse(new Course(type, name, teacher, room), Integer.parseInt(emplacement));
            }
        }

    }

    private void setScheduleFragment() {
        /** Met en place le view pager avec les cours et le table layout
         *
         */

        //On lie notre viewpager2 à son adapter
        viewPager2 = findViewById(R.id.schedule_viewpager);
        viewPager2.setAdapter(
                new ScheduleAdapter(this, this.all_courses)
        );

        //Implémentation d'un table layout (le truc où y a écrit les jours de la semaine)
        tabLayout = findViewById(R.id.tablayout_id); // On récupère notre table layout depuis la fragment schedule
        //On lie le tablayout à notre viewpager2
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

        //On masque l'emploi du temps
        tabLayout.setVisibility(View.GONE);
        viewPager2.setVisibility(View.GONE);

    }

    private void querySetCoursesOfTheStudent() {
        /** Fait une requete sur les cours de la classe de l'élève puis les affiches dans un fragment
         *
         */

        setDayCourses();

        db.collection("classes").document(classe_eleve).collection("matieres")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            //On boucle sur chaque matière
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                //On mets les cours dans leur jours et heures respectives
                                setCourses(document.getString("jour"),
                                        document.getString("type"),
                                        document.getId(),
                                        document.getString("enseignant"),
                                        document.getString("salle"),
                                        document.getString("emplacement"));

                            }
                        } else {
                            Log.w("Erreur firebase", "Recherche des matières de l'élève impossible :" + task.getException());
                            backToLoginPage();
                        }

                        //Mise en place du fragment viewpager2 et de son tabLayout
                        setScheduleFragment();
                    }
                });


    }



    private void setNoteFragment() {
        /** Permet d'aller la page des notes
         *
         */
        //On recupere la recyclerview
        recyclerView_notes = findViewById(R.id.recycler_notes);
        recyclerView_notes.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        NoteAdapter adapter = new NoteAdapter(MainActivity.this, listSubjects);
        recyclerView_notes.setAdapter(adapter);

        //On masque les notes
        recyclerView_notes.setVisibility(View.GONE);

    }

    private void querySetNotesOfTheStudent() {
        /** Fait une requete sur les notes de l'élève
         *
         */

        db.collection("eleves").document(id_eleve).collection("notes")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                Subject subject = new Subject(document.getId());
                                for (Map.Entry<String, Object> pair : document.getData().entrySet()) {
                                    subject.addNote(pair.getKey(), Double.parseDouble((String) pair.getValue()));
                                }
                                listSubjects.add(subject);
                                setNoteFragment();

                            }
                        } else {
                            Log.w("Erreur firebase", "Recherche des notes de l'élève impossible :" + task.getException());
                            backToLoginPage();
                        }
                    }
                });

    }


    private void backToLoginPage(){
        Intent intent = new Intent(MainActivity.this, LoginPageActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private class description_webscrape extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {

            Document document = null;
            try {
                document = Jsoup.connect("https://www.google.fr/search?q=web").get();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Elements elements = document.getElementsByClass("kno-rdesc");
            String desc = elements.text();
            allNews.add(new News(R.drawable.img_1news, desc, getString(R.string.url_news1)) );

            return null;
        }
    }


}
