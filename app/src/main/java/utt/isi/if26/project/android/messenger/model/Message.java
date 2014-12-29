package utt.isi.if26.project.android.messenger.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jean-michelly on 26/12/2014.
 */
public class Message {

    private String contenu;
    private String date;
    private boolean sent;
    private int author;

    public Message (String contenu, String date, boolean sent) {
        this.contenu = contenu;
        this.date = date;
        this.sent = sent;
    }

    public Message (String contenu, String date, boolean sent, int author) {
        this.contenu = contenu;
        this.date = date;
        this.sent = sent;
        this.author = author;
    }

    public String booleanToString () {
        return sent ? "Envoyé":"Non envoyé";
    }

    public String getContenu() {
        return contenu;
    }

    public String getDate() {
        return date;
    }

    public boolean isSent() {
        return sent;
    }

    public int getAuthor() {
        return author;
    }

    public String messageView () {
        Matcher m = Pattern.compile("[0-9]{2}:[0-9]{2}:[0-9]{2}").matcher(date);

        return date+"  "+booleanToString()+"\n"+contenu;
    }

}
