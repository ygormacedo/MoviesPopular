package com.example.dev.movies.android.moviespopular.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dev.movies.android.moviespopular.R;
import com.example.dev.movies.android.moviespopular.data.model.Movie;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.dev.movies.android.moviespopular.util.Constants.IMAGE_URL;

public class MovieDetailsActivity extends AppCompatActivity {

    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        getMovie();
        setComponents();
    }

    private void getMovie() {
        movie = getIntent().getParcelableExtra("movie");
    }

    @SuppressLint("SetTextI18n")
    private void setComponents() {
        ImageView imageMovieDetail = findViewById(R.id.image_movie_detail);
        TextView tvTitle = findViewById(R.id.tittle_movie_detail);
        TextView tvRating = findViewById(R.id.rating_movie_detail);
        TextView tvDate = findViewById(R.id.date_movie_detail);
        TextView tvDescription = findViewById(R.id.description_movie_detail);
        TextView tvBack = findViewById(R.id.button_back_movie_detail);

        Picasso.get().load(IMAGE_URL + movie.getBackdropPath())
                .placeholder(R.drawable.ic_launcher_background).into(imageMovieDetail);
        tvTitle.setText(movie.getTitle());
        tvRating.setText(movie.voteAverage.toString());
        tvDate.setText(movie.getReleaseDate());
        tvDescription.setText(movie.getOverview());
        tvBack.setOnClickListener(v -> onBackPressed());

    }
}
