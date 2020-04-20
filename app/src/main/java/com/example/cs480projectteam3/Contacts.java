package com.example.cs480projectteam3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class Contacts extends AppCompatActivity {

    private ListView contactList;
    private ArrayList<HashMap<String, String>> contacts = new ArrayList<>();

    private final String[] departments = {"Academic Services (Undergrad)", "Academic Services (Grad)",
            "Accountancy", "Career Services (Undergrad)", "Career Services (Grad)",
            "Center for International Students and Scholars", "Center for Languages and International Collaboration",
            "Center for Marketing Technology", "CIS Sandbox", "Computer Information Systems",
            "Counseling Center", "Cybersecurity", "Disability Services", "Eco-Fi Stat Learning Center",
            "Economics", "English and Media Studies", "ESOL Center", "Facilities Management",
            "Finance", "Financial Assistance", "Global Studies", "Health Center", "History",
            "ACELAB", "Trading Room", "Information and Process Management",
            "Information Design and Corporate Communication", "Information Technology",
            "International Education", "Valente Center", "Law, Taxation and Financial Planning",
            "Mail Services", "Management", "Marketing", "Mathematical Sciences",
            "Mathematics Learning Center", "Media and Culture Lab", "Modern Languages",
            "Natural and Applied Sciences", "Philosophy", "Registrar", "Service-Learning Center",
            "Sociology", "Student Employment", "Student Financial Services", "Sustainability",
            "University Police (Business)", "University Police (Emergency)",
            "W. Michael Hoffman Center for Business Ethics", "Writing Center"};

    private final String[] phones = {"781-891-2803", "781-891-2348", "781-891-2525", "781-891-2165",
            "", "781-891-2829", "781-891-3454", "781-891-3115", "781-891-3491", "781-891-2852",
            "781-891-3122", "781-891-2274", "781-891-2004", "781-891-2413", "781-891-3483",
            "781-891-2944", "781-891-2944", "781-891-2021", "781-891-2208", "781-891-2781",
            "781-891-3441", "781-891-2865", "781-891-2424", "781-891-2222", "781-891-2919",
            "781-891-3442", "781-891-3492", "781-891-2932", "781-891-3122", "781-891-3474",
            "781-891-3117", "781-891-2202", "781-891-2212", "781-891-2530", "781-891-3149",
            "781-891-2467", "781-891-3180", "781-891-2902", "781-891-3187", "781-891-2901",
            "781-891-2085", "781-891-2177", "781-891-2170", "781-891-2698", "781-891-3441",
            "781-891-2162", "781-891-2252", "781-891-2201", "781-891-3131", "781-891-2981",
            "781-891-3173"};

    private final String[] emails = {"GA_Academic_Services@bentley.edu", "gradvising@bentley.edu",
            "GA_accountancydept@bentley.edu", "GA_UCS@bentley.edu", "GCS@bentley.edu",
            "GA_CISS@bentley.edu", "adalsant@bentley.edu", "GA_CMT@bentley.edu",
            "GA_cis_sandbox@bentley.edu", "htopi@bentley.edu", "Counseling_GA@bentley.edu",
            "cybersecurity@bentley.edu", "sbrodeur@bently.edu", "ebikhazi@bentley.edu",
            "GA_economics@bentley.edu", "baslinger@bentley.edu", "ESOLCenter_GA@bentley.edu",
            "workorders@bentley.edu", "kraman@bentley.edu", "finaid@bentley.edu",
            "Global_Studies_GA@bentley.edu", "", "bandrews@bentley.edu", "ACELAB_GA@bentley.edu",
            "Tradingroom_GA@bentley.edu", "htopi@bentley.edu", "rhubscher@bentley.edu",
            "GA_helpdesk@bentley.edu", "study_abroad@bentley.edu", "Valente_Center_GA@bentley,edu",
            "malexander@bentley.edu", "GA_mailstop@bentley.edu", "ledelman@bentley.edu",
            "aaylesworth@bentley.edu", "MathSciences_GA@bentley.edu", "zmaar@bentley.edu", "",
            "crubio@bentley.edu", "dsymanski@bentley.edu", "jmoriarty@bentley.edu",
            "registrar@bentley.edu", "ServiceLearning_GA@bentley.edu", "gdavid@bentley,edu",
            "Student_Employment_GA@bentley.edu", "GA_SFS@bentley.edu",
            "GA_Bentley_Sustainability@bentley.edu", "ga_universitypolice@bentley.edu", "",
            "cbeinfo@bentley.edu", "gfarber@bentley.edu"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        // assign widgets
        contactList = (ListView) findViewById(R.id.contact_list);

        // Populate contactList
        for (int i=0; i<departments.length; i++) {
            HashMap<String, String> hash = new HashMap<>();
            hash.put("dept", departments[i]);
            hash.put("phone", phones[i]);
            hash.put("email", emails[i]);
            contacts.add(hash);
        }

        // Assign adapter to contactList
        ListViewAdapter contAdapter = new ListViewAdapter(this, contacts);
        contactList.setAdapter(contAdapter);

    }

}