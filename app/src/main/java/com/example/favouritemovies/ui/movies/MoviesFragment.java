package com.example.favouritemovies.ui.movies;

import android.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.favouritemovies.MovieApplication;
import com.example.favouritemovies.R;
import com.example.favouritemovies.model.MovieDto;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MoviesFragment extends Fragment implements MoviesScreen {

    @Inject
    MoviesPresenter moviesPresenter;
    private RecyclerView recyclerViewMovies;
    private SwipeRefreshLayout swipeRefreshLayoutMovies;
    private TextView tvEmpty;
    private List<MovieDto> movieDtoList;
    private MovieAdapter movieAdapter;

    public MoviesFragment() {
        MovieApplication.injector.inject(this);
    }

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);
        moviesPresenter.attachScreen(this);
    }

    @Override
    public void onDetach() {
        moviesPresenter.detachScreen();
        super.onDetach();
    }

    @Override
    public void onPause() {
        moviesPresenter.unRegister();
        super.onPause();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_movies, container, false);
        tvEmpty = view.findViewById(R.id.tvEmpty);
        recyclerViewMovies = view.findViewById(R.id.recyclerViewMovies);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(RecyclerView.VERTICAL);
        recyclerViewMovies.setLayoutManager(llm);

        movieDtoList = new ArrayList<>();
        movieAdapter = new MovieAdapter(getContext(), movieDtoList, new MovieAdapter.MovieAdapterListener() {
//            @Override
//            public void isDoneOnClick(View v, int position) {
//                MovieDto movieDto = movieDtoList.get(position);
//                moviesPresenter.updateMovie(movieDto, !isNetworkAvailable());
//            }

            @Override
            public void deleteOnClick(View v, int position) {
                MovieDto movieDto = movieDtoList.get(position);
                moviesPresenter.deleteMovie(movieDto, !isNetworkAvailable());
            }
        });
        recyclerViewMovies.setAdapter(movieAdapter);

        swipeRefreshLayoutMovies = view.findViewById(R.id.swipeRefreshLayoutMovies);

        swipeRefreshLayoutMovies.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                moviesPresenter.showMoviesList(!isNetworkAvailable());
            }
        });
        return view;
    }

    @Override
    public void showMovies(List<MovieDto> movieDtos) {
        if (swipeRefreshLayoutMovies != null) {
            swipeRefreshLayoutMovies.setRefreshing(false);
        }
        movieDtoList.clear();
        movieDtoList.addAll(movieDtos);
        movieAdapter.notifyDataSetChanged();


        if (movieDtoList.isEmpty()) {
            recyclerViewMovies.setVisibility(View.GONE);
            tvEmpty.setVisibility(View.VISIBLE);
        } else {
            recyclerViewMovies.setVisibility(View.VISIBLE);
            tvEmpty.setVisibility(View.GONE);
        }

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void showNetworkError(String message) {
        if (swipeRefreshLayoutMovies != null) {
            swipeRefreshLayoutMovies.setRefreshing(false);
        }
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void remove(MovieDto movieDto) {
        movieDtoList.remove(movieDto);
        movieAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        moviesPresenter.showMoviesList(!isNetworkAvailable());
    }

}