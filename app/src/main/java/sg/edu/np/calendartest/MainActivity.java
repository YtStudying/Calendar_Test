package sg.edu.np.calendartest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText emailId, password;
    Button bt_login;
    TextView noAcc, forgetPass;
    DatabaseHelper db;

    CustomCalendarView customCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        db = new DatabaseHelper(this);
        emailId = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        bt_login = (Button)findViewById(R.id.btn_login);
        noAcc = (TextView)findViewById(R.id.noAcc);
        forgetPass = (TextView)findViewById(R.id.forgot_password);
        noAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(MainActivity.this, Register.class);
                startActivity(registerIntent);
            }
        });

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = emailId.getText().toString().trim();
                String pwd = password.getText().toString().trim();
                Boolean res = db.checkUser(user, pwd);
                if(res == true)
                {
                    Intent successLogin = new Intent(MainActivity.this, CalendarActivity.class);
                    startActivity(successLogin);
                }
                else {
                    Toast.makeText(MainActivity.this,"Login Error",Toast.LENGTH_SHORT).show();
                }
            }
        });

        customCalendarView = (CustomCalendarView) findViewById(R.id.custom_calendar_view);
    }
}
