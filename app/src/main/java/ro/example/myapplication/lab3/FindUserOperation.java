package ro.example.myapplication.lab3;

import android.os.AsyncTask;

public class FindUserOperation extends AsyncTask<String, Void, User> {

    UserOperations listener;

    FindUserOperation(UserOperations listener){
        this.listener = listener;
    }

    @Override
    protected User doInBackground(String... names) {

        String name = names[0];
        return MyApplication.getAppDatabase().userDao().findUserWithName(name);
    }

    @Override
    protected void onPostExecute(User user){
        listener.findUser(user);
    }
}
