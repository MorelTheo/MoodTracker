package fr.theomorel.moodtracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    private RelativeLayout relativeLayout1;
    private RelativeLayout relativeLayout2;
    private RelativeLayout relativeLayout3;
    private RelativeLayout relativeLayout4;
    private RelativeLayout relativeLayout5;
    private RelativeLayout relativeLayout6;
    private RelativeLayout relativeLayout7;
    private Mood currentMood;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hystory);

        this.relativeLayout1= findViewById(R.id.relative_layout1);
        this.relativeLayout2= findViewById(R.id.relative_layout2);
        this.relativeLayout3= findViewById(R.id.relative_layout3);
        this.relativeLayout4= findViewById(R.id.relative_layout4);
        this.relativeLayout5= findViewById(R.id.relative_layout5);
        this.relativeLayout6= findViewById(R.id.relative_layout6);
        this.relativeLayout7= findViewById(R.id.relative_layout7);

        List<Mood> historyList = new ArrayList<>();
        historyList.add(new Mood(3,new Date(),"jhvkhv"));
        historyList.add(new Mood(4,new Date(),""));
        historyList.add(new Mood(1,new Date(),"jhsdova"));
        historyList.add(new Mood(2,new Date(),"l,sjfb"));
        historyList.add(new Mood(4,new Date(),"iqhfabc"));
        historyList.add(new Mood(5,new Date(),"SJBFKHAVE"));
        historyList.add(new Mood(2,new Date(),""));

        relativeLayout1.setBackgroundColor(getResources().getColor(R.color.veryHappyColor));


    }
}
