package fr.theomorel.moodtracker;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fr.theomorel.moodtracker.tools.Tools;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, "ALARM", Toast.LENGTH_LONG).show();
        Mood moodDay = Tools.loadData(context);

        if(moodDay != null) {
            ArrayList<Mood> historyList = Tools.loadHistory(context);
            if(historyList==null){
                historyList = new ArrayList<>();
            }
            historyList.add(moodDay);
            Tools.saveHistory(historyList, context);
        }

    }}

