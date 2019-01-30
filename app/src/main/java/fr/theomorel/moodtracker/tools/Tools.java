package fr.theomorel.moodtracker.tools;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;

import fr.theomorel.moodtracker.Mood;

public class Tools {

    public static void saveData(Mood mood, Context context){

        Gson gson = new Gson();

        String moodToJson = gson.toJson(mood);

        SharedPreferences moodPreferences = context.getSharedPreferences(Constantes.keyTableau, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = moodPreferences.edit();
        editor.putString(Constantes.keyMood, moodToJson);
        editor.commit();

    }

    public static Mood loadData(Context context) {
        Gson gson = new Gson();
        Mood mood;

        SharedPreferences moodPreferences = context.getSharedPreferences(Constantes.keyTableau, context.MODE_PRIVATE);
        String moodString = moodPreferences.getString(Constantes.keyMood, "");
        if(!moodString.equals("")){
            mood = gson.fromJson(moodString, Mood.class);
        }
        else{
            mood = null;
        }

        return mood ;

    }

    public static void saveHistory(ArrayList historyList, Context context){

        Gson gson = new Gson();

        String moodToJson = gson.toJson(historyList);

        SharedPreferences listPreferences = context.getSharedPreferences(Constantes.keyTableau, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = listPreferences.edit();
        editor.putString(Constantes.keyHistory, moodToJson);
        editor.commit();

    }

    public static ArrayList loadHistory(Context context) {

        Gson gson = new Gson();
        ArrayList historyList;


        SharedPreferences listPreferences = context.getSharedPreferences(Constantes.keyTableau, context.MODE_PRIVATE);
        String moodString = listPreferences.getString(Constantes.keyHistory, "");
        if( !moodString.equals("")){
            historyList = gson.fromJson(moodString, ArrayList.class);
        }
        else {
            historyList = new ArrayList();
        }

        return historyList ;

    }
}
