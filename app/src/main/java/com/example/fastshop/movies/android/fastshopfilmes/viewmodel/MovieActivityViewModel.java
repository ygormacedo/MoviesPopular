package com.example.fastshop.movies.android.fastshopfilmes.viewmodel;

import android.app.Application;

import com.example.fastshop.movies.android.fastshopfilmes.data.model.Movie;
import com.example.fastshop.movies.android.fastshopfilmes.data.model.MovieRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class MovieActivityViewModel extends AndroidViewModel {

    private MovieRepository movieRepository;

    public MovieActivityViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new MovieRepository(application);
    }

    public LiveData<List<Movie>> getMovies() {
        return movieRepository.getMovieLiveData();
    }

    public void clear() {
        movieRepository.clear();
    }
}
