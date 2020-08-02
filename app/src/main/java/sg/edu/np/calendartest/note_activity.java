package sg.edu.np.calendartest;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class note_activity extends AppCompatActivity{
    Toolbar toolbar;
    RecyclerView recyclerView;
    Adapter adapter;
    TextView noItemText;
    SimpleDatabase simpleDatabase;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        //Finding the navigation bar through its ID
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Setting the Selected Item ID as Notes because it is on the notes page
        bottomNavigationView.setSelectedItemId(R.id.Notes);

        //The action where it determines which icon on the navigation bar is being clicked
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    //If it is the calender icon being clicked,
                    // it will start the activity in the CalenderActivity class
                    case R.id.Calender:
                        startActivity(new Intent(getApplicationContext(),CalendarActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    //If the Notes icon is being clicked, it will not have any changes
                    // as it is already on the Notes page
                    case R.id.Notes:
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


        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        noItemText = findViewById(R.id.noItemText);
        simpleDatabase = new SimpleDatabase(this);
        List<Note> allNotes = simpleDatabase.getAllNotes();
        recyclerView = findViewById(R.id.allNotesList);

        if(allNotes.isEmpty()){
            noItemText.setVisibility(View.VISIBLE);
        }else {
            noItemText.setVisibility(View.GONE);
            displayList(allNotes);
        }

    }

    private void displayList(List<Note> allNotes) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this,allNotes);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.add){
            Toast.makeText(this, "Add New Note", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this,AddNote.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Note> getAllNotes = simpleDatabase.getAllNotes();
        if(getAllNotes.isEmpty()){
            noItemText.setVisibility(View.VISIBLE);
        }else {
            noItemText.setVisibility(View.GONE);
            displayList(getAllNotes);
        }


    }

}


