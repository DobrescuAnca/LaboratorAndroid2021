package ro.example.myapplication.lab2;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import ro.example.myapplication.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lab2);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container, FirstFragment.class, null)
                    .commit();
        }

    }




}
