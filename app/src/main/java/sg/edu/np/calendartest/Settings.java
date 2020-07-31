package sg.edu.np.calendartest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Settings extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.Settings);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.Calender:
                        startActivity(new Intent(getApplicationContext(),CalendarActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.Notes:
                        startActivity(new Intent(getApplicationContext(),Notes.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.Pin:
                        startActivity(new Intent(getApplicationContext(),Pin.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.Settings:
                        return true;
                }
                return false;
            }
        });
    }
}