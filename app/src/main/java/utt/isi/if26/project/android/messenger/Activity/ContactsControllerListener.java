package utt.isi.if26.project.android.messenger.Activity;

import android.widget.ListView;

/**
 * Created by jean-michelly on 03/01/2015.
 */
public interface ContactsControllerListener {

    public void update(ListView contactsLv);
    public void showContact(String contact);
    public void wrongRequest();

}
