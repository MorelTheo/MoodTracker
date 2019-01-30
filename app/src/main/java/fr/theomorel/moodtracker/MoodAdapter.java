package fr.theomorel.moodtracker;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MoodAdapter extends BaseAdapter {

    private Context context;
    private List<Mood> moodHistory;
    private LayoutInflater inflater;

    public MoodAdapter (Context context, List<Mood> moodHistory) {
        this.context=context;
        this.moodHistory=moodHistory;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return moodHistory.size();
    }

    @Override
    public Mood getItem(int position) {
        return moodHistory.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = inflater.inflate(R.layout.adapter_history, null);

        Mood currentMood = getItem(position);
        int humeur = currentMood.getHumeur();
        int dateHumeurTest = currentMood.getDateHumeurTest();
        final String commentaire = currentMood.getCommentaire();

        TextView dateHumeurTestView = convertView.findViewById(R.id.date_text);
        dateHumeurTestView.setText("Il y a " + dateHumeurTest + " jours");

        if (commentaire.equals("")){
            ImageView commentaireIcon = convertView.findViewById(R.id.message_icon);
            commentaireIcon.setVisibility(View.INVISIBLE);
        }

        RelativeLayout changeBackground = convertView.findViewById(R.id.adapter_layout);
        switch (humeur){
            case 1:
                changeBackground.setBackgroundColor(context.getResources().getColor(R.color.sadColor));
                changeBackground.setLayoutParams(new RelativeLayout.LayoutParams(200,150));
                break;

            case 2 :
                changeBackground.setBackgroundColor(context.getResources().getColor(R.color.confusedColor));
                changeBackground.setLayoutParams(new RelativeLayout.LayoutParams(300,150));
                break;

            case 3 :
                changeBackground.setBackgroundColor(context.getResources().getColor(R.color.neutralColor));
                changeBackground.setLayoutParams(new RelativeLayout.LayoutParams(450,150));
                break;

            case 4 :
                changeBackground.setBackgroundColor(context.getResources().getColor(R.color.happyColor));
                changeBackground.setLayoutParams(new RelativeLayout.LayoutParams(600,150));
                break;

            case 5 :
                changeBackground.setBackgroundColor(context.getResources().getColor(R.color.veryHappyColor));


        }

        ImageView messageIcon = convertView.findViewById(R.id.message_icon);
        messageIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, commentaire, Toast.LENGTH_SHORT).show();
            }
        });


        return convertView;
    }

}
