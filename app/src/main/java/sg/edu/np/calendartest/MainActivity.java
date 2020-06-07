package sg.edu.np.calendartest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText emailId, password;
    Button bt_login;
    ToggleButton bt_view;
    TextView noAcc;
    DatabaseHelper db;

    CustomCalendarView customCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        db = new DatabaseHelper(this);
        bt_view = (ToggleButton)findViewById(R.id.btn_view);
        emailId = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        bt_login = (Button)findViewById(R.id.btn_login);
        noAcc = (TextView)findViewById(R.id.noAcc);

        bt_view.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

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
