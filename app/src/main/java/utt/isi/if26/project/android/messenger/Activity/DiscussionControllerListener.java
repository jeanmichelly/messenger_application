package utt.isi.if26.project.android.messenger.Activity;

import android.widget.ListView;

/**
 * Created by jean-michelly on 28/12/2014.
 */
public interface DiscussionControllerListener {

    public void update(ListView discussionLv);
    public void sendMessage();
    public void wrongRequest();

}
