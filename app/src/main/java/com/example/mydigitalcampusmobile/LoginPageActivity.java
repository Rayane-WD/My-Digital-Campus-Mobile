package com.example.mydigitalcampusmobile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginPageActivity extends AppCompatActivity {

    //Credentials de connexion a firestore
    String e="rayane.waidi@esme.fr";
    String p="123abc";

    EditText username_email;
    EditText username_password;
    Button login_btn;

    private ProgressDialog progressDialog;

    private FirebaseAuth mAuth;
    private FirebaseUser user = null;
    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("aaaaaaaaaaaz","rrrrrrrrrrr");
        setContentView(R.layout.activity_login_page);

        username_password = findViewById(R.id.password);
        username_email = findViewById(R.id.username);
        login_btn = findViewById(R.id.login_button);

        mAuth = FirebaseAuth.getInstance();


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (user==null){return;}

                //Boite de dialogue d'attente
                progressDialog = new ProgressDialog(LoginPageActivity.this);
                progressDialog.setMessage("Connexion en cours, veuillez patienter...");
                progressDialog.setTitle("Connexion");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                //On récupère la database
                db = FirebaseFirestore.getInstance();

                //On récupère l'email et le mot de passe
                String user_mail = username_email.getText().toString();
                String user_pass = username_password.getText().toString();

                //On essaye de se connecter
                login(user_mail, user_pass);
                progressDialog.dismiss();

            }
        });


    }

    private void LogToFireBase(String email, String pass) {

        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    user = mAuth.getCurrentUser();
                    Toast.makeText(LoginPageActivity.this, "Connecté à FireBase", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(LoginPageActivity.this, "Problème lors de la connexion à FireBase: "+task.getException(), Toast.LENGTH_SHORT).show();
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
                if (task.isSuccessful()){

                    //On récupère le résultat de la query
                    DocumentSnapshot document = task.getResult();

                    if (document.exists()) {
                        if (document.getString("mdp").equals(user_pass)){
                            // B I N G O
                            sendUserToNextActivty();
                        }
                        //Message d'erreur (mauvais mdp)
                        else{
                            Toast.makeText(LoginPageActivity.this, "Mot de passe incorrect", Toast.LENGTH_LONG).show();
                        }
                    }
                    //Message d'erreur (mauvais email)
                    else {
                        Toast.makeText(LoginPageActivity.this, "Cet utilisateur n'existe pas", Toast.LENGTH_LONG).show();
                    }

                }
                else {
                    Toast.makeText(LoginPageActivity.this, "Erreur :"+task.getException(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void sendUserToNextActivty(){
        Intent intent = new Intent(LoginPageActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogToFireBase(e,p);
    }
}