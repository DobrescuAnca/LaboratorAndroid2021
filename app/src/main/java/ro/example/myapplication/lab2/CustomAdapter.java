package ro.example.myapplication.lab2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ro.example.myapplication.R;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MovieViewHolder> {

    private List<MovieModel> localDataSet;
    public static OnItemClickListener itemClickListener;

    public CustomAdapter(
            List<MovieModel> dataSet,
            OnItemClickListener listener
    ) {
        itemClickListener = listener;
        localDataSet = dataSet;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_movie, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder viewHolder, final int position) {
        viewHolder.bind(localDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView duration;
        private final ImageView movieImage;

        private final ConstraintLayout layout;

        public MovieViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.movie_title);
            duration = view.findViewById(R.id.tv_duration);
            movieImage =  view.findViewById(R.id.iv_picture);
            layout =  view.findViewById(R.id.container);
        }

        public void bind(MovieModel item){
            title.setText(item.getTitle());
            duration.setText(item.getDuration());
            movieImage.setImageDrawable(ContextCompat.getDrawable(movieImage.getContext(), item.getImageId()));

            layout.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClick(item);
                }
            });
        }
    }
}
