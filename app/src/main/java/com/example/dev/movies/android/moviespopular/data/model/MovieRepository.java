package com.example.dev.movies.android.moviespopular.data.model;

import android.app.Application;

import com.example.dev.movies.android.moviespopular.data.service.ApiService;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static com.example.dev.movies.android.moviespopular.util.Constants.API_KEY;

public class MovieRepository {

    private Application application;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MutableLiveData<List<Movie>> movieLiveData = new MutableLiveData<>();
    private List<Movie> movieList = new ArrayList<>();

    public MovieRepository(Application application) {
        this.application = application;

        Observable<MovieResponse> movieResponseObservable = ApiService.getInstance().getMovies(API_KEY, "pt-BR");

        compositeDisposable.add(movieResponseObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap((Function<MovieResponse, Observable<Movie>>) movieResponse ->
                        Observable.fromArray(movieResponse.getMovieList().toArray(new Movie[0])))
                .subscribeWith(new DisposableObserver<Movie>() {
                    @Override
                    public void onNext(Movie movie) {
                        movieList.add(movie);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        movieLiveData.postValue(movieList);
                    }
                }));
    }

    public MutableLiveData<List<Movie>> getMovieLiveData() {
        return movieLiveData;
    }

    public void clear() {
        compositeDisposable.clear();
    }
}
