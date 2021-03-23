package ro.example.myapplication.lab4;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ro.example.myapplication.R;
import ro.example.myapplication.databinding.ActivityMainLab4Binding;


public class MainActivity extends AppCompatActivity implements TickListener{

    private MovieAdapter adapter;
    private ActivityMainLab4Binding dataBinding;
    ClockBroadcast receiver;
    IntentFilter filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main_lab4);

        serializeMovieModel();
        initAdapter();
        initReceiver();
        tick();

       Call<SearchMovieModel> call = ApiBuilder.getInstance().getMovieList(ApiBuilder.API_KEY, "avenger");
       call.enqueue(new Callback<SearchMovieModel>() {
           @Override
           public void onResponse(Call<SearchMovieModel> call, Response<SearchMovieModel> response) {
               List<MovieModel> list = response.body().getSearch();
               adapter.submitList(list);
           }

           @Override
           public void onFailure(Call<SearchMovieModel> call, Throwable t) {

           }
       });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            registerReceiver(receiver, filter);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    private void initReceiver(){
        receiver = new ClockBroadcast();
        receiver.setupListener(this);

        filter = new IntentFilter();
        filter.addAction(Intent.ACTION_TIME_TICK);
    }

    private void sendBroadcast(){
        Intent intent = new Intent();
        intent.setAction("ro.example.broadcast.MY_NOTIFICATION");
        intent.putExtra("data", "Nothing to see here, move along.");
        sendBroadcast(intent);
    }

    private void initAdapter(){
        adapter = new MovieAdapter();
        dataBinding.container.setLayoutManager(new GridLayoutManager(this, 2));
        dataBinding.container.setAdapter(adapter);
    }


    private void serializeMovieModel(){
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<MovieModel> jsonAdapter = moshi.adapter(MovieModel.class);

        MovieModel movie = new MovieModel("My MovieModel", "2021", "0", "");
        String json = jsonAdapter.toJson(movie);

        try {
            MovieModel movie2 = jsonAdapter.fromJson(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getTime(){
        String hour = String.valueOf(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
        String minute = String.valueOf(Calendar.getInstance().get(Calendar.MINUTE));
        return hour + ":" + minute;
    }

    @Override
    public void tick() {
        dataBinding.clock.setText(getTime());
    }
}
