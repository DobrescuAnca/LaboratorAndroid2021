package ro.example.myapplication.lab4;

import java.util.List;

public class SearchMovieModel {

    private List<MovieModel> Search;
    private Long totalResults;

    public List<MovieModel> getSearch() {
        return Search;
    }

    public Long getTotalResults() {
        return totalResults;
    }
}
