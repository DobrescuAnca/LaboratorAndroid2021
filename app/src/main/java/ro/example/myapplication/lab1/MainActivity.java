package ro.example.myapplication.lab1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import ro.example.myapplication.R;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "MESSAGE";
    private TextView textView;
    private Button button;
    private AppCompatEditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        editText = findViewById(R.id.editText);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = "";
                if(editText.getText() != null)
                    text = editText.getText().toString();

                if(text.isEmpty())
                    showErrorPopup();
                else
                  gotoSecondApp(text);
            }
        });
    }

    private void gotoSecondApp(String extra){
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(EXTRA_MESSAGE, extra);
        startActivity(intent);
    }
    private void gotoSecondApp2(String extra){
        Intent intent = new Intent(this, SecondActivity.class);

        Bundle mBundle = new Bundle();
        mBundle.putString(EXTRA_MESSAGE, extra);
        intent.putExtras(mBundle);
    }

    private void showErrorToast(){
        Toast.makeText(this, getString(R.string.error_msg), Toast.LENGTH_LONG).show();
    }

    private void showErrorPopup(){
        new AlertDialog.Builder(this)
            .setTitle("Error")
            .setMessage(R.string.error_msg)

            // Specifying a listener allows you to take an action before dismissing the dialog.
            // The dialog is automatically dismissed when a dialog button is clicked.
            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            })

            // A null listener allows the button to dismiss the dialog and take no further action.
            // .setNegativeButton(android.R.string.no, null)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show();
    }

}