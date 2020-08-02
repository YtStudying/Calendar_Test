package sg.edu.np.calendartest;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        final Button AboutUs;

        final Button HowToUse;

        BottomNavigationView bottomNavigationView;

        //Finding the navigation bar through its ID
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Setting the Selected Item ID as Settings because it is on the Pin page
        bottomNavigationView.setSelectedItemId(R.id.Info);

        AboutUs = findViewById(R.id.aboutus);

        HowToUse = findViewById(R.id.howtouse);

        AboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AboutUs.class));
            }
        });

        HowToUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),HowToUse.class));
            }
        });

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

                    //If the Calculator icon is being clicked,
                    // it will start the activity in the Calculator class
                    case R.id.Calculator:
                        startActivity(new Intent(getApplicationContext(),Calculator.class));
                        overridePendingTransition(0,0);
                        return true;

                    //If the Info icon is being clicked, it will not have any changes
                    // as it is already on the Info Page
                    case R.id.Info:
                        return true;
                }
                //otherwise it will return false
                //however it will not happen as all the icons
                //will lead to a page
                return false;
            }
        });

    }

    @Override
    public void finish() {
        super.finish();
    }
}