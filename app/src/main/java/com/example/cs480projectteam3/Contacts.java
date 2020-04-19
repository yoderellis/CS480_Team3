package com.example.cs480projectteam3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Contacts extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView deptList;
    private final String[] departments = {"Academic Services (Undergrad)", "Academic Services (Grad)",
            "Accountancy", "Career Services (Undergrad)", "Career Services (Grad)" };
    private ArrayAdapter<String> deptAdapter;

    private ListView phoneList;
    private final String[] phones = {"781-891-2803", "781-891-2348", "781-891-2525", "781-891-2165", ""};
    private ArrayAdapter<String> phoneAdapter;

    private ListView emailList;
    private final String[] emails = {"GA_Academic_Services@bentley.edu", "gradvising@bentley.edu",
            "GA_accountancydept@bentley.edu", "GA_UCS@bentley.edu", "GCS@bentley.edu"};
    private ArrayAdapter<String> emailAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        // assign widgets
        deptList = findViewById(R.id.dept_list);
        phoneList = findViewById(R.id.phone_list);
        emailList = findViewById(R.id.email_list);

        // set ListView OnItemClickListeners
        deptList.setOnItemClickListener(this);
        phoneList.setOnItemClickListener(this);
        emailList.setOnItemClickListener(this);

        // assign adapters to ListViews
        deptAdapter = new ArrayAdapter<> (this, R.layout.contact_list, departments);
        deptList.setAdapter(deptAdapter);
        phoneAdapter = new ArrayAdapter<> (Contacts.this, R.layout.contact_list, phones);
        phoneList.setAdapter(phoneAdapter);
        emailAdapter = new ArrayAdapter<> (Contacts.this, R.layout.contact_list, emails);
        emailList.setAdapter(emailAdapter);

    }

    // Dial phone number if phone number is clicked
    // New email message if email is clicked
    public void onItemClick(AdapterView<?> av, View v, int pos, long id) {

        if (av.equals(phoneList)) {
            Uri tel = Uri.parse("tel:" + phones[pos]);
            Intent dial = new Intent(Intent.ACTION_DIAL, tel);
            startActivity(dial);
        } else if (av.equals(emailList)) {
            Uri email = Uri.parse("mailto:" + emails[pos]);
            Intent send = new Intent(Intent.ACTION_SENDTO, email);
            startActivity(send);
        }
    }
}