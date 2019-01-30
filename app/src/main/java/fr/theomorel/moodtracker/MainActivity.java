package fr.theomorel.moodtracker;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.text.Layout;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import android.widget.Toast;

import com.google.gson.Gson;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


import fr.theomorel.moodtracker.tools.Constantes;


public class MainActivity extends AppCompatActivity {

    private GestureDetectorCompat gestureObject;
    private RelativeLayout mainLayout;
    private ImageView smileyImage;
    private ImageView histotyIcon;
    private ImageView dialogIcon;
    private Button saveButton;
    private int position;
    private MainActivity mainActivity;
    private EditText input;
    private MediaPlayer noteDo;
    private MediaPlayer noteRe;
    private MediaPlayer noteMi;
    private MediaPlayer noteFa;
    private MediaPlayer noteSol;
    private Mood moodEnCours;
    private ArrayList historyList;
    private int hourOfDay;
    private int minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mainActivity=this;
        this.noteDo=MediaPlayer.create(getApplicationContext(),R.raw.note_do1);
        this.noteRe=MediaPlayer.create(getApplicationContext(),R.raw.note_re1);
        this.noteMi=MediaPlayer.create(getApplicationContext(),R.raw.note_mi1);
        this.noteFa=MediaPlayer.create(getApplicationContext(),R.raw.note_fa1);
        this.noteSol=MediaPlayer.create(getApplicationContext(),R.raw.note_sol1);



        gestureObject = new GestureDetectorCompat(this,new LearnGesture());
        mainLayout = findViewById(R.id.mainLayout);
        smileyImage = findViewById(R.id.image_smiley);
        histotyIcon = findViewById(R.id.main_history_icon);
        histotyIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent historyActivity = new Intent(getApplicationContext(),History.class);
                startActivity(historyActivity);
                finish();
            }
        });
        dialogIcon = findViewById(R.id.main_dialog_icon);
        dialogIcon.setOnClickListener(new View.OnClickListener() {

           // Edit text for mood's comments of users
            @Override
            public void onClick(View v) {

                input = new EditText(mainActivity);
                input.setHint("Votre commentaire.");
                AlertDialog.Builder dialog = new AlertDialog.Builder(mainActivity);
                dialog.setTitle("Commentaire");
                dialog.setView(input);
                dialog.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Commentaire enregistré",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        String comment = input.getText().toString();
                        saveData(new Mood(position,new Date(),comment));
                    }
                });
                dialog.setCancelable(true);
                dialog.show();
            }
        });

        moodEnCours = loadData();
        if (moodEnCours!=null) {
            position = moodEnCours.getHumeur();
            switchMood(moodEnCours.getHumeur());
        }
        else {
            position = 4;
        }

        AlarmMidnight();



    }

    public void AlarmMidnight() {



        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Date dat = new Date();

        Calendar calendar = Calendar.getInstance();

        Calendar cal_now = Calendar.getInstance();

        cal_now.setTime(dat);



        calendar.setTime(dat);

        calendar.set(Calendar.HOUR_OF_DAY,19);

        calendar.set(Calendar.MINUTE,11);

        calendar.set(Calendar.SECOND, 0);



        if(calendar.before(cal_now)){

            calendar.add(Calendar.DATE,1);

        }



        Intent myIntent = new Intent(MainActivity.this, AlarmReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, myIntent, 0);



        if (Build.VERSION.SDK_INT > 19) {

            if (manager != null) {

                manager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis() + 300, pendingIntent);

            }
        }



        else {

            if (manager != null) {

                manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis() + 300, pendingIntent);

            }
        }

    }


    @Override
    protected void onStop() {
        super.onStop();
        saveData(new Mood(position, new Date(),""));
        Toast.makeText(getApplicationContext(),"onStop fonctionne", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadData();
        Toast.makeText(getApplicationContext(),"onStart fonctionne", Toast.LENGTH_LONG).show();
    }

    // Change mood with gesture and implement a music note
    @Override
    public boolean onTouchEvent (MotionEvent event){
        this.gestureObject.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class LearnGesture extends GestureDetector.SimpleOnGestureListener{

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY){

            if (event2.getY() > event1.getY() && position!=5){

                position++;

                switch (position){
                    case 1:
                        changeMood(R.drawable.smiley_1,getResources().getColor(R.color.sadColor));
                        noteDo.start();
                        break;

                    case 2 :
                        changeMood(R.drawable.smiley_2,getResources().getColor(R.color.confusedColor));
                        noteRe.start();
                        break;

                    case 3 :
                        changeMood(R.drawable.smiley_3,getResources().getColor(R.color.neutralColor));
                        noteMi.start();
                        break;

                    case 4 :
                        changeMood(R.drawable.smiley_4,getResources().getColor(R.color.happyColor));
                        noteFa.start();
                        break;

                    case 5 :
                        changeMood(R.drawable.smiley_5,getResources().getColor(R.color.veryHappyColor));
                        noteSol.start();

                }

            }
            else if (event1.getY() > event2.getY() && position!=1){
                position--;

                switch (position){
                    case 1:
                        changeMood(R.drawable.smiley_1,getResources().getColor(R.color.sadColor));
                        noteDo.start();
                        break;

                    case 2 :
                        changeMood(R.drawable.smiley_2,getResources().getColor(R.color.confusedColor));
                        noteRe.start();
                        break;

                    case 3 :
                        changeMood(R.drawable.smiley_3,getResources().getColor(R.color.neutralColor));
                        noteMi.start();
                        break;

                    case 4 :
                        changeMood(R.drawable.smiley_4,getResources().getColor(R.color.happyColor));
                        noteFa.start();
                        break;

                    case 5 :
                        changeMood(R.drawable.smiley_5,getResources().getColor(R.color.veryHappyColor));
                        noteSol.start();
                }
            }
            return true;

        }
    }

    public void switchMood (int position) {

        switch (position){
            case 1:
                changeMood(R.drawable.smiley_1,getResources().getColor(R.color.sadColor));
                noteDo.start();
                break;

            case 2 :
                changeMood(R.drawable.smiley_2,getResources().getColor(R.color.confusedColor));
                noteRe.start();
                break;

            case 3 :
                changeMood(R.drawable.smiley_3,getResources().getColor(R.color.neutralColor));
                noteMi.start();
                break;

            case 4 :
                changeMood(R.drawable.smiley_4,getResources().getColor(R.color.happyColor));
                noteFa.start();
                break;

            case 5 :
                changeMood(R.drawable.smiley_5,getResources().getColor(R.color.veryHappyColor));
                noteSol.start();

        }

    }

    // To set new mood with LearnGesture
    void changeMood(int idImage,int color){
        smileyImage.setImageResource(idImage);
        mainLayout.setBackgroundColor(color);
    }


    //To save and load moods of te day (convert wit Json and Gson)

    private void saveData(Mood mood){

        Gson gson = new Gson();

        String moodToJson = gson.toJson(mood);

        SharedPreferences moodPreferences = getSharedPreferences(Constantes.keyTableau, MODE_PRIVATE);
        SharedPreferences.Editor editor = moodPreferences.edit();
        editor.putString(Constantes.keyMood, moodToJson);
        editor.commit();

    }

    public Mood loadData() {
        Gson gson = new Gson();

        SharedPreferences moodPreferences = getSharedPreferences(Constantes.keyTableau, MODE_PRIVATE);
        String moodString = moodPreferences.getString(Constantes.keyMood, "");
        Mood mood = gson.fromJson(moodString, Mood.class);

        return mood ;

    }




}
