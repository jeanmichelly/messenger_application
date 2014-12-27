package utt.isi.if26.project.android.messenger.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jean-michelly on 26/12/2014.
 */
public class User extends Contact {

    private static User uniqueInstance = null;
    static private Map<String, Contact> listContacts;
    static private ArrayList<Message> conversation;

    public User () {
        listContacts = new HashMap<String, Contact>();
        conversation = new ArrayList<Message>();
    }

    public static User getUser() {
        if ( uniqueInstance == null ) {
            uniqueInstance = new User();
        }
        return uniqueInstance;
    }

    public void initConversation () {
        conversation = new ArrayList<Message>();
    }

    public void addContact (String key, Contact c) {
        listContacts.put(key, c);
    }

    public void addMessageConversation (Message message) { conversation.add(message); }

    public Map<String, Contact> getContacts () {
        return listContacts;
    }

    public ArrayList<Message> getConversation () { return conversation; }

}
