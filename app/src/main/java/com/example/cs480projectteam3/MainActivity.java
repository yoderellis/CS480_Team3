package com.example.cs480projectteam3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
    implements AdapterView.OnItemClickListener, TextToSpeech.OnInitListener{

    private EditText courseEntry;
    private EditText dayTimeEntry;
    private EditText classroomEntry;

    private ListView courseList;
    private ArrayList<String> courses = new ArrayList<>();
    private ArrayAdapter<String> courseAdapter = null;

    private ListView dayTimeList;
    private ArrayList<String> dayTimes = new ArrayList<>();
    private ArrayAdapter<String> dayTimeAdapter = null;

    private ListView classroomList;
    private ArrayList<String> classrooms = new ArrayList<>();
    private ArrayAdapter<String> classroomAdapter = null;

    private int position;
    private TextToSpeech speaker;
    private Button webButton;
    private Button mapsButton;
    private Button contactsButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // assign widgets
        courseEntry = findViewById(R.id.course_input);
        dayTimeEntry = findViewById(R.id.day_time_input);
        classroomEntry = findViewById(R.id.classroom_input);
        courseList = findViewById(R.id.course_list);
        dayTimeList = findViewById(R.id.day_time_list);
        classroomList = findViewById(R.id.classroom_list);
        webButton = findViewById(R.id.web_button);
        mapsButton = findViewById(R.id.maps_button);
        contactsButton = findViewById(R.id.contacts_button);

        // set ListView OnItemClickListeners
        courseList.setOnItemClickListener(this);
        dayTimeList.setOnItemClickListener(this);
        classroomList.setOnItemClickListener(this);

        // assign adapters to ListViews
        courseAdapter = new ArrayAdapter<> (this, R.layout.list_item, courses);
        courseList.setAdapter(courseAdapter);
        dayTimeAdapter = new ArrayAdapter<> (MainActivity.this, R.layout.list_item, dayTimes);
        dayTimeList.setAdapter(dayTimeAdapter);
        classroomAdapter = new ArrayAdapter<> (MainActivity.this, R.layout.list_item, classrooms);
        classroomList.setAdapter(classroomAdapter);

        // initialize TextToSpeech widget
        speaker = new TextToSpeech(this, this);

        // assign listeners to Buttons
        webButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // HERE IS WHERE INTENTS FOR OTHER CLASSES GO
            }
        });
        mapsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent map = new Intent(getApplicationContext(), CampusMap.class);
                startActivity(map);
            }
        });
        contactsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent contacts = new Intent(getApplicationContext(), Contacts.class);
                startActivity(contacts);
            }
        });
    }

    // set text to speech language to English
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS)
            speaker.setLanguage(Locale.US);
    }

    // text to speech speaks output
    public void speak(String output) {
        speaker.speak(output, TextToSpeech.QUEUE_FLUSH, null, "Id 0");
    }

    // what happens when a ListView item is clicked
    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
        courseEntry.setText(courseList.getItemAtPosition(pos).toString());
        dayTimeEntry.setText(dayTimeList.getItemAtPosition(pos).toString());
        classroomEntry.setText(classroomList.getItemAtPosition(pos).toString());
        position = pos;
    }

    // inflation of the Options Menu from the XML file
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return true;
    }

    // what happens if you click an Options Menu item
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            // what happens if you click "add"
            case R.id.add:
                courses.add(courseEntry.getText().toString());
                dayTimes.add(dayTimeEntry.getText().toString());
                classrooms.add(classroomEntry.getText().toString());
                speak(courseEntry.getText().toString() + " on "
                    + dayTimeEntry.getText().toString() + " in "
                    + classroomEntry.getText().toString() + " added.");
                courseEntry.setText("");
                dayTimeEntry.setText("");
                classroomEntry.setText("");
                courseAdapter.notifyDataSetChanged();
                dayTimeAdapter.notifyDataSetChanged();
                classroomAdapter.notifyDataSetChanged();
                return true;

            // what happens if you click "update"
            case R.id.update:
                courses.remove(position);
                dayTimes.remove(position);
                classrooms.remove(position);
                courses.add(courseEntry.getText().toString());
                dayTimes.add(dayTimeEntry.getText().toString());
                classrooms.add(classroomEntry.getText().toString());
                speak(courseEntry.getText().toString() + " on "
                    + dayTimeEntry.getText().toString() + " in "
                    + classroomEntry.getText().toString() + " updated.");
                courseEntry.setText("");
                dayTimeEntry.setText("");
                classroomEntry.setText("");
                courseAdapter.notifyDataSetChanged();
                dayTimeAdapter.notifyDataSetChanged();
                classroomAdapter.notifyDataSetChanged();
                return true;

            // what happens if you click "delete"
            case R.id.delete:
                speak(courses.get(position) + " deleted.");
                courses.remove(position);
                dayTimes.remove(position);
                classrooms.remove(position);
                courseEntry.setText("");
                dayTimeEntry.setText("");
                classroomEntry.setText("");
                courseAdapter.notifyDataSetChanged();
                dayTimeAdapter.notifyDataSetChanged();
                classroomAdapter.notifyDataSetChanged();
                return true;

            // what happens if you click "close"
            case R.id.close:
                finish();

            default: super.onOptionsItemSelected(item);
        }

        return false;
    }

    // shut down text to speech
    public void onDestroy() {
        if(speaker != null) {
            speaker.stop();
            speaker.shutdown();
        }
        super.onDestroy();
    }

}
