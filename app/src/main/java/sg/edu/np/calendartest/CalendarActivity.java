package sg.edu.np.calendartest;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CalendarActivity extends AppCompatActivity {

    CustomCalendarView customCalendarView;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customCalendarView = findViewById(R.id.custom_calendar_view);
        //Finding the navigation bar through its ID
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Setting the Selected Item ID as Calender because it is on the calender page
        bottomNavigationView.setSelectedItemId(R.id.Calender);

        //The action where it determines which icon on the navigation bar is being clicked
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    //If the calender icon being clicked, it will not have any changes
                    // as it is already on the calender page
                    case R.id.Calender:
                        return true;

                    //If the Notes icon is being clicked,
                    // it will start the activity in the Notes class
                    case R.id.Notes:
                        startActivity(new Intent(getApplicationContext(),note_activity.class));
                        overridePendingTransition(0,0);
                        return true;

                    //If the Pin icon is being clicked,
                    // it will start the activity in the Pin class
                    case R.id.Pin:
                        startActivity(new Intent(getApplicationContext(),Calculator.class));
                        overridePendingTransition(0,0);
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
    }
}