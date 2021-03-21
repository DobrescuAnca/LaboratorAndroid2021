package ro.example.myapplication.lab3;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ro.example.myapplication.R;

public class MainActivity extends AppCompatActivity implements UserOperations {

    public final static String PREFERENCES_KEY = "preferences key";
    public final static String PREFERENCES_ID_KEY = "preferences id key";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lab3);

        findViewById(R.id.button).setOnClickListener(view ->
//                makePreferences()
                insertUsers()
        );

        findViewById(R.id.button_getName).setOnClickListener(view ->
                new FindUserOperation(this).execute("Irina")
        );


//        UserDao userDao = MyApplication.getAppDatabase().userDao();
//        List<User> users = userDao.getAll();
    }

    private void makePreferences() {
//        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences preferences = getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(PREFERENCES_ID_KEY, 10);
        editor.apply();

        int id = preferences.getInt(PREFERENCES_ID_KEY, 0);

        Toast.makeText(this, id, Toast.LENGTH_LONG).show();
    }

    private void insertUsers(){
        User user1 = new User(
                1,
                "Irina",
                "Popescu",
                20
        );
        User user2 = new User(
                2,
                "Cristi",
                "Andrei",
                18
        );
        User[] userList = new User[]{user1, user2};

        new InsertUserOperation(this).execute(userList);
    }

    @Override
    public void insertUsers(String result) {
        if(result.equals("success"))
            Toast.makeText(this, "Users inserted in the database sucessfully", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Users inserted in the database failed", Toast.LENGTH_LONG).show();
    }

    @Override
    public void findUser(User user) {
        if(user != null)
            Toast.makeText(this, user.firstName + " is " + user.age, Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "fail", Toast.LENGTH_LONG).show();
    }

}
