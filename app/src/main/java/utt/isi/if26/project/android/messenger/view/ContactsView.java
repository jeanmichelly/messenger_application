package utt.isi.if26.project.android.messenger.view;

import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by jean-michelly on 03/01/2015.
 */
public class ContactsView {

    ListView contactsLv;

    public ContactsView (ListView contactsLv) {
        this.contactsLv = contactsLv;
    }

    public void setListeners(AdapterView.OnItemClickListener onItemClickListener){
        contactsLv.setOnItemClickListener(onItemClickListener);
    }

    public ListView getContacts () {
        return contactsLv;
    }

}
