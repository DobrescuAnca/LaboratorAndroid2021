package ro.example.myapplication.lab4;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ro.example.myapplication.R;
import ro.example.myapplication.databinding.ItemMovieLab4Binding;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<MovieModel> localDataSet;

    public void submitList(List<MovieModel> dataSet) {
        localDataSet = dataSet;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new MovieViewHolder(DataBindingUtil.inflate(
                        LayoutInflater.from(viewGroup.getContext()),
                        R.layout.item_movie_lab4,
                        viewGroup,
                        false)
        );
    }

    @Override
    public void onBindViewHolder(MovieViewHolder viewHolder, final int position) {
        viewHolder.bind(localDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        if(localDataSet == null)
            return 0;
     return localDataSet.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        private final ItemMovieLab4Binding binding;

        public MovieViewHolder(ItemMovieLab4Binding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(MovieModel item){
            binding.title.setText(item.getTitle());
            binding.year.setText(item.getYear());
            Picasso.get().load(item.getPoster()).into(binding.image);
        }
    }
}
