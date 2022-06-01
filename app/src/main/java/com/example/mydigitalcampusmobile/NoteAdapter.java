package com.example.mydigitalcampusmobile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.SubjectHolder> {

    Context context;
    private ArrayList<Subject> all_subjects;

    //Constructeur
    public NoteAdapter(Context context, ArrayList<Subject> all_subjects) {
        this.context = context;
        this.all_subjects = all_subjects;
    }

    @NonNull
    @Override
    public SubjectHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //On inflate avec card_item
        View view = LayoutInflater.from(context).inflate(R.layout.card_item, parent, false);
        return new SubjectHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectHolder holder, int position) {
        //On selectionne le sujet que l'on va afficher
        Subject subject = all_subjects.get(position);
        //Puis on l'affiche
        holder.setSubjectDetails(subject);
    }

    @Override
    public int getItemCount() {
        return all_subjects.size();
    }

    static class SubjectHolder extends RecyclerView.ViewHolder{
        TextView subject_name, note_un, note_deux;
        public SubjectHolder(@NonNull View itemView) {
            super(itemView);
            //Recupère les textview de card_item
            subject_name = itemView.findViewById(R.id.note_name);
            note_un = itemView.findViewById(R.id.note_ens);
            note_deux = itemView.findViewById(R.id.note_exam);
        }
        void setSubjectDetails(Subject subject){
            //On affiche le nom de la matière
            subject_name.setText(subject.getName());
            //On affiche les notes en fonction de si la matière contient une note de projet ou non
            note_un.setText(subject.getString_un());
            note_deux.setText(subject.getString_deux());
        }
    }
}