package com.example.favouritemovies.ui.movies;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.favouritemovies.R;
import com.example.favouritemovies.model.MovieDto;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private Context context;
    private List<MovieDto> movieDtoList;
    public MovieAdapterListener onClickListener;

    public MovieAdapter(Context context, List<MovieDto> movieDtoList, MovieAdapterListener listener) {
        this.context = context;
        this.movieDtoList = movieDtoList;
        this.onClickListener = listener;
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_movie, viewGroup, false);
        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MovieDto movieDto = movieDtoList.get(position);
        holder.tvTitle.setText(movieDto.getTitle());
        holder.tvYear.setText("("+movieDto.getYear().toString()+")");
        holder.tvRating.setText(movieDto.getRating().toString());
    }

    void setMovis(List<MovieDto> movies) {
        movieDtoList = movies;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return movieDtoList.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        public TextView tvYear;
        public TextView tvRating;
        public ImageButton btDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvYear = itemView.findViewById(R.id.tvYear);
            tvRating = itemView.findViewById(R.id.tvRating);
            btDelete = itemView.findViewById(R.id.btnDelete);

//            cbIsDone.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    onClickListener.isDoneOnClick(view, getAdapterPosition());
//                }
//            });

            btDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickListener.deleteOnClick(view, getAdapterPosition());
                }
            });
        }
    }


    //region Interface Details listener
    public interface MovieAdapterListener {

//        void isDoneOnClick(View v, int position);

        void deleteOnClick(View v, int position);
    }
}