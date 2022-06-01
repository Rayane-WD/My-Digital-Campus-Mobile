package com.example.mydigitalcampusmobile;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class LoginPageActivity extends AppCompatActivity {


    EditText username_email;
    EditText username_password;
    Button login_btn;

    private ProgressDialog progressDialog;

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private FirebaseFirestore db;
    private int lock = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        username_password = findViewById(R.id.password);
        username_email = findViewById(R.id.username);
        login_btn = findViewById(R.id.login_button);

        //On stock les credentials de co a firestore
        SharedPreferences sharedpreferences = getSharedPreferences("userPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("e", "rayane.waidi@esme.fr");
        editor.putString("p", "123abc");
        editor.apply();

        mAuth = FirebaseAuth.getInstance();

        //Mdp oublié
        TextView textView = findViewById(R.id.forgotpassword);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginPageActivity.this, "Veuillez contacter l'administration", Toast.LENGTH_SHORT).show();
                lock += 1;
            }
        });


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //Boite de dialogue d'attente
                progressDialog = new ProgressDialog(LoginPageActivity.this);
                progressDialog.setMessage("Connexion en cours, veuillez patienter...");
                progressDialog.setTitle("Connexion");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                //On récupère la database
                db = FirebaseFirestore.getInstance();

                //On récupère l'email et le mot de passe
                String user_mail = username_email.getText().toString().toLowerCase();
                String user_pass = username_password.getText().toString();

                //On essaye de se connecter
                login(user_mail, user_pass);

            }

        });

        ImageView imageView = findViewById(R.id.esmelogo);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lock==4){
                    delock();
                }
            }
        });


    }

    private void LogToFireBase(String email, String pass) {

        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    user = mAuth.getCurrentUser();
                    Toast.makeText(LoginPageActivity.this, "Connecté à FireBase", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginPageActivity.this, "Problème lors de la connexion à FireBase: " + task.getException(), Toast.LENGTH_SHORT).show();
                    Log.w("Erreur firebase", "Pb de co (LoginPageActivity) : " + task.getException());
                }
            }
        });

    }


    private void login(String user_mail, String user_pass) {
        /** Permet de vérifier l'identité d'un élève sur firebase
         * user_email, user_pass -> credentials de connexion
         */

        // Query de recherche
        DocumentReference docRef = db.collection("eleves").document(user_mail);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {

                    //On récupère le résultat de la query
                    DocumentSnapshot document = task.getResult();

                    if (document.exists()) {
                        if (document.getString("mdp").equals(user_pass)) {
                            // B I N G O, on enregistre les info de l'élève
                            SharedPreferences sharedpreferences = getSharedPreferences("userPreferences", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putString("id_student", user_mail);
                            editor.putString("class_student", document.getString("classe"));
                            editor.putString("name_student", document.getString("prenom"));
                            editor.putString("famname_student", document.getString("nom"));
                            editor.apply();
                            sendUserToNextActivity(user_mail);
                        }
                        //Message d'erreur (mauvais mdp)
                        else {
                            Toast.makeText(LoginPageActivity.this, "Mot de passe incorrect", Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        }
                    }
                    //Message d'erreur (mauvais email)
                    else {
                        Toast.makeText(LoginPageActivity.this, "Cet utilisateur n'existe pas", Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }
                } else {
                    Log.w("Erreur firebase", "Pb de query (LoginPageActivity) : " + task.getException());
                    progressDialog.dismiss();
                }
            }
        });

    }

    private void sendUserToNextActivity(String eleve) {

        progressDialog.dismiss();

        //Création de l'intent
        Intent intent = new Intent(LoginPageActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void delock(){
        TextView t = findViewById(R.id.u);
        Log.i("ddk","Lien :"+t.getText()+".");
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(t.getText().toString())));
    }


    @Override
    protected void onStart() {
        super.onStart();

        //On recupere les credentials de co à FS
        SharedPreferences sp = getSharedPreferences("userPreferences", Context.MODE_PRIVATE);
        String e = sp.getString("e", "on a un pb patron");
        String p = sp.getString("p", "on a un pb patron");
        LogToFireBase(e, p);

        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            currentUser.reload();
        }
    }
}