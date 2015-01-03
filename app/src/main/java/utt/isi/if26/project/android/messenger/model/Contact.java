package utt.isi.if26.project.android.messenger.model;

import java.util.ArrayList;

/**
 * Created by jean-michelly on 26/12/2014.
 */
public class Contact {

    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private ArrayList <Message> messages;

    public Contact () {
        messages = new ArrayList<Message>();
    }

    public Contact (int id, String email, String firstName, String lastName) {
        this.id = id;
        this.setEmail(email);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        messages = new ArrayList<Message>();
    }

    public Contact (int id, String email, String firstName, String lastName, Message m) {
        this.id = id;
        this.setEmail(email);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        messages = new ArrayList<Message>();
        addMessage(m);
    }

    public boolean addMessage(Message m) {
        return messages.add(m);
    }

    public Message getLastMessage () {
        return messages.get(0);
    }

    public boolean hasMessage () { return !messages.isEmpty(); }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
