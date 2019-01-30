package fr.theomorel.moodtracker;

import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Date;

public class Mood {

    private int humeur;
    private Date dateHumeur;
    private String commentaire;
    private int dateHumeurTest;
    private ArrayList historySave;

    public Mood (int humeur, Date dateHumeur, String commentaire){
        this.humeur=humeur;
        this.dateHumeurTest=dateHumeurTest;
        this.commentaire=commentaire;
    }

    public int getHumeur() {
        return humeur;
    }

    public void setHumeur(int humeur) {
        this.humeur = humeur;
    }

    public Date getDateHumeur() {
        return dateHumeur;
    }

    public void setDateHumeur(Date dateHumeur) {
        this.dateHumeur = dateHumeur;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getDateHumeurTest() {
        return dateHumeurTest;
    }

    public void setDateHumeurTest(int dateHumeurTest) {
        this.dateHumeurTest = dateHumeurTest;
    }

    public ArrayList getHistorySave() {
        return historySave;
    }

    public void setHistorySave(ArrayList historySave) {
        this.historySave = historySave;
    }
}
