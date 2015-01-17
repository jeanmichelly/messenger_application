package utt.isi.if26.project.android.messenger.controller;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import utt.isi.if26.project.android.messenger.Activity.DiscussionsControllerListener;
import utt.isi.if26.project.android.messenger.Activity.NewDiscussionControllerListener;
import utt.isi.if26.project.android.messenger.view.DiscussionsView;
import utt.isi.if26.project.android.messenger.view.NewDiscussionView;

/**
 * Created by jean-michelly on 14/01/2015.
 */
public class NewDiscussionController implements OnItemClickListener {

    private NewDiscussionView newDiscussionView;
    private NewDiscussionControllerListener listener;

    public NewDiscussionController (ListView contactsNoDiscussion, NewDiscussionControllerListener listener) {
        newDiscussionView = new NewDiscussionView(contactsNoDiscussion);
        this.listener = listener;
    }

    public void initContactsNoDiscussion () {
        listener.update(newDiscussionView.getContactsNoDiscussion());
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        if (listener.isNetworkAvailable()) {
            listener.getConversation(((TextView) arg1).getText().toString());
        } else {
            listener.networkIsUnavailable();
        }
    }

    public void setListeners () {
        newDiscussionView.setListeners(this);
    }

}
