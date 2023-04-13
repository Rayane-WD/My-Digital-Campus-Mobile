package com.example.mydigitalcampusmobile;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {

    Context context;
    private ArrayList<News> all_news;

    public NewsAdapter(Context context, ArrayList<News> all_news) {

        this.context = context;
        this.all_news = all_news;
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //On inflate avec item_news
        View view = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false);
        return new NewsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {

        //On selectionne une news que l'on va afficher
        News news = all_news.get(position);
        //Puis on l'affiche
        holder.setNews(news);

    }

    @Override
    public int getItemCount() {return all_news.size();}


    static class NewsHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView title;
        View view;

        public NewsHolder(@NonNull View itemView) {
            super(itemView);

            //On récupère la view
            view = itemView;

            //Recupère la imgView et la textView de item_news
            img = itemView.findViewById(R.id.news_image);
            title = itemView.findViewById(R.id.news_title);
        }

        void setNews(News news){

            //Le onClickListener de l'item
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //On est envoyé vers le lien de la news
                    Intent intent = new Intent( Intent.ACTION_VIEW , Uri.parse(  (news.getUrl())  )   );
                    view.getContext().startActivity(intent);
                }
            });

            //On affiche la bonne image
            img.setImageResource(news.getId_img());
            //On affiche le bon titre
            title.setText(news.getTitle());
        }
    }

}