package fr.theomorel.moodtracker;

import android.app.AlarmManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import fr.theomorel.moodtracker.tools.Tools;

public class History extends AppCompatActivity {

    private ImageView returnButton;
    private int hourOfDay;
    private int minute;
    private ArrayList historyList;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        this.returnButton=findViewById(R.id.return_button);
        this.context=getApplicationContext();


        List<Mood> moodHistory = Tools.loadHistory(context);

        ListView historyListView = findViewById(R.id.history_list_view);
        historyListView.setAdapter(new MoodAdapter(this, moodHistory));

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainActivity);
                finish();
            }
        });


    }
}
