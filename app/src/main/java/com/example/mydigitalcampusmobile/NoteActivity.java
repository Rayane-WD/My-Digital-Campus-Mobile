package com.example.mydigitalcampusmobile;
import static android.content.ContentValues.TAG;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;



public class NoteActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    ArrayList<Subject> listSubjects = new ArrayList<Subject>();
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

//        //mail pour l'authentication à la db
//        String email="mateo.theveney@esme.fr";
//        // password associé
//        String password="123abc";
//
        mAuth = FirebaseAuth.getInstance();
//        mAuth.signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "signInWithEmail:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
//
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "signInWithEmail:failure", task.getException());
//                        }
//                    }
//                });


        //Connexion à la db
        db = FirebaseFirestore.getInstance();

        //Récupère l'id de l'élève
        SharedPreferences sp = getSharedPreferences("userPreferences", Context.MODE_PRIVATE);
        String id_eleve = sp.getString("id_student", "Pb patron");

        db.collection("eleves").document(id_eleve).collection("notes")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                Subject subject = new Subject(document.getId());
                                for (Map.Entry<String, Object> pair : document.getData().entrySet()) {
                                    subject.addNote(pair.getKey(), Double.parseDouble( (String) pair.getValue()));
                                }
                                listSubjects.add(subject);

                                //On recupere la recyclerview
                                recyclerView = findViewById(R.id.recycler);
                                recyclerView.setLayoutManager(new LinearLayoutManager(NoteActivity.this));
                                NoteAdapter adapter = new NoteAdapter(NoteActivity.this, listSubjects);
                                recyclerView.setAdapter(adapter);
                                Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }



    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }
}