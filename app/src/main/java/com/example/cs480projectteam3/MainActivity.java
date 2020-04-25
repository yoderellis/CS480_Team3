package com.example.cs480projectteam3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener, TextToSpeech.OnInitListener {

    // entry fields
    private EditText courseEntry;
    private EditText dayTimeEntry;
    private EditText classroomEntry;

    // animated logos
    private ImageView logo1;
    private ImageView logo2;

    // course ListView
    private ListView courseList;
    private ArrayList<String> courses = new ArrayList<>();
    private ArrayAdapter<String> courseAdapter = null;

    // day/time ListView
    private ListView dayTimeList;
    private ArrayList<String> dayTimes = new ArrayList<>();
    private ArrayAdapter<String> dayTimeAdapter = null;

    // classroom ListView
    private ListView classroomList;
    private ArrayList<String> classrooms = new ArrayList<>();
    private ArrayAdapter<String> classroomAdapter = null;

    // position of clicked item
    private int position;

    // text-to-speech object
    private TextToSpeech speaker;

    // navigation buttons
    private Button webButton;
    private Button mapsButton;
    private Button contactsButton;

    // notification variables
    private NotificationManager NotificationManager;
    private NotificationCompat.Builder builder = null;
    private int SIMPLE_NOTIFICATION_ID = 25;
    private String CHANNEL_ID = "11";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // assign widgets
        courseEntry = findViewById(R.id.course_input);
        dayTimeEntry = findViewById(R.id.day_time_input);
        classroomEntry = findViewById(R.id.classroom_input);
        logo1 = findViewById(R.id.image1);
        logo1.setImageResource(R.drawable.logo);
        logo2 = findViewById(R.id.image2);
        logo2.setImageResource(R.drawable.logo);
        courseList = findViewById(R.id.course_list);
        dayTimeList = findViewById(R.id.day_time_list);
        classroomList = findViewById(R.id.classroom_list);
        webButton = findViewById(R.id.web_button);
        mapsButton = findViewById(R.id.maps_button);
        contactsButton = findViewById(R.id.contacts_button);

        // load animation
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.fancylogo);

        // register animation listener
        anim.setAnimationListener(new MyAnimationListener());

        // start animation
        logo1.startAnimation(anim);
        logo2.startAnimation(anim);

        // set ListView OnItemClickListeners
        courseList.setOnItemClickListener(this);
        dayTimeList.setOnItemClickListener(this);
        classroomList.setOnItemClickListener(this);

        // assign adapters to ListViews
        courseAdapter = new ArrayAdapter<>(this, R.layout.list_item, courses);
        courseList.setAdapter(courseAdapter);
        dayTimeAdapter = new ArrayAdapter<>(MainActivity.this, R.layout.list_item, dayTimes);
        dayTimeList.setAdapter(dayTimeAdapter);
        classroomAdapter = new ArrayAdapter<>(MainActivity.this, R.layout.list_item, classrooms);
        classroomList.setAdapter(classroomAdapter);

        // initialize TextToSpeech widget
        speaker = new TextToSpeech(this, this);

        // assign listeners to Buttons
        webButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent web = new Intent(getApplicationContext(), web.class);
                startActivity(web);
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

        // create notification manager
        NotificationManager =
                (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        // create notification channel
        createNotificationChannel();

        // explicit intent for notification
        Intent intent = new Intent(this, Contacts.class);

        // pending intent for notification
        PendingIntent pendingIntent = PendingIntent
                .getActivity(this, 9, intent, 0);

        // build notification
        // notification will enable user to tap to see contact list
        builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.academic)
                .setContentTitle("NEED MORE RESOURCES?")
                .setContentText("Tap here to see contact list!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
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
        switch (item.getItemId()) {
            // what happens if you click "add"
            case R.id.add:
                // add info to lists
                courses.add(courseEntry.getText().toString());
                dayTimes.add(dayTimeEntry.getText().toString());
                classrooms.add(classroomEntry.getText().toString());
                // speak info
                speak(courseEntry.getText().toString() + " on "
                        + dayTimeEntry.getText().toString() + " in "
                        + classroomEntry.getText().toString() + " added.");
                // make EditTexts blank
                courseEntry.setText("");
                dayTimeEntry.setText("");
                classroomEntry.setText("");
                // notify adapters
                courseAdapter.notifyDataSetChanged();
                dayTimeAdapter.notifyDataSetChanged();
                classroomAdapter.notifyDataSetChanged();
                // send notification when "ADD" is tapped
                NotificationManager.notify(SIMPLE_NOTIFICATION_ID, builder.build());
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

            default:
                super.onOptionsItemSelected(item);
        }

        return false;
    }

    // create notification channel
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Main";
            String description = "The only notification channel";
            int importance = android.app.NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    // shut down text to speech
    public void onDestroy() {
        if (speaker != null) {
            speaker.stop();
            speaker.shutdown();
        }
        super.onDestroy();
    }

    // create custom animation listener
    public class MyAnimationListener implements Animation.AnimationListener {
        public void onAnimationEnd(Animation animation) {
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
        }
    }

}
