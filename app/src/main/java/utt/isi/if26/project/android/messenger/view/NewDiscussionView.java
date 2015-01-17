package utt.isi.if26.project.android.messenger.view;

import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by jean-michelly on 14/01/2015.
 */
public class NewDiscussionView {

    ListView contactsNoDiscussionLv;

    public NewDiscussionView (ListView contactsNoDiscussionLv) {
        this.contactsNoDiscussionLv = contactsNoDiscussionLv;
    }

    public void setListeners(AdapterView.OnItemClickListener onItemClickListener){
        contactsNoDiscussionLv.setOnItemClickListener(onItemClickListener);
    }

    public ListView getContactsNoDiscussion () { return contactsNoDiscussionLv; }

}
