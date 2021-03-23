package ro.example.myapplication.lab4;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("/")
    Call<SearchMovieModel> getMovieList(
        @Query("apiKey") String apiKey,
        @Query("s") String search
    );
}
