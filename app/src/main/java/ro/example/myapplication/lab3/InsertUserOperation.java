package ro.example.myapplication.lab3;

import android.os.AsyncTask;

class InsertUserOperation extends AsyncTask<User, Void, String> {

    UserOperations listener;

    InsertUserOperation(UserOperations listener){
        this.listener = listener;
    }

    @Override
    protected String doInBackground(User... users) {
        try {
            MyApplication.getAppDatabase().userDao().insertAll(users);
        } catch(Exception e){
            return "error";
        }
        return "success";
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result){
        listener.insertUsers(result);
    }
}
