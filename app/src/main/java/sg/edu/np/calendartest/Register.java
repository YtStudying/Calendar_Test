package sg.edu.np.calendartest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText emailId_sign, password_sign, cfmPassword_sign;
    Button signUp;
    TextView haveAcc;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_create);

        db = new DatabaseHelper(this);
        emailId_sign = findViewById(R.id.username_sign);
        password_sign = findViewById(R.id.password_sign);
        cfmPassword_sign = findViewById(R.id.cfmPassword_sign);
        signUp = findViewById(R.id.btn_signUp);
        haveAcc = findViewById(R.id.haveAcc);
        haveAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = new Intent(Register.this, MainActivity.class);
                startActivity(signInIntent);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = emailId_sign.getText().toString().trim();
                String pwd = password_sign.getText().toString().trim();
                String cfm_pwd = cfmPassword_sign.getText().toString().trim();
                if (pwd.equals(cfm_pwd)) {
                    long val = db.addUser(user,pwd);
                    if(val > 0) {
                        Toast.makeText(Register.this,"You have registered",Toast.LENGTH_SHORT).show();
                        Intent moveToLogin = new Intent (Register.this,MainActivity.class);
                        startActivity(moveToLogin);
                        finish();
                    }
                }

                else{
                    Toast.makeText(Register.this,"Registration error",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}