package com.example.dev.movies.android.moviespopular.view;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dev.movies.android.moviespopular.R;
import com.example.dev.movies.android.moviespopular.data.model.Movie;
import com.example.dev.movies.android.moviespopular.util.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    private List<Movie> mMovieResults;

    public MovieAdapter(List<Movie> movieResults) {
        this.mMovieResults = movieResults;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        holder.bind(mMovieResults.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovieResults.size();
    }

    class MovieHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle, tvDescription;
        private ImageView imageMovie;

        MovieHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tittle_movie_detail);
            tvDescription = itemView.findViewById(R.id.description_movie_detail);
            imageMovie = itemView.findViewById(R.id.image_movie_detail);

        }


        @SuppressLint("SetTextI18n")
        void bind(Movie movie) {
            if (movie.getOverview().length() > 300) {
                tvDescription.setText(movie.getOverview().substring(0, 300) + "...");
            } else if (movie.getOverview().length() == 0) {
                tvDescription.setText("Filme sem descrição...");
            } else {
                tvDescription.setText(movie.getOverview());
            }
            tvTitle.setText(movie.getTitle());
            Picasso.get().load(Constants.IMAGE_URL + movie.getPosterPath())
                    .placeholder(R.drawable.ic_launcher_foreground).into(imageMovie);
        }
    }
}