package utt.isi.if26.project.android.messenger.Activity;

import android.widget.ListView;

/**
 * Created by jean-michelly on 14/01/2015.
 */
public interface NewDiscussionControllerListener {

    public void update(ListView contactsNoDiscussion);
    public void getConversation(String key);
    public boolean isNetworkAvailable();
    public void networkIsUnavailable();

}
