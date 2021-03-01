package ro.example.myapplication.lab2;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import ro.example.myapplication.R;
import ro.example.myapplication.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    public static String CHANNEL_ID = "channel";

    private FragmentSecondBinding dataBinding;

    public SecondFragment(){
        super(R.layout.fragment_second);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_second,
                container,
                false);

        return dataBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String title = bundle.getString(FirstFragment.MOVIE_TITLE);
            ((TextView) view.findViewById(R.id.title)).setText(title);

            MovieModel movie = bundle.getParcelable(FirstFragment.MOVIE);
        }

        view.findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNotif();
            }
        });


        dataBinding.btn.setText("text data binding");
    }

    private void createNotif(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(requireContext(), CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Notificare")
            .setContentText("Descriere notificare")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);

            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = requireActivity().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(requireContext());
        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(1, builder.build());

    }
}
