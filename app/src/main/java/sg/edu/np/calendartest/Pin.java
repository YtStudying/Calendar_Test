package sg.edu.np.calendartest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Pin extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    CustomCalendarView customCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);

        //Finding the navigation bar through its ID
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Setting the Selected Item ID as Pin because it is on the Pin page
        bottomNavigationView.setSelectedItemId(R.id.Pin);

        //The action where it determines which icon on the navigation bar is being clicked
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    //If the calender icon being clicked,
                    // it will start the activity in the CalenderActivity class
                    case R.id.Calender:
                        startActivity(new Intent(getApplicationContext(),CalendarActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    //If the Notes icon is being clicked,
                    // it will start the activity in the Notes class
                    case R.id.Notes:
                        startActivity(new Intent(getApplicationContext(),note_activity.class));
                        overridePendingTransition(0,0);
                        return true;

                    //If the Pin icon is being clicked,it will not have any changes
                    // as it is already on the Pin page
                    case R.id.Pin:
                        return true;

                    //If the Info icon is being clicked,
                    // it will start the activity in the Info class
                    case R.id.Info:
                        startActivity(new Intent(getApplicationContext(),Info.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                //otherwise it will return false
                //however it will not happen as all the icons
                //will lead to a page
                return false;
            }
        });

        customCalendarView = findViewById(R.id.custom_calendar_view);

    }
}